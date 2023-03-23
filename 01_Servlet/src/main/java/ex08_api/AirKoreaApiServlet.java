package ex08_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AirKoreaApiServlet")

public class AirKoreaApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 요청 파라미터
		String sidoName = request.getParameter("sidoName");			// 시도명
		String returnType = request.getParameter("returnType"); 	// 데이터표출방식
		
		// 서비스키	// 마이페이지 - > 인증키 복사(decoding)
		String serviceKey = "fj7ayv/XfydOpYCsuw3DdlQ/VfvzsriuCK8LrO3++OJai8PpBQ8LWg6v0it5vwdcrGM1nliz5OSGfCUSEvBCHw==";
		
		// 요청주소
		String apiURL = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		apiURL += "?serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8"); 		// 주소복사
		apiURL += "&sidoName=" + URLEncoder.encode(sidoName, "UTF-8");			// 한글로 되 있기때문에 URLEncoder.encode를 해준다
		apiURL += "&returnType=" + URLEncoder.encode(returnType, "UTF-8");		// 영어(영어는 할 필요가없다.)이지만 통일하기위해 해줬다.
		// + 를 너무많이 쓰면 성능이 떨어져서 stringbuilder로 성능을 좋게해주면 좋다.
	
		// URL
		URL url = new URL(apiURL);				// try catch를 해주지않을거기때문에 new를 사용한다
		
		// HttpURLConnection
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		
		// 요청 메소드
		con.setRequestMethod("GET"); // 대문자로 전달해야한다!!
		
		// returnType에 따른 Content-Type
		con.setRequestProperty("Content-Type", "application/" + returnType + "; charset=UTF-8");
		
		// 입력 스트림 생성
		BufferedReader reader = null;
		if(con.getResponseCode() == 200) {
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else {
			reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		
		// 입력 (API의 응답 결과를 StringBuilder에 저장)
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		
		// 입력 스트림, 접속 종료
		reader.close();
		con.disconnect();
		
		// API 결과를 ajax 응답 처리하기
		
		response.setContentType("application/" + returnType + "; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		out.flush();
		out.close();
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
