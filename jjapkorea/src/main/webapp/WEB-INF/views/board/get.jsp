<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardOne</title>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f5f5f5;
    }
    .container {
      max-width: 800px;
      margin: 20px auto;
      background-color: #ffffff;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      overflow: hidden;
    }
    .content {
      padding: 20px;
    }
    h2 {
      margin-top: 0;
      font-size: 24px;
      color: #333333;
    }
    input[type="text"],
    textarea {
      width: 100%;
      padding: 12px;
      margin-bottom: 10px;
      border: 1px solid #e0e0e0;
      border-radius: 4px;
      background-color: #f7f7f7;
      font-size: 16px;
      color: #333333;
    }
    button {
      padding: 10px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      background-color: #007bff;
      color: #ffffff;
      font-size: 16px;
      transition: background-color 0.2s;
    }
    button:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>
 <div class="container">
    <div class="content">
      <c:choose>
        <c:when test="${not empty bvo}">
          <h2>${bvo.bno}번글</h2>
        </c:when>
        <c:otherwise>
          <script>
            alert("해당하는 글을 읽을 수 없습니다. 다시 글 선택해주세요.");
            location.href = "${pageContext.request.contextPath}/board/list";
          </script>
        </c:otherwise>
      </c:choose>
      <div>
        <form action="${pageContext.request.contextPath }/board/update" method="get">
          <input type="hidden" name="bno" value="${bvo.bno}">
          <label for="btitle">제목:</label>
          <input type="text" id="btitle" name="btitle" value="${bvo.btitle}" readonly>
          <br>
          <label for="bcontent">내용:</label>
          <textarea id="bcontent" rows="10" cols="50" name="bcontent" readonly>${bvo.bcontent}</textarea>
          <br>
          <button type="submit" id="btn-board-update">글 수정</button>
          <button type="button" id="btn-board-list">글 목록으로 이동</button>
        </form>
      </div>
    </div>
  </div>
  <script>
    $("#btn-board-list").click(function () {
      location.href = "${pageContext.request.contextPath}/board/list";
    });
    $("#btn-board-update").click(function () {
      location.href = "${pageContext.request.contextPath}/board/update?bno=${bvo.bno}";
    });
  </script>
</body>
</html>