package ex11_upload_download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadServelt")

public class DownloadServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 요청 파라미터
		request.setCharacterEncoding("UTF-8");
		String path = URLDecoder.decode(request.getParameter("path"), "UTF-8");
		
		// 다운로드 해야 할 File 객체	( 파일을 읽어서 보내야 한다 . read 먼저 그 다음 writer)
		File file = new File(path);
		
		// 다운로드 해야 할 파일을 읽어들일 입력 스트림
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		
		// 다운로드용 응답 헤더 작업 (응답하면 사용자가 가져간다.)
		response.setHeader("Content-Disposition", "attachment; filename=" + path.substring(path.lastIndexOf("\\") + 1)); // 없는 상태로 저장하기 하면 경로이름으로 저장되는데 파일명으로 저장하고 싶어서 path.substring(path.lastIndexOf("\\") + 1)를 넣었다. 
		response.setHeader("Content-Length", file.length() + "");  // 헤더는 int값을 요구해서 length를 빈문자열로 더해서 String으로 변환
		
		// 응답 스트림(출력 스트림)
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		
		// 파일 복사 (in에서 1024바이트 단위로 읽은 다음 out으로 보내기)
		byte[] b = new byte[1024];		// 입력 단위
		int readByte = 0;				// 실제로 읽은 바이트
		while((readByte = in.read(b)) != -1) {		// in.read(b) = 1024단위로 읽을 것
			out.write(b, 0, readByte); // 인덱스 0부터 몇개 출력하겠다
		}
		
		out.close();
		in.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
