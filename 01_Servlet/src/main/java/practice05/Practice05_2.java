package practice05;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Practice05_2")

public class Practice05_2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
request.setCharacterEncoding("UTF-8");
		
		// 냉장고 sysout으로 출력
		String model = request.getParameter("model");
		System.out.println("Practice05_2 : " + model);
		
		System.out.println(request.getServletContext().getRealPath("practice05"));		// practice05의 실제 경로를 알려달라 webapp에있는
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
