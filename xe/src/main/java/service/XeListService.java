package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.XeDao;

public class XeListService implements IXeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("xeList", XeDao.getInstance().list());
		
		return new ActionForward("xe/list.jsp", false);
		
	}

}
