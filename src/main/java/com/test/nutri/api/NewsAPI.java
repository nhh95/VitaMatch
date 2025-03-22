package com.test.nutri.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.nutri.model.NewsListDTO;

import lombok.RequiredArgsConstructor;

/**
 * 네이버 뉴스 검색 API를 호출하여 뉴스 목록을 가져오는 Component Class.
 * 
 * 이 클래스는 네이버의 오픈 API를 사용하여 뉴스 검색 결과를 가져옵니다. 검색어에 대한 뉴스 목록을 
 * 페이징 처리하여 받아올 수 있으며, 응답 받은 JSON 데이터를 NewsListDTO 객체로 매핑합니다.
 * 
 * @author chimy2
 */
@Component
@RequiredArgsConstructor
public class NewsAPI {

    /**
     * 네이버 클라이언트 ID (API 키).
     * {@link application.yml} 파일에서 값을 주입받습니다.
     */
	@Value("${api-key.news.client-id}")
	String clientId;

    /**
     * 네이버 클라이언트 시크릿 (API 키).
     * {@link application.yml} 파일에서 값을 주입받습니다.
     */
	@Value("${api-key.news.client-secret}")
	String clientSecret;

    /**
     * JSON 데이터를 객체로 변환하는 ObjectMapper.
     */
	private final ObjectMapper objectMapper;

    /**
     * 기본 뉴스 검색 결과를 가져오는 메서드.
     * 
     * '영양제, 건강'을 검색어로 사용하여 네이버 뉴스 API에서 최신 100개의 뉴스 목록을 가져옵니다.
     * 
     * @return {@link NewsListDTO} 객체에 매핑된 뉴스 목록.
     */
	public NewsListDTO getNewsList() {
		String search = null;
		try {
			search = URLEncoder.encode("영양제,건강", "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}
		
		String apiURL = String.format("https://openapi.naver.com/v1/search/news?query=%s&display=100&sort=date", search);
		
		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("X-Naver-Client-Id",  clientId);
		requestHeaders.put("X-Naver-Client-Secret",  clientSecret);
		
		String responseBody = get(apiURL, requestHeaders);
		
		try {
			return objectMapper.readValue(responseBody, NewsListDTO.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

    /**
     * 주어진 시작 번호를 기준으로 뉴스 검색 결과를 가져오는 메서드.
     * 
     * @param start 검색 결과의 시작 번호 (1부터 시작).
     * @return {@link NewsListDTO} 객체에 매핑된 뉴스 목록.
     */
	public NewsListDTO getNewsList(int start){
		if(start < 1 || start >= 10) {
			return null;
		}
		
		int display = 100;
		String search = null;
		
		try {
			search = URLEncoder.encode("영양제,건강", "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}
		
		String apiURL = String.format("https://openapi.naver.com/v1/search/news?query=%s&display=100&sort=date&start=%d", search, (start - 1) * display + 1);
		
		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("X-Naver-Client-Id",  clientId);
		requestHeaders.put("X-Naver-Client-Secret",  clientSecret);
		
		String responseBody = get(apiURL, requestHeaders);
		
		try {
			return objectMapper.readValue(responseBody, NewsListDTO.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

    /**
     * 지정된 API URL에 GET 요청을 보내고 응답을 반환하는 메서드.
     * 
     * @param apiUrl 호출할 API의 URL.
     * @param requestHeaders 요청 헤더 (네이버 API의 클라이언트 ID 및 시크릿).
     * @return API 호출 응답 문자열.
     */
	private String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
	}

    /**
     * API URL에 연결을 시도하고 {@link HttpURLConnection} 객체를 반환하는 메서드.
     * 
     * @param apiUrl 호출할 API의 URL.
     * @return 연결된 {@link HttpURLConnection} 객체.
     */
	private HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
	}

    /**
     * 입력 스트림을 읽고 응답 본문을 문자열로 반환하는 메서드.
     * 
     * @param body 입력 스트림.
     * @return 응답 본문 문자열.
     */
	private String readBody(InputStream body) {

        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
}
