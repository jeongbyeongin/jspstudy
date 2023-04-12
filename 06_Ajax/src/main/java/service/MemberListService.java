package service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDAO;

public class MemberListService implements IMemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 하나의 서비스는 여러 개의 DAO 메소드를 호출할 수 있다. (DAO에 있는 목록과, 전체 회원 수)
		// 두번 호출 할 것이라서 dao를 미리 구해놓고
		MemberDAO dao = MemberDAO.getInstance();
		List<Member> memberList =  dao.selectAllMembers();
		int memberCount = dao.getMemberCount();
		
		// 응답할 JSON 데이터 만들기 (자바스크립트 객체로 만들겠다.) 이런식으로 JSON 데이터로 만들어서 반환을 하겠다.
		// lib가 알아서 해줄것이다 밑에있는 것 처럼.
		/*
			{
				"memberCount": 2,
				"memberList": [			각회원들의 실제 데이터확인 [배열]
					{					하나의 객체
						"memberNo": 1,
						"id": "회원아이디",
						"name": "회원명",
						"gender": "회원성별",
						"address": "회원주소
					},
					{
						"memberNo": 1,
						"id": "회원아이디",
						"name": "회원명",
						"gender": "회원성별",
						"address": "회원주소
					}
				]
			}
		*/
				
		JSONObject obj = new JSONObject();
		obj.put("memberCount", memberCount); 			// 저장할 때 쓰는 메소드 put (프로퍼티, 값)
		obj.put("memberList", memberList);							// JSON 라이브러리가 Java의 ArrayList를 JavaScript 배열([])로 바꾸고, Java의 Member 객체를 JavaScript의 객체({})로 바꾼다.( 스스로 위에 보이는 것 처럼 만들어 줄 것이다.)
		
		// 응답 (요청한 곳으로 그대로 응답된다. 즉 ajax() 메소드로 응답 처리된다.)
		response.setContentType("application/json; charset=UTF-8"); 		// 응답은 JSON이다. 그렇기 때문에 어플리케이션/제이슨 
		PrintWriter out = response.getWriter();
		out.println(obj.toString());	// JSON 데이터를 텍스트 형식으로 변경해서 반환하기 (서블릿 1장 참고)
		out.flush();
		out.close();
		
	} 

}
