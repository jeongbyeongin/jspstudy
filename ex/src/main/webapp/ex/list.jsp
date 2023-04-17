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
<script>
	function fnDetail(exNo){
		location.href = '${contextPath}/detail.do?exNo=' + exNo;
	}
	function fnWrite(){
		location.href = '${contextPath}/write.do';
	}
</script>
</head>
<body>
	<c:forEach items="${exList}" var="ex">
		<div style="border: 1px solid gray; cursor: pointer; width:250px;" onclick="fnDetail(${ex.exNo})">
			<div>${ex.exNo}</div>
			<div>${ex.exContent}</div>
			<div>${ex.exCreatedAt}</div>
		</div>
	</c:forEach>
	<input type="button" value="작성하러가기" onclick="fnWrite()">
</body>
</html>