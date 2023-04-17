<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
</head>
<body>
		<form method="post" action="${contextPath}/modify.do">
			<div>${ex.exNo}</div>
			<div>
				<input type="text" id="exContent" value="${ex.exContent}">
			</div>
			<div>${ex.exCreatedAt}</div>
			<div>
				<input type="button" value="삭제" onclick="fnRemove()">
				<input type="button" value="수정하기" onclick="fnModified()">
				<input type="button" value="목록" onclick="fnList()">
			</div>
		</form>
<script>
	function fnRemove(){
		if(confirm('삭제할거냐?')){
			location.href = '${contextPath}/remove.do?exNo=${ex.exNo}';
		}
	}
	function fnModified(){
		if(confirm('수정?')){
		location.href= '${contextPath}/modify.do?exNo=${ex.exNo}&exContent=' + $('#exContent').val();
		}
	}
	function fnList(){
		location.href= '${contextPath}/list.do';
	}
</script>
		
</body>
</html>