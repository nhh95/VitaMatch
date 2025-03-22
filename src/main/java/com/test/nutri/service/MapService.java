package com.test.nutri.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.nutri.api.MapAPI;
import com.test.nutri.model.MapDTO;

/**
 * MapService
 * MapService 클래스는 약국 API에서 데이터를 가져와 파싱하고, 
 * 이를 MapDTO 객체로 변환하여 반환하는 서비스 클래스입니다. 
 * 이 클래스는 약국 목록 및 세부 정보를 조회하고, 
 * 운영 시간과 영업 여부를 계산하여 클라이언트에 제공하는 역할을 합니다.
 * @Service Spring 서비스 클래스 정의
 * @author YuJeong Choi
 */
@Service
public class MapService {

	/**
	 * API URL 생성 및 관련 메서드 제공
	 */
    private final MapAPI mapAPI;
    private final ObjectMapper objectMapper = new ObjectMapper(); 

    public MapService(MapAPI mapAPI) {
        this.mapAPI = mapAPI;
    }
    
    /**
     * 약국 API에서 데이터를 가져와 MapDTO 객체 리스트로 변환합니다.
     * @param url - 약국 API 호출을 위한 URL
     * @return - 약국 정보 객체 리스트
     * 로직:
     * API 호출 후 XML 데이터를 읽습니다.
     * XML 데이터를 파싱하여 약국 정보를 추출합니다.
     * 각 약국의 영업 시간, 좌표, 기타 정보를 MapDTO에 매핑합니다.
     * 현재 시간에 기반하여 영업 여부를 계산합니다.
     * 리스트로 반환합니다.
     */
    // 메서드 분리하고 싶음....
    public List<MapDTO> getPharmacyList(String url) {
    	
    	System.out.println(url);
        List<MapDTO> pharmacyList = new ArrayList<>();
        HttpURLConnection conn = null;
        
        try {
            // API 호출
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                // 응답 읽기
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();

                // XML 파싱
                String xmlResponse = sb.toString();
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(new ByteArrayInputStream(xmlResponse.getBytes()));

                // XML에서 데이터 추출
                Element root = doc.getDocumentElement();
                NodeList items = root.getElementsByTagName("item");

                for (int i = 0; i < items.getLength(); i++) {
                    Node itemNode = items.item(i);
                    if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element itemElement = (Element) itemNode;

                        // MapDTO에 매핑
                        MapDTO dto = new MapDTO();
                        dto.setName(getTagValue("dutyName", itemElement, "정보 없음"));
                        dto.setAddress(getTagValue("dutyAddr", itemElement, "정보 없음"));
                        dto.setTel(getTagValue("dutyTel1", itemElement, "정보 없음"));
                        dto.setHpid(getTagValue("hpid", itemElement, null));

                        // Latitude와 Longitude
                        String latStr = getTagValue("wgs84Lat", itemElement, "0");
                        String lonStr = getTagValue("wgs84Lon", itemElement, "0");
                        dto.setLatitude(Double.parseDouble(latStr.isEmpty() ? "0" : latStr));
                        dto.setLongitude(Double.parseDouble(lonStr.isEmpty() ? "0" : lonStr));

                        //요일 숫자 구하기
                        LocalDate date = LocalDate.now();
                        DayOfWeek week = date.getDayOfWeek();
                        int weekNum = week.getValue();
                         
                        // 영업 시간
                        String openTime = formatTime(getTagValue("dutyTime" + weekNum + "s", itemElement, "0000"), "00:00");
                        String closeTime = formatTime(getTagValue("dutyTime" + weekNum + "c", itemElement, "0000"), "00:00");

                        dto.setOpenTime(openTime);
                        dto.setCloseTime(closeTime);

                        // 영업 여부 계산
                        if (!"정기 휴무".equals(openTime) && !"정기 휴무".equals(closeTime)) {
                            try {
                                LocalTime open = LocalTime.parse(openTime);
                                LocalTime close = LocalTime.parse(closeTime);
                                LocalTime now = LocalTime.now();
                                dto.setOpen(now.isAfter(open) && now.isBefore(close));
                            } catch (Exception e) {
                                System.out.println("영업 여부 계산 중 오류 발생: " + e.getMessage());
                                dto.setOpen(false); // 오류 발생 시 닫힘 상태로 처리
                            }
                        } else {
                            dto.setOpen(false); // "정기 휴무"인 경우 영업하지 않음
                        }

                        
                        //월~일, 공휴일 영업 시간
                        for (int j = 1; j <= 8; j++) {
                            String dutyTimeStart = formatTime(getTagValue("dutyTime" + j + "s", itemElement, "정기 휴무"), "00:00");
                            String dutyTimeClose = formatTime(getTagValue("dutyTime" + j + "c", itemElement, "정기 휴무"), "00:00");

                            switch (j) {
                                case 1:
                                    dto.setDutyTime1s(dutyTimeStart);
                                    dto.setDutyTime1c(dutyTimeClose);
                                    break;
                                case 2:
                                    dto.setDutyTime2s(dutyTimeStart);
                                    dto.setDutyTime2c(dutyTimeClose);
                                    break;
                                case 3:
                                    dto.setDutyTime3s(dutyTimeStart);
                                    dto.setDutyTime3c(dutyTimeClose);
                                    break;
                                case 4:
                                    dto.setDutyTime4s(dutyTimeStart);
                                    dto.setDutyTime4c(dutyTimeClose);
                                    break;
                                case 5:
                                    dto.setDutyTime5s(dutyTimeStart);
                                    dto.setDutyTime5c(dutyTimeClose);
                                    break;
                                case 6:
                                    dto.setDutyTime6s(dutyTimeStart);
                                    dto.setDutyTime6c(dutyTimeClose);
                                    break;
                                case 7:
                                    dto.setDutyTime7s(dutyTimeStart);
                                    dto.setDutyTime7c(dutyTimeClose);
                                    break;
                                case 8:
                                    dto.setDutyTime8s(dutyTimeStart);
                                    dto.setDutyTime8c(dutyTimeClose);
                                    break;

                            }
                        }
                        
                        //비고 
                        String etc = getTagValue("dutyEtc", itemElement, "-");
                        dto.setEtc(etc);
                        
                        pharmacyList.add(dto);
                    }
                }

            } else {
                throw new RuntimeException("API 호출 실패: HTTP 상태 코드 " + conn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pharmacyList;
    }


    /**
     * XML 태그 값을 읽어 반환합니다.
     * @param tag - XML 태그 이름
     * @param element - XML 엘리먼트 객체
     * @param defaultValue - 값이 없을 때 반환할 기본값
     * @return String - 태그 값 또는 기본값
     * 로직:
     * 지정된 태그의 값을 가져옵니다.
     * 태그가 없거나 값이 비어 있으면 기본값을 반환합니다.
     */
    private String getTagValue(String tag, Element element, String defaultValue) {
        NodeList nodeList = element.getElementsByTagName(tag);
        if (nodeList.getLength() > 0 && nodeList.item(0).getChildNodes().getLength() > 0) {
            String value = nodeList.item(0).getTextContent();
            return (value != null && !value.isEmpty()) ? value : defaultValue;
        }
        // 로그를 줄이고 싶으면 아래 줄을 제거하거나 주석 처리
        System.out.println("태그 [" + tag + "]가 존재하지 않거나 값이 비어 있습니다.");
        return defaultValue;
    }

    /**
     * 시간 문자열을 HH:mm 형식으로 변환합니다.
     * @param time - 원본 시간 문자열 (예: 0900)
     * @param defaultValue - 포맷팅 실패 시 반환할 기본값
     * @return String - 포맷된 시간 또는 기본값
     * 로직:
     * 입력 시간이 정기 휴무인 경우 그대로 반환합니다.
     * 입력 시간 문자열의 길이를 검증하고 HH:mm 형식으로 변환합니다.
     * 변환 실패 시 기본값을 반환합니다.
     */
    private String formatTime(String time, String defaultValue) {
        // "정기 휴무"는 그대로 반환
        if ("정기 휴무".equals(time)) {
            return time;
        }
        
        // 시간 값 검증 및 포맷팅
        if (time == null || time.isEmpty() || time.length() < 4) {
            return defaultValue; // 기본값 반환
        }
        try {
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(2, 4));
            if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
                throw new IllegalArgumentException("유효하지 않은 시간 값: " + time);
            }
            return String.format("%02d:%02d", hour, minute); // 정상적인 시간 반환
        } catch (Exception e) {
            System.out.println("시간 포맷팅 중 오류 발생: " + e.getMessage());
            return defaultValue; // 오류 발생 시 기본값 반환
        }
    }
 

}
