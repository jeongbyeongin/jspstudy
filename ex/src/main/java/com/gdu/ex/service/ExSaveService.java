package com.gdu.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.ex.common.ActionForward;
import com.gdu.ex.domain.ExDto;
import com.gdu.ex.repository.ExDao;

public class ExSaveService implements ExService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String exContent = request.getParameter("exContent");		// 사용자가 입력한 값
		
		ExDto ex = new ExDto();										// db로 불러서 
		ex.setExContent(exContent);									// dto로 보낸 것.
		
		int result = ExDao.getInstance().save(ex);				// 다오를 불러서 세이브를 호출해서 만든 ExDto를 반납
		
		// Insert 이후에는 redirect 하자!						// save의 호출 결과는 int이다
		// redirect 경로 : mapping을 작성한다. ContextPath부터 시작하는 경로로 작성한다.
		return new ActionForward(request.getContextPath() + "/list.do", true);				// insert 후에는 목록보기로 mapping 후에는 list.do이다.
		
	}

}
