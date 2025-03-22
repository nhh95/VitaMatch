/*
 * package com.test.nutri.controller;
 * 
 * import java.net.URI; import java.util.ArrayList; import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Value; import
 * org.springframework.http.HttpHeaders; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.http.RequestEntity; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.ResponseBody; import
 * org.springframework.web.client.RestTemplate; import
 * org.springframework.web.util.UriComponentsBuilder;
 * 
 * import com.fasterxml.jackson.databind.JsonNode; import
 * com.fasterxml.jackson.databind.ObjectMapper;
 * 
 * import lombok.AllArgsConstructor; import lombok.Data;
 * 
 * @Controller public class ChatGptController {
 * 
 * @Value("${api-key.chatGpt.key}") private String key;
 * 
 * // PostMapping으로 변경
 * 
 * @PostMapping("/api/send") public ResponseEntity<String> send(@RequestBody
 * UserMessage userMessage) { //System.out.println("Received message: " +
 * userMessage.getMessage());
 * 
 * RestTemplate restTemplate = new RestTemplate();
 * 
 * URI uri = UriComponentsBuilder
 * .fromUriString("https://api.openai.com/v1/chat/completions") .build()
 * .encode() .toUri();
 * 
 * HttpHeaders httpHeaders = new HttpHeaders(); httpHeaders.add("Authorization",
 * "Bearer " + key);
 * 
 * 
 * // 메시지 리스트 준비 ArrayList<Message> list = new ArrayList<>(); list.add(new
 * Message("user", userMessage.getMessage()));
 * 
 * // Body에 모델과 메시지 리스트 설정 Body body = new Body("gpt-3.5-turbo", list);
 * 
 * // RequestEntity 생성 RequestEntity<Body> httpEntity = new
 * RequestEntity<>(body, httpHeaders, HttpMethod.POST, uri);
 * 
 * // OpenAI API 호출 ResponseEntity<String> exchange =
 * restTemplate.exchange(httpEntity, String.class);
 * 
 * // 응답 처리 try { ObjectMapper objectMapper = new ObjectMapper(); JsonNode
 * rootNode = objectMapper.readTree(exchange.getBody()); // JSON 문자열을 파싱
 * 
 * // "choices" 배열에서 첫 번째 항목의 "message.content" 값을 추출 String content =
 * rootNode.path("choices").get(0).path("message").path("content").asText();
 * //System.out.println(">>>>>"+content); // 출력
 * 
 * return ResponseEntity.ok(content); // 클라이언트에게 content만 응답 } catch (Exception
 * e) { e.printStackTrace(); return
 * ResponseEntity.status(500).body("Error parsing response"); } }
 * 
 * @AllArgsConstructor
 * 
 * @Data static class Body { String model; List<Message> messages; }
 * 
 * @AllArgsConstructor
 * 
 * @Data static class Message { String role; String content; }
 * 
 * // 사용자 메시지 구조를 정의 (requestBody로 사용할 클래스)
 * 
 * @Data static class UserMessage { String message; }
 * 
 * 
 * }
 */