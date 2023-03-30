<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
	 	Jsp binding 우선 순위
	 	1. 같은 이름의 속성이 서로 다른 영역에 저장될 수 있다.
	 	2. 접근 범위(Scope)가 좁을 수록 높은 우선 순위를 가진다.
	 		높음									낮음
	 		pageContext > request > session > application
	 	3. 각 영역을 지정하는 표현언어(EL) 내장 객체가 있다.
	 			영역		: 내장 객체
	 		1) pageContext  : pageScope
	 		2) request		: requestScope
	 		3) session		: sessionScope
	 		4) application  : applicationScope
	 	4. Jsp binding 영역에 저장된 값은 모두 EL로 표현할 수 있다.
	 --%>
	  
	 <%
	 	pageContext.setAttribute("a", 1);
	 	request.setAttribute("a", 10);
	 	session.setAttribute("a", 100);
	 	application.setAttribute("a", 1000);
	 %>
	 
	 <%-- 우선순위 확인 --%>
	 <h1>${a}</h1>
	 
	 <%-- 모든 영역의 속성 a 확인 --%>
	 <h3>${pageScope.a}</h3>
	 <h3>${requestScope.a}</h3>
	 <h3>${sessionScope.a}</h3>
	 <h3>${applicationScope.a}</h3>
	 
	 <%--
	 	EL 연산자
	 	1. 산술 : + - * /(div) %(mod)
	 	2. 관계 : <(lt) <=(le) >(gt) >=(ge) ==(eq) !=(ne)
	 	3. 논리 : &&(and) ||(or) !(not)
	 	4. 조건 : (조건식) ? 참 : 거짓
	 --%>
	 
	 <%
	 	// EL 사용을 위해서 pageContext에 저장한다.
	 	pageContext.setAttribute("x", 5);
	 	pageContext.setAttribute("y", 2);
	 %>
	 
	 <ul>
	 	<li>${x + y}</li>
	 	<li>${x - y}</li>
	 	<li>${x * y}</li>
	 	<li>${x div y}</li>
	 	<li>${x mod y}</li>
	 </ul>
	 
	 <ul>
	 	<li>${x lt y}</li>
	 	<li>${x le y}</li>
	 	<li>${x gt y}</li>
	 	<li>${x ge y}</li>
	 	<li>${x eq y}</li>
	 	<li>${x ne y}</li>
	 </ul>
	 
	 <ul>
	 	<li>${x eq 5 and y eq 2}</li>
	 	<li>${x eq 6 or y eq 2}</li>
	 	<li>${not (x eq 5)}</li>
	 </ul>
	 

	  

</body>
</html>