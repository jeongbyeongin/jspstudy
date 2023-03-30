<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src='<c:url value="resources/js/lib.jquery-3.6.4.min.js"/>'></script>
<script>
	if(confirm('이동할까요?')){
		location.href='<c:url value="/ex07_jstl/core/01_set.jsp"/>';
	}
</script>
</head>
<body>

	<%--
		<c:url>
		1. 경로를 작성하는 태그이다.
		2. 컨텍스트 패스가 자동으로 포함된다.
		3. 파라미터를 추가할 수 있다.
		4. 형식
			<c:url value="경로">
				<c:param name="파라미터" value="값"></c:param>
			</c:url>
	--%>

</body>
</html>







