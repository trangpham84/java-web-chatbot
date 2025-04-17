<%-- 
    Document   : chatbotdify_Css
    Created on : Apr 4, 2025, 7:14:31 AM
    Author     : trang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chatbot Dạy Java</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .chat-container {
            width: 400px;
            max-width: 100%;
            background: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
        }
        .chat-header {
            background: #007bff;
            color: white;
            padding: 15px;
            text-align: center;
            font-size: 18px;
            font-weight: bold;
        }
        .chat-box {
            height: 300px;
            overflow-y: auto;
            padding: 10px;
            display: flex;
            flex-direction: column;
        }
        .chat-message {
            max-width: 80%;
            padding: 10px;
            border-radius: 10px;
            margin-bottom: 10px;
        }
        .user-message {
            align-self: flex-end;
            background: #007bff;
            color: white;
        }
        .bot-message {
            align-self: flex-start;
            background: #e9ecef;
        }
        .chat-input {
            display: flex;
            padding: 10px;
            border-top: 1px solid #ddd;
        }
        .chat-input input {
            flex: 1;
            padding: 10px;
            border: none;
            border-radius: 5px;
        }
        .chat-input button {
            background: #007bff;
            color: white;
            border: none;
            padding: 10px;
            margin-left: 5px;
            cursor: pointer;
            border-radius: 5px;
        }
        .chat-input button:hover {
            background: #0056b3;
        }
    </style>
    <script>
function sendMessage() {
    const message = document.getElementById("userInput").value;

    fetch("testchat", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: "message=" + encodeURIComponent(message)
    })
    .then(response => response.json())
    .then(data => {
        console.log("Phản hồi từ AI:", data);
        // xử lý hiển thị đoạn trả lời của AI từ data.answer hoặc tương ứng
    });
}
</script>
</head>
    <body>

<div class="chat-container">
    <div class="chat-header">Chatbot Dạy Java</div>
    <div id="chatBox" class="chat-box"></div>
    <div class="chat-input">
        <input type="text" id="userInput" placeholder="Nhập câu hỏi hoặc chọn chương..." />
        <button onclick="sendMessage()">Gửi</button>
    </div>
</div>


 
</body>
</html>
