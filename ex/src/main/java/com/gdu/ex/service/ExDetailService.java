package com.gdu.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.ex.common.ActionForward;
import com.gdu.ex.domain.ExDto;
import com.gdu.ex.repository.ExDao;

public class ExDetailService implements ExService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 상세보기할 게시글의 번호 exNo
		int exNo = Integer.parseInt(request.getParameter("exNo"));
		
		// exNo값을 가진 레코드(행, 데이터)를 DB에서 가져온다.
		ExDto ex = ExDao.getInstance().detail(exNo);
		
		// 가져온 데이터를 request에 저장해서 응답 Jsp(detail.jsp)로 전달할 준비를 한다.
		request.setAttribute("ex", ex);
		
		// 응답 Jsp명을 반환한다.
		return new ActionForward ("ex/detail.jsp", false); // ex/detail.jsp로 forward하시오.
	}

}
