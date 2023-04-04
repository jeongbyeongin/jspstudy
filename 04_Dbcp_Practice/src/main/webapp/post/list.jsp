<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="${contextPath}/write.post">새 포스트 작성</a>
	
	<hr>

	<div class="container">		<!-- 공부하려면 여기에 부트스트랩 넣어서 공부 -->
		<c:forEach var="post" items="${posts}">  <!-- 포스트리스트서비스에서 에뜨리뷰트 이름이 여기서 사용 -->
			<ul>
				<li>포스트 번호 ${post.post_no}</li>	<!-- 위에 forEach에 var = post에서 온것이다 -->
				<li>작성자 ${post.writer}</li>
				<li>제목 ${post.title}</li>
				<li>작성일 ${post.created_date}</li>
			</ul>
		</c:forEach>
	</div>

</body>
</html>