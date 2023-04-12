package com.gdu.ex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.ex.service.ExListService;
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
		
		String path = null;		// 목록을 보여줄 경로 실행, 결과가 String이기 때문에 exlistservice를 참조.
		
		switch(urlMapping) {
		case "/list.do":
			service = new ExListService();
			break;
		}
		
		path = service.execute(request, response);						// excontroller실행 결과를 받기 위해. path가 실행결과 받아오기.
														
		request.getRequestDispatcher(path).forward(request, response);	// 여기서 이제 컨트롤러는 포워드를 해준다.
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
