package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IPostService;
import service.PostDeleteService;
import service.PostDetailService;
import service.PostListService;
import service.PostSaveService;

@WebServlet("*.post")	// /list.post  /detail.post  /save.post(저장하기)  /change.post(변경,수정)  /edit.post  /delete.post(삭제하기)

public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 요청, 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// urlMapping
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// (모든 서비스의) 서비스 타입 선언
		IPostService service = null;
		
		// forward 할 경로
		String path = null;
		
		// urlMapping에 따른 서비스 선택(생성) 선택 !!
		switch(urlMapping) {
		case "/list.post":
			service = new PostListService();
			break;
		case "/save.post":
			service = new PostSaveService();
			break;
		case "/write.post":
			path = "post/write.jsp";
			break;
		case "/detail.post":
			service = new PostDetailService();
			break;
		case "/delete.post":
			service = new PostDeleteService();
			break;
		}
	
		// 선택된 서비스 실행	실행 !!
		if(service != null) {
			try {
				// redirect가 필요한 서비스(삽입,수정,삭제)는 서비스 내에서 직접 redirect하고(location.href를 이용) path에 null을 반환한다.
				// 메시지를 띄우고 로케이션으로 이동하려는 것이다. 
				path = service.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		// 이동할 경로(path)로 forward		//  리다이렉트 이동을 원하지않는다.
		if(path != null) {
			request.getRequestDispatcher(path).forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
