<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chatbot Học Java</title>
    <script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>

 
</head>
<body>
    <h2>Java Assistant Chatbot</h2>
    <p>Hãy hỏi chatbot về Java!</p>

    <!-- Đây là nơi chatbot sẽ xuất hiện -->
    <div id="df-chat"></div>

    <script>
       window.addEventListener("DOMContentLoaded", function () {
    window.dfMessenger = document.createElement("df-messenger");
    window.dfMessenger.setAttribute("intent", "WELCOME");
    window.dfMessenger.setAttribute("chat-title", "Java Assistant");
    window.dfMessenger.setAttribute("agent-id", "trangptm-lxqi");  // Thay YOUR_AGENT_ID bằng ID thực tế
    window.dfMessenger.setAttribute("language-code", "vi");
    document.body.appendChild(window.dfMessenger);
});
    </script>
   
</body>
</html>
