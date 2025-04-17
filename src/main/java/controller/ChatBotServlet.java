/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
@WebServlet(name = "ChatBotServlet", urlPatterns = {"/chat"})
public class ChatBotServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("Chatbot đang hoạt động!");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("Chatbot đang hoạt động!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String userMessage = reader.readLine();
        reader.close();

        // Gọi API của Dify
        String difyResponse = callDifyAPI(userMessage);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"reply\": \"" + difyResponse.replace("\n", "<br>") + "\"}");
    }
//dataset-apD3s2stgHK10fF4h9p4tN0K

    private String callDifyAPI(String userMessage) throws IOException {
        URL url = new URL("https://api.dify.ai/v1");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer 3wvdubZnV31FFXaBOBR55ZK2");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

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
