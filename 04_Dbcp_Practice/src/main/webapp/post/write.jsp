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

	<form method="post" action="${contextPath}/save.post">
		<div><input type="text" name="writer" value="${sessionScope.loginId}" readonly></div>		<!--  로그인 한 아이디가 자동으로 들어간다 작성자는 못고친다.  -->
		<div><input type="text" name="title" placeholder="제목"></div>
		<div><textarea name="content" rows="5" cols="30" placeholder="내용"></textarea></div>
		<div>
			<c:if test="${sessionScope.loginId != null}">
			<button>저장하기</button>
			</c:if>
			<input type="button" value="목록" onclick="goList()">
		</div>
	</form>
	
	<script>
		function goList(){
			location.href='${contextPath}/list.post';
		}
	</script>

</body>
</html>