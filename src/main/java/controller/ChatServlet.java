/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.json.JSONObject;

/**
 *
 * @author trang
 */
@WebServlet(name = "ChatServlet", urlPatterns = {"/chatservlet"})
public class ChatServlet extends HttpServlet {

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userMessage = request.getParameter("message");
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        try {
            // Gọi tới class Test và dùng hàm callDifyAPI
            String resultJson = new Test().callDifyAPI(userMessage);
            JSONObject obj = new JSONObject(resultJson);
           String answer = obj.getString("answer");
           response.getWriter().write(answer);
                 // Trả về dạng text
   
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Lỗi: " + e.getMessage());
        }
       
    }

}
