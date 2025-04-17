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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author trang
 */
@WebServlet(name = "ChatServlet1", urlPatterns = {"/chatservlet1"})
public class ChatServlet1 extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String message = request.getParameter("message");
 response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
         String answer=null;
    // Gửi câu hỏi đến Dify/GPT và lấy phản hồi
      try {
          String resultJson = new Test().callDifyAPI(message); // viết hàm send() riêng để gọi API
          JSONObject obj = new JSONObject(resultJson);
           answer = obj.getString("answer");
             response.getWriter().write(answer);
      } catch (Exception e) {
          e.printStackTrace();
            response.getWriter().write("Lỗi: " + e.getMessage());
      }
 

    // Lấy session và cập nhật lịch sử chat
    HttpSession session = request.getSession();
    List<String> chatHistory = (List<String>) session.getAttribute("chatHistory");
    if (chatHistory == null) {
        chatHistory = new ArrayList<>();
    }
    
    chatHistory.add("Bạn: " + message);
    chatHistory.add("Bot: " + answer);
    session.setAttribute("chatHistory", chatHistory);

    request.getRequestDispatcher("viewChat1.jsp").forward(request, response);
}

}
