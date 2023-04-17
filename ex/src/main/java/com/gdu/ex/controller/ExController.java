package com.gdu.ex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.ex.common.ActionForward;
import com.gdu.ex.service.ExDetailService;
import com.gdu.ex.service.ExListService;
import com.gdu.ex.service.ExModifiedService;
import com.gdu.ex.service.ExRemoveService;
import com.gdu.ex.service.ExSaveService;
import com.gdu.ex.service.ExService;

/**
 * Servlet implementation class ExController
 */
@WebServlet("*.do")
public class ExController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		// 이 작업은 urlMapping을 구하기 위해서 하는 것이다.
		String requestURI = request.getRequestURI();						// /ex/list.do
		String contextPath = request.getContextPath();						// /ex
		String urlMapping = requestURI.substring(contextPath.length());		// list.do
																			// contextPath의 길이는 3이다. substring(3)이면 인덱스 3부터 끝까지 길이.
		ExService service = null;
		
		ActionForward af = null;		// 목록을 보여줄 경로 실행, 결과가 String이기 때문에 exlistservice를 참조.
		
		switch(urlMapping) {
		case "/list.do":
			service = new ExListService();
			break;
		case "/detail.do":
			service = new ExDetailService();
			break;
		case "/write.do":
			// ex/write.jsp로 forward이동하기 (단순이동) - ActionForward 객체 만들기
			af = new ActionForward("ex/write.jsp", false);	// 객체 만들기 위해서 (forward할 때는 jsp이동이다 !)
			break;
		case "/save.do":
			service = new ExSaveService();
			break;
		case "/remove.do":
			service = new ExRemoveService();
			break;
		case "/modify.do":
			service = new ExModifiedService();
			break;
		
			
		}
		
													
				
		if(service != null) {
			af = service.execute(request, response);	// excontroller실행 결과를 받기 위해. path가 실행결과 받아오기.
		}
		
		if(af.isRedirect()) {							//if(af.isRedirect() == true) 이것인데 true는 제외해도된다.
			response.sendRedirect(af.getPath());
		} else {
			request.getRequestDispatcher(af.getPath()).forward(request, response);	// 여기서 이제 컨트롤러는 포워드를 해준다.
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
