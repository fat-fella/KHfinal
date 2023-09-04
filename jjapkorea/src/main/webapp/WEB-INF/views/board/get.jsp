<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

input[type="text"], textarea {
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
			<div>
				<form action="${pageContext.request.contextPath }/board/update"
					method="get">
					<input type="hidden" name="bno" value="${bvo.bno}"> <label
						for="btitle">제목:</label> <input type="text" id="btitle"
						name="btitle" value="${bvo.btitle}" readonly> <br> <label
						for="bcontent">내용:</label>
					<textarea id="bcontent" rows="10" cols="50" name="bcontent"
						readonly>${bvo.bcontent}</textarea>
					<br>
					<a href="${pageContext.request.contextPath}/board/list">
					<button type="submit" id="btn-board-update">글 수정</button>
					</a>
					<button type="button" id="btn-board-delete">글 삭제</button>
					<a href="${pageContext.request.contextPath}/board/list">
					  <button type="button">글 목록으로 이동</button>
					</a>				
				</form>
			</div>
			<ul>
				<c:forEach items="${bvo }" var="commentList">
					<li>
						<div>
							<p>${bvo.mid } / ${bvo.bwriteDate }</p> 
							<p>${bvo.bcontent }</p>
						</div>
					</li>
				</c:forEach>
			</ul>
		<!-- Comments Form -->
			<div class="card my-4">
				<h5 class="card-header">Leave a Comment:</h5>
				<div class="card-body">
					<form action="${pageContext.request.contextPath}/board/insertReply" method="post" enctype="multipart/form-data">
						<div class="form-group">
							<input type="hidden" name="bno" value="${bvo.bno}"/>
							<textarea name="content" class="form-control" rows="3"></textarea>
						</div>
						<button type="submit" class="btn-board-comment">댓글 등록</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
    $("#btn-board-delete").click(function () {
        var bno = '${bvo.bno}';
        if (confirm("글 삭제하시겠습니까?")) {
          $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/board/delete",
            data: { bno: bno },
            success: function (response) {
            	console.log(response);
	            if(response > 0){
	            	alert("삭제되었습니다.");
	            }else {
	            	alert("삭제 실패했습니다");
	            }
				location.href = "${pageContext.request.contextPath}/board/list";
				}
			});
		}
	});

    $("#btn-board-comment").click(function() {
    	var bno = '${bvo.bno}';
        if (confirm("댓글을 등록하시겠습니까?")) {
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/board/insertReply",
                dataType: "json",
                data: { 
                	bno: bno,
                    btitle: btitle,
                    bcontent: bcontent
                },
                success: function(response) {
                    if (response > 0) {
                        alert("댓글 등록되었습니다.");
                        location.href = "${pageContext.request.contextPath}/board/list;
                    } else {
                        alert("댓글 등록에 실패했습니다.");
        				location.href = "${pageContext.request.contextPath}/board/list";

                    }
                }
            });
        }
    });
</script>
</body>
</html>