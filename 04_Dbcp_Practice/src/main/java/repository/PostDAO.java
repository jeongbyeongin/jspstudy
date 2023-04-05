package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import domain.PostVO;

public class PostDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql = "";  // 하려면 모두 += 으로 작업 할 수있다.
	
	private DataSource dataSource;  // CP 커넥션 풀 관리하는 아이
	
	private static PostDAO dao = new PostDAO();
	private PostDAO() {	
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle11g");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static PostDAO getInstance() {
		return dao;
	}
	
	public void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 목록보기는 무조건 ArrayList 사용
	public List<PostVO> getAllPosts() throws Exception {
		List<PostVO> posts = new ArrayList<PostVO>();
		con = dataSource.getConnection();
		sql =  "SELECT POST_NO, WRITER, TITLE, CONTENT, IP, MODIFIED_DATE, CREATED_DATE";
		sql += "  FROM POST";
		sql += " ORDER BY POST_NO DESC";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery(); // 실행
		while(rs.next()) {	// 결과가 있으면 해당결과를 하나씩 꺼내서 사용하겠다.	// 객체를 만들어서 집어넣기
			PostVO post = PostVO.builder()		// 한줄에 하나씩 빌드를 시작한다.
							.post_no(rs.getInt(1))	        // setPost_no(rs.getInt(1))과 같은 역할	// POST_NO, WRITER, TITLE, CONTENT, IP, MODIFIED_DATE, CREATED_DATE 순차적으로 1열 2열 3열 이런식으로 불러온다.
							.writer(rs.getString(2))		// 세터를 하나씩 불러오는거다.
							.title(rs.getString(3))
							.content(rs.getString(4))
							.ip(rs.getString(5))
							.modified_date(rs.getDate(6))
							.created_date(rs.getDate(7))
							.build();						// build를 사용하려면 @builder가 있어야한다.
			posts.add(post);
		}
		close();
		return posts;
	}
	
	// 저장하기
	public int savePost(PostVO post) throws Exception {
		con = dataSource.getConnection();
		sql = "INSERT INTO POST(POST_NO, WRITER, TITLE, CONTENT, IP, MODIFIED_DATE, CREATED_DATE)";
		sql += " VALUES(POST_SEQ.NEXTVAL, ?, ?, ?, ?, NULL, SYSDATE)";
		ps = con.prepareStatement(sql);
		ps.setString(1, post.getWriter());
		ps.setString(2, post.getTitle());
		ps.setString(3, post.getContent());
		ps.setString(4, post.getIp());
		int saveResult = ps.executeUpdate();
		close();
		return saveResult;
	}
	
	public PostVO getPostByNo(int post_no) throws Exception {
		PostVO post = null;
		con = dataSource.getConnection();
		sql =  "SELECT POST_NO, WRITER, TITLE, CONTENT, IP, MODIFIED_DATE, CREATED_DATE";
		sql += "  FROM POST";
		sql += " WHERE POST_NO = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, post_no);
		rs = ps.executeQuery();
		if(rs.next()) {
			post = PostVO.builder()
					.post_no(post_no)
					.writer(rs.getString(2))
					.title(rs.getString(3))
					.content(rs.getString(4))
					.ip(rs.getString(5))
					.modified_date(rs.getDate(6))
					.created_date(rs.getDate(7))
					.build();
		}
		close();
		return post;
	}
	
	public int updatePost(PostVO post) throws Exception {		// WRITER, IP, CREATED_DATE는 바꾸지 못한다
		con = dataSource.getConnection();
		sql  = "UPDATE POST";
		sql += "   SET TITLE = ?, CONTENT = ?, MODIFIED_DATE = SYSDATE";
		sql += " WHERE POST_NO = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, post.getTitle());
		ps.setString(2, post.getContent());
		ps.setInt(3, post.getPost_no());
		int updateResult = ps.executeUpdate();
		clone();
		return updateResult;
		
	}
	
	public int deletePost(int post_no) throws Exception {
		con = dataSource.getConnection();
		sql = "DELETE FROM POST WHERE POST_NO = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, post_no);
		int delteteResult = ps.executeUpdate();
		close();
		return delteteResult;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
