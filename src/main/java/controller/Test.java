/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author trang
 */
public class Test {
    public String callDifyAPI(String userMessage) throws IOException {
    // 1. Đúng endpoint của Dify
    URL url = new URL("https://api.dify.ai/v1/chat-messages");

    // 2. Mở kết nối HTTP POST
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("POST");

    // 3. Thêm Header cần thiết
    conn.setRequestProperty("Authorization", "Bearer app-3wvdubZnV31FFXaBOBR55ZK2"); // ← Thay bằng API key thật của bạn
    conn.setRequestProperty("Content-Type", "application/json");
    conn.setDoOutput(true); // Cho phép gửi body

    // 4. Tạo JSON body đúng định dạng Dify yêu cầu
   String jsonInputString = "{"
    + "\"inputs\": {},"
    + "\"query\": \"" + userMessage + "\","
    + "\"response_mode\": \"blocking\","
    + "\"user\": \"test-user\""
    + "}";

    // 5. Gửi JSON vào output stream
    try (OutputStream os = conn.getOutputStream()) {
        byte[] input = jsonInputString.getBytes("utf-8");
      //  System.out.print(input);
        os.write(input, 0, input.length);
    }

    // 6. Đọc phản hồi từ server
    int responseCode = conn.getResponseCode();
    InputStream inputStream = (responseCode == 200) ? conn.getInputStream() : conn.getErrorStream();

    StringBuilder response = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"))) {
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line.trim());
        }
    }

    // 7. Trả về chuỗi JSON phản hồi
    return response.toString();
}
    public static void main(String[] args) {
    try {
        String result = new Test().callDifyAPI("oop in java");
       // Phân tích JSON
        JSONObject responseObj = new JSONObject(result);
        String answer = responseObj.getString("answer");

        // In ra nội dung trả lời của chatbot
        System.out.println("Chatbot trả lời:\n" + answer);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
