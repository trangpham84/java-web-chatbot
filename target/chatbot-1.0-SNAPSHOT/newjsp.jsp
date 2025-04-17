<%-- 
    Document   : newjsp
    Created on : Apr 2, 2025, 3:32:22 PM
    Author     : trang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
    <meta charset="UTF-8">
    <title>Chatbot Học Java</title>
    <script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>

 <style>
  df-messenger {
      position: fixed;
      bottom: 10px;
      right: 10px;
      z-index: 999;
  }
</style>
<script>
        function sendRequest() {
            var prompt = document.getElementById("prompt").value;
            if (!prompt) {
                alert ("Please enter promt");
                return;
            }
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "testchat", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    document.getElementById("response").innerHTML = "<strong>Generated Code:</strong><br>" + xhr.responseText;
                }
            };

            xhr.send("prompt=" + encodeURIComponent(prompt));
        }
    </script>
</head>
   <body>
    <h2>Java Assistant Chatbot</h2>
    <p>Hãy hỏi chatbot về Java!</p>

    <!-- Đây là nơi chatbot sẽ xuất hiện -->
    <div id="df-chat"></div>

    
   <body>
    <h2>Generate Java Code using AI</h2>
    
    <label for="prompt">Enter your code request:</label>
    <textarea id="prompt" placeholder="Enter your code request here..."></textarea>
    
    <button onclick="sendRequest()">Generate Java Code using AI</button>
    
    <h3>AI Generated Code:</h3>
    <div id="response">Your generated code will appear here...</div>
</body>
</body>
</html>
