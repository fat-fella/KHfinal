<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardUpdate</title>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
    }

    h2 {
        text-align: center;
        margin-top: 20px;
    }

    form {
        max-width: 800px;
        margin: 20px auto;
        padding: 20px;
        background: #fff;
        border: 1px solid #ddd;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    form input[type="text"],
    form textarea {
        width: 100%;
        padding: 10px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 3px;
        resize: vertical;
    }

    form input[type="submit"],
    form button {
        padding: 10px 20px;
        background-color: #007bff;
        border: none;
        border-radius: 3px;
        color: #fff;
        cursor: pointer;
    }

    form button#btn-board-list {
        background-color: #ccc;
        margin-left: 10px;
    }
</style>
</head>
<body>
<h2>글 수정</h2>
<form action="${pageContext.request.contextPath }/board/update" method="post">
	<input type="hidden" name="bno" value="${dto.bno}">
    제목: <input type="text" name="btitle" value="${dto.btitle}">
    <br>
    내용: <textarea rows="10" cols="50" name="bcontent">${dto.bcontent}</textarea>
    <br>
    <input type="submit" value="수정">
    <button type="button" id="btn-board-list">메인으로</button>
</form>
[[
${dto }
]] 

<script>   
	$("#btn-board-list").click(function(){
	    location.href="${pageContext.request.contextPath}/board/list";
	});    
</script>
</body>
</html>