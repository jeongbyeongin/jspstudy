<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");
		String model = request.getParameter("model");	 							// Optional = null은 잡는데 빈 문자열이면 Optional을 사용하지않는다.
		Optional<String> opt = Optional.ofNullable(request.getParameter("price"));	// request.getParameter("price") = 파라미터 price가 null일 수도있는데 
		int price = Integer.parseInt(opt.orElse("0")); 								// opt.orElse("0") = null이면 0처리 해주세요  get은 파라미터가 있을 때 사용
		
	%>
	
	<h1>모델 : <%=model%></h1>
	<h1>가격 : <%=price%></h1>

</body>
</html>
