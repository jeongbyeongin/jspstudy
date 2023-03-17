package ex02_urlmapping;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	URLMapping 수정하는 방법
	
	방법1. 서블릿을 열고 @WepServlet 애너테이션을 수정한다.
	
	방법2. web.xml을 열고 <servlet> 태그와 <servlet-mapping> 태그를 추가한다.
 */


@WebServlet("/HiServlet")		// @WebServlet({"/hi", "/hello"})처럼 2개 이상의 URLMapping 지정이 가능하다.

public class HiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
