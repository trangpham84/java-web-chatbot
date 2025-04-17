/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.chatbot;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 *
 * @author trang
 */
@WebServlet(name = "ChatbotServlet", urlPatterns = {"/ChatbotServlet"})
public class ChatbotServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userMessage = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();
        
        // Gửi yêu cầu đến Dify API
        String difyResponse = callDifyAPI(userMessage);
        
        response.setContentType("application/json");
        response.getWriter().write("{\"reply\": \"" + difyResponse + "\"}");
    }

    private String callDifyAPI(String userMessage) throws IOException {
        URL url = new URL("https://api.dify.ai/v1");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer YOUR_DIFY_API_KEY");
        conn.setDoOutput(true);

        String jsonInputString = "{\"messages\": [{\"role\": \"user\", \"content\": \"" + userMessage + "\"}]}";
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        String response = br.readLine();
        br.close();
        
        return response;
    }

}
