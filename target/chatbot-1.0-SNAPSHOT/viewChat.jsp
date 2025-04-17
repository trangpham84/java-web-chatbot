<%-- 
    Document   : viewChat
    Created on : Apr 7, 2025, 3:03:48 PM
    Author     : trang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <title>Chatbot Java + Dify</title>
    
    <script>
        async function sendMessage() {
            const userMessage = document.getElementById("userMessage").value;

            const response = await fetch("chatservlet", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: "message=" + encodeURIComponent(userMessage)
            });

            const botReply = await response.text();
            document.getElementById("chatOutput").innerText = botReply;
        }
    </script>
</head>
<body>
    <h2>Chatbot hỗ trợ học Java</h2>
    <input type="text" id="userMessage" placeholder="Nhập câu hỏi...">
    <button onclick="sendMessage()">Gửi</button>

    <div id="chatOutput" style="margin-top: 20px; color: green;"></div>
</body>
</html>
