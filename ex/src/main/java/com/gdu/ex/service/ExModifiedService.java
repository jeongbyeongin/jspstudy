package com.gdu.ex.service;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.ex.common.ActionForward;
import com.gdu.ex.domain.ExDto;
import com.gdu.ex.repository.ExDao;

public class ExModifiedService implements ExService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		String exContent = request.getParameter("exContent");
		int exNo = Integer.parseInt(request.getParameter("exNo"));
		
		System.out.println(exContent);
		
		ExDto exdto = new ExDto();
		exdto.setExContent(exContent);
		exdto.setExNo(exNo);
		
		int result = ExDao.getInstance().update(exdto);
		
		return new ActionForward(request.getContextPath() + "/detail.do?exNo=" + exNo, true);
	}

}
