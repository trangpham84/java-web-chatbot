/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
@WebServlet(name = "TesChat", urlPatterns = {"/testchat"})
public class TesChat extends HttpServlet {
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Đọc request từ frontend
        Gson gson = new Gson();
        ChatRequest chatRequest = gson.fromJson(request.getReader(), ChatRequest.class);
        
        // Xử lý chatbot
        String userMessage = chatRequest.getMessage();
        String botResponse = "Bạn vừa nói: " + userMessage;
 
        // Gọi API của Dify
       // String difyResponse = callDifyAPI(userMessage);
        // Đặt header JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Trả về JSON đúng định dạng
         JSONObject responseObj = new JSONObject(botResponse);
        String answer = responseObj.getString("answer");
      ChatResponse chatResponse = new ChatResponse(answer);
      response.getWriter().write(answer);
response.getWriter().write(gson.toJson(chatResponse));
 
    }
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
}

// Model nhận request từ frontend
class ChatRequest {
    private String message;
    public String getMessage() { return message; }
}

// Model phản hồi về frontend
class ChatResponse {
    private String reply;
    public ChatResponse(String reply) { this.reply = reply; }
    }

  



