package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDAO;

public class BoardRemoveService implements IBoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 주소창에 삭제 주소를 입력해서 삭제를 시도하는 경우 잘못된 요청으로 처리한다.
		try {
			
			if(request.getMethod().equalsIgnoreCase("get")) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 요청입니다.')");
				out.println("history.back()");	// 뒤로가기 버튼 history.back()
				out.println("</script>");
				out.flush();
				out.close();
				return null;	// 컨트롤러로 null값을 반환하면 컨트롤러는 이동(redirect 또는 forward)을 하지 않는다.
								// 서비스에서 직접 이동하는 경우에 이 방법을 사용한다.
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} 
		
		// 1. 요청 파라미터
		String strBoard_no = request.getParameter("board_no");
		int board_no = Integer.parseInt(strBoard_no.isEmpty() ? "0" : strBoard_no); // 공백 처리 : (list.jsp에)폼 안에 인풋으로 내장된 경우에 널값으로 전달되지 않음 왜냐면 네임이 있으니까. 
																					// 받는 측에서는 무조건 있는 것. 
																					// 값이 입력되어있지 않을 수 있어서 그거를 빈문자열로 인식할 수 있게 하는 것.  // 
		// 2. BoardDAO의 deleteBoard 메소드 호출
		int deleteResult = BoardDAO.getInstance().deleteBoard(board_no);
		System.out.println(deleteResult == 1 ? "삭제성공" : "삭제실패");
		
		// 3. 어디로 and 어떻게 이동할 것인가.
		return new ActionForward(request.getContextPath() + "/getAllBoardList.do", true);
	}

}
