package ex09_binding;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BindingServlet2")

public class BindingServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// HttpServletRequest에 저장된 속성 확인
		System.out.println(request.getAttribute("a"));		// int 타입 맞춰준 것
		
		// HttpSession에 저장된 속성 확인
		//서블릿1에서 세션에 어트리뷰트 저장해서 전달하고 서블릿2에서는 그 요청에 들어있는 세션에서 어트리뷰트 가져오는 것
		System.out.println((int)request.getSession().getAttribute("a")); 
		
		
		// ServletContext에 저장된 속성 확인
		System.out.println((int)request.getServletContext().getAttribute("a"));
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
