package kr.codeback.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {

	//요청해서 Json으로 응답을 받기
	public JsonNode getResp(Map<String,String> codemap, HttpURLConnection conn) throws IOException {

		// 응답 코드가 200 (HTTP_OK) 인지 확인
		int responseCode = conn.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) {
			// 입력 스트림 생성
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			// 응답 내용을 한 줄씩 읽어서 StringBuffer에 추가
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			// 입력 스트림 닫기
			in.close();

			// Jackson ObjectMapper 객체 생성
			ObjectMapper objectMapper = new ObjectMapper();

			// JSON 문자열을 JsonNode로 변환
			JsonNode jsonNode = objectMapper.readTree(response.toString());

			return jsonNode;
		} else {
			System.out.println("POST request not worked");
			return null;
		}
	}
}