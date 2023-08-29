<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글등록</title>
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

    div {
        max-width: 600px;
        margin: 0 auto;
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

    form button[type="submit"],
    form button[type="button"] {
        padding: 10px 20px;
        background-color: #007bff;
        border: none;
        border-radius: 3px;
        color: #fff;
        cursor: pointer;
    }

    form button[type="button"] {
        background-color: #ccc;
        margin-left: 10px;
    }
    
    #btn-board-list {
        background-color: #ccc;
        margin-left: 10px;
    }
</style>
</head>
<body>
<script>
	var msg = '${msg}';
	if(msg){
		alert(msg);
	}
</script>
<div>
    <form action="${pageContext.request.contextPath }/board/insert" method="post"
            enctype="multipart/form-data" >
        <c:if test="${not empty bno }">
            <input type="hidden" name="bno" value="${bno }">
        </c:if>
        제목: <input type="text" name="btitle">
        <br>
        내용: <textarea rows="10" cols="50" name="bcontent"></textarea>
        <br>
        <input type="file" name="uploadFile1">
        <input type="file" name="uploadFile2">
        <input type="file" name="uploadFile">
        <input type="file" name="uploadFile">
        <input type="file" name="uploadFile">
        <input type="checkbox" name="hobby" value="a">a<br>
        <input type="checkbox" name="hobby" value="b">b<br>
        <input type="checkbox" name="hobby" value="c">c<br>
        <button type="submit" id="btn-board-insert">글 등록</button>
        <input type="hidden" name="${_csrf.parameterName }" value="${_csrf. }">
	    <button type="button" id="btn-board-list">글목록으로 이동</button>
    </form>
</div>
<script>
	$("#btn-board-list").click(function(){
		location.href="${pageContext.request.contextPath}/board/list";
	});
</script>
</body>
</html>