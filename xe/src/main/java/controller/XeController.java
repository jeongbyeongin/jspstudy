package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.IXeService;
import service.XeListService;


@WebServlet("*.do")
public class XeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		IXeService service = null;
		
		ActionForward af = null;
		
		switch(urlMapping) {
		case "/list.do":
			service = new XeListService();
			break;
		}
		if(service != null) {
			af = service.execute(request, response);
		}
		if(af.isRedirect()) {
			response.sendRedirect(af.getPath());
		} else {
			request.getRequestDispatcher(af.getPath()).forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
