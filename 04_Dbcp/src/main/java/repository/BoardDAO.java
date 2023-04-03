package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.BoardDTO;

public class BoardDAO {
	
	// 모든 메소드가 사용할 공통 필드
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// Connection 관리를 위한 DataSource 필드
	private DataSource dataSource;
	
	// Single Pattern으로 DAO 생성하기
	private static BoardDAO dao = new BoardDAO();
	private BoardDAO() {
		// context.xml에서 <Resource name="jdbc/GDJ61" />인 Resource를 읽어서 DataSource 객체 생성하기 (JNDI 방식)
		try {
			Context context = new InitialContext();
			Context envContext = (Context)context.lookup("java:comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/GDJ61");
		/*
			위에 3줄이랑 같은 말
			Context context new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/GDJ61");
		*/
		
		} catch(NamingException e) {
			e.printStackTrace();
		}
		
	}
	public static BoardDAO getInstance() {
		return dao;
	}

	// 게시글 목록 반환하기
	public List<BoardDTO> selectBoardList() {
		return null;
	}
	
	// 게시글 반환하기
	public BoardDTO selectBoardByNo(int board_no) {
		
		return null;
		
	}
	
	// 게시글 삽입하기
	public int insertBoard(BoardDTO board) {
		
		return 0;
		
	}
	
	// 게시글 수정하기
	public int updateBoard(BoardDTO board) {
		
		return 0;
		
	}
	
	// 게시글 삭제하기
	public int deleteBoard(int board_no) {
		
		return 0;
		
	}
	
}
