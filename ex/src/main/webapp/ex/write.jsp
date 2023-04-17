<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>작성 화면</h1>
	<form method="post" action="${contextPath}/save.do">
		<!-- exNo는 EX_SEQ를 사용하고, exCreatedAt은 SYSTIMESTAME를 사용하므로 사용자로부터 입력 받지 않습니다. -->
		<div>
			<input type="text" name="exContent" placeholder="저장할내용입력">
		</div>
		<div>
			<button>저장</button>
			<input type="reset" value="다시작성하기">
			<input type="button" value="목록보기" onclick="fnList()">
		</div>
	</form>
	<script>
		function fnList(){
			location.href = '${contextPath}/list.do';
		}
	</script>
	
</body>
</html>