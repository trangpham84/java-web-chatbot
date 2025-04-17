<%-- 
    Document   : viewChat1
    Created on : Apr 7, 2025, 4:37:47 PM
    Author     : trang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page session="true" %>
<%
    List<String> chatHistory = (List<String>) session.getAttribute("chatHistory");
    if (chatHistory == null) {
        chatHistory = new ArrayList<>();
        session.setAttribute("chatHistory", chatHistory);
    }
%>

<h2>Chatbot Java</h2>
<div style="border:1px solid gray; padding:10px; height:300px; overflow-y:auto;">
    <c:forEach var="msg" items="${chatHistory}">
        <p>${msg}</p>
    </c:forEach>
</div>

<form action="chatservlet1" method="post">
  <input type="text" name="message" placeholder="Nhập câu hỏi...">
  <input type="submit" value="Gửi">
</form>
