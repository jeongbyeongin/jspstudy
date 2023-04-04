package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDAO;

public class BoardRemoveService implements IBoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 요청 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));
		int board_no = Integer.parseInt(opt.orElse("0")); // delete가 동작하지않게끔 만약에 게시글의 번호가 오지않으면 0처리를 하겠다.
		
		// 2. BoardDAO의 deleteBoard 메소드 호출
		int deleteResult = BoardDAO.getInstance().deleteBoard(board_no);
		System.out.println(deleteResult == 1 ? "삭제성공" : "삭제실패");
		
		// 3. 어디로 and 어떻게 이동할 것인가.
		return new ActionForward(request.getContextPath() + "/getAllBoardList.do", true);
	}

}
