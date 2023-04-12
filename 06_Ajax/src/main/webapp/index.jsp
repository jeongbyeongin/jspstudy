<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("contextPath", request.getContextPath());
%> <!-- c:set 태그와 같다 -->
</head>
<body>

	<div class="wrap">
	
		<h1>회원 관리</h1>
		<form id="frm_member">
			<div>		
				<label for="id">아이디</label>
				<input type="text" id="id" name="id"> 		<!-- name은 자료들을 줄 때 좋다. -->
			</div>
			<div>
				<label for="name">이름</label>
				<input type="text" id="name" name="name">
			</div>
			<div>
				<input type="radio" id="male" name="gender" value="M">	<!-- value를 줘야 해당 값이 넘어가서 반드시 있어야함. -->
				<label for="male">남자</label>
				<input type="radio" id="female" name="gender" value="F">	<!-- value를 줘야 해당 값이 넘어가서 반드시 있어야함. -->
				<label for="female">여자</label>
			</div>
			<div>
				<label for="address">주소</label>
				<input type="text" id="address" name="address">
			</div>
			<div>
				<input type="hidden" id="memberNo" name="memberNo">
				<input type="button" value="초기화" onclick="fnInit()">
				<input type="button" value="신규등록" onclick="fnAddMember()">
				<input type="button" value="변경저장" onclick="fnModifyMember()">
				<input type="button" value="삭제" onclick="fnRemoveMember()">
			</div>
		</form>
	
		<hr>
		
		<table border="1">
			<caption>전체 회원 수 : <span id="member_count"></span>명</caption>		<!-- 테이블의 제목 -->
			<thead>
				<tr>
					<td>회원번호</td>
					<td>아이디</td>
					<td>이름</td>
					<td>성별</td>
					<td>주소</td>
					<td>버튼</td>
				</tr>
			</thead>
		<tbody id="member_list"></tbody>		<!-- dom조작으로 만들 것이다. -->
		</table>
	</div>
	
	<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	
	/* 함수 호출 (먼저하든 나중에하든 상관없다 // 호이스팅대상이므로) */
	fnGetAllMember();
	fnInit();
	
	/* 함수 정의 */
	function fnInit(){
		$('#id').val('').prop('disbled', false);
		$('#name').val('');
		$(':radio[name=gender]').prop('checked', false);
		$('#address').val('');
	}
	
	function fnGetAllMember(){
		$.ajax({
			// 요청
			type: 'get',
			url: '${contextPath}/list.do',
			// 응답
			dataType: 'json',
			success: function(resData){	 // 응답 데이터는 resData로 전달된다.
				/*
					resData <-- obj.toString()
					(이렇게 들어온 상황이다.)
					resData = {
						"memberCount": 2,
						"memberList": [
							{ },
							{ }
						]
					}
				*/
				$('#member_count').text(resData.memberCount);  // resData에 있는 memberCount ( 2 )를 가져온 것 다른 방법 resData['memberCount'] !! 객체의 프로퍼티 값 꺼내오는 방법
				let memberList = $('#member_list');
				memberList.empty(); 		// 기존의 회원 목록을 지운다. (ajax에서는 값을 계속 가져올 것이라서 지우는 것이 좋다.)
				
				if(resData.memberCount == 0){		// == 0  // 회원이 없으면
					memberList.append('<tr><td colspan="6">회원이 없습니다.</td></tr>'); 	// 회원이없을 때 6칸을 합쳐서 추가해 주겠다.
				} else {
					/* $.each(배열, (인덱스, 요소)->{}) */ 		// 배열에 하나씩 알아서 꺼내서 쓴다. 1번인덱스에 누구 이런식으로 // (인덱스, 요소)->{} == 함수이다.
					/* $.each(배열, function(인덱스, 요소){})  위에는 화살표 함수 , 여기서는 익명함수 */
					$.each(resData.memberList, function(i, element){  	// 여기서 element는 한 회원 객체를 의미 ( memberList 안에있는 회원 한명의 객체 정보 )
					let str = '<tr>';
					str += '<td>' + element.memberNo + '</td>';
					str += '<td>' + element.id + '</td>';
					str += '<td>' + element.name + '</td>';
					str += '<td>' + (element.gender === 'M' ? '남자' : '여자') + '</td>';
					str += '<td>' + element.address + '</td>';
					str += '<td><input type="button" value="조회" class="btn_detail" onclick="fnGetMember(' + element.memberNo + ')"></td>';
					memberList.append(str);
					})
				}
			}
		})
	}
	
	function fnAddMember(){		// onclick에서 fnAddMember()를 해줘서 함수호출을 따로 안해줘도된다.
		$.ajax({
			// 요청
			type: 'post',	// add 할 때는 무조건 post 하기로 했다.
			url: '${contextPath}/add.do', 		// 컨트롤러 했을 때 지정했다 (스위치문에)
			data: $('#frm_member').serialize(), 							// 보내주는 파라미터를 data라고 한다. // 폼의 모든 입력 요소를 파라미터로 전송한다.(입력 요소에는 name속성이 필요하다.)
			// 응답
			dataType: 'json',
			success: function(resData){	// try문의 응답이 resData에 저장된다. resData = {"insertResult: 1"}
				if(resData.insertResult === 1){
					alert('신규 회원이 등록되었습니다.');
					fnInit();			// 입력란 초기화
					fnGetAllMember();	// 새로운 회원 목록으로 갱신한다.
				} else {
					alert('신규 회원 등록이 실패했습니다.');
				}
			},							// 예외가 오면 에러 응답 코드도 같이온다.
			error: function(jqXHR) {	// jqXHR 객체에는 예외코드(응답코드:404,500 등)와 예오이메시지 등이 저장된다.
										// catch문의 응답 코드는 jqXHR 객체의 status 속성에 저장된다.
										// catch문의 응답 메시지는 jqXHR 객체의 responseText 속성에 전달된다.
				alert(jqXHR.responseText + '(' + jqXHR.status + ')'); // responseText == addService의 예외 msg를 말함.
			}		
		})
	}
	
	// onclick="fnGetMember(element.memberNo)" 번호를 아는 곳에서 호출 해준 것이다.
	// fnGetMember() 함수를 호출할 때 회원번호(element.memberNo)를 인수로 전달하면 매개변수 memberNo가 받는다.
	function fnGetMember(memberNo){ 		// memberNo == 변수
		$.ajax({
			// 요청
			type: 'get',
			url: '${contextPath}/detail.do',
			data: 'memberNo=' + memberNo,
			// 응답
			dataType: 'json',
			success: function(resData){	// resData = {"member": {"memberNo":회원번호, ...}}
				alert('회원 정보가 조회되었습니다.');
				$('#id').val(resData.member.id).prop('disabled', true);
				$('#name').val(resData.member.name);
				$(':radio[name=gender][value=' + resData.member.gender + ']').prop('checked', true);
				$('#address').val(resData.member.address);
				$('#memberNo').val(resData.member.memberNo);	// <input type="hidden">에 저장하는 값 hidden은 눈에보이지 않지만 form에 넣어놓은 것
			}
			
		})		
	}
	
	function fnModifyMember(){
		$.ajax({
			// 요청
			type: 'post',
			url: '${contextPath}/modify.do',
			data: $('#frm_member').serialize(), 		// form 전체를 보냈기 때문에 파라미터가 많이 간다.
			// 응답
			dataType: 'json',
			success: function(resData){	// resData = {"updateResult": 1}
				if(resData.updateResult === 1){
					alert('회원 정보가 수정되었습니다.');
					fnGetAllMember();	// 새로운 회원 정보로 갱신
				} else {
					alert('회원 정보 수정이 실패했습니다.');
				}
			}
		})
	}
	
	function fnRemoveMember(){
		if(confirm('회원 정보를 삭제할까요?') == false) {
			alert('취소되었습니다.');
			return;
		}
		$.ajax({
			// 요청
			type: 'get',
			url: '${contextPath}/remove.do',
			data: 'memberNo=' + $('#memberNo').val(),
			// 응답
			dataType: 'json',
			success: function(resData){  // resData = {"deleteResult": 1}
				if(resData.deleteResult === 1) {
					alert('회원 정보가 삭제되었습니다.');
					fnGetAllMember();   // 새로운 회원 목록으로 갱신
				} else {
					alert('회원 정보 삭제가 실패했습니다.');
				}
			}
		})
	}
	
		
</script>

</body>
</html>