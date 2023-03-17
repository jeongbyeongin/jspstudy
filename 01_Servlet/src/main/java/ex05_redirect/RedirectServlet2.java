package ex05_redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RedirectServlet2")

public class RedirectServlet2 extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 리다이렉트 이후(두 번째 요청) 파라미터확인 // 리다이렉트는 파라미터를 1번을 전달하고 2번을 할땐 전달해 주지않고 끊어진다.
		// 두 번째 요청 : 01_Servlet/RedirectServlet2
		String model = request.getParameter("model");	// 두 번째 요청에 파라미터 model이 없기 때문에 null 값이 저장된다.
		System.out.println("RedirectServlet2 : " + model);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
