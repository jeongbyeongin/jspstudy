package ex03_parameter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PostServlet")

public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public PostServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		request.setCharacterEncoding("UTF-8");
		
		String model = request.getParameter("model");
		String strPrice = request.getParameter("price");
		
		// <form> 태그에 포함된 입력 요소들이 name 속성을 가지고 있다면, null 처리를 할 수 없다. 빈 문자열("")로 처리해야한다.
		// get 방식으로 제출할 때는 입력값이 없으면 null 로 보내지는데 post 방식은 이미 파라미터가 있는 순간 (value가 없어도) null 이 아니라 빈문자열이 전달된걸로 인식해서
		// get일때는 null처리 post일때는 빈문자열처리 해줘야한다.

		
		int price = 0;
		if(strPrice.isEmpty() == false) {		// 빈 문자열 점검 // 빈 문자열이 아니면 if(str == null || str.isEmpty());
			price = Integer.parseInt(strPrice);
		}
		
		response.getWriter().append("model: " + model).append("price: " + price);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		System.out.println("POST 요청이 들어옴");
		doGet(request, response);
	}

}
