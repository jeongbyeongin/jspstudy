package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.BbsDTO;

public class BbsDAO {
	
	// field(필드)
	private SqlSessionFactory factory;
	
	// Singleton Pattern
	private static BbsDAO dao = new BbsDAO();
	private BbsDAO() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";  			// 우리 경로로 바꿔줌
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static BbsDAO getInstance() {
		return dao;
	}
	
	/* 메소드명과 쿼리문의 id를 맞추자. */
	
	// mapper의 namespace
	private final String NS = "mybatis.mapper.bbs.";
	
	// 1. 목록	// 반환타입 ArrayList
	public List<BbsDTO> selectAllBbsList() {
		SqlSession ss = factory.openSession();									// 쿼리문 실행할 SqlSession 가져오기
		List<BbsDTO> bbsList = ss.selectList(NS + "selectAllBbsList");			// 목록셀렉트 가져오기 // bbs.xml에 있는 "selectAllBbsList"를 돌릴 것이다.
		ss.close();																// 클로즈 시키고
		return bbsList;															// 반환한다.
	}
	
	// 2. 상세
	public BbsDTO selectBbsByNo(int bbsNo) {	// bbsNo가 와야 작동함
		SqlSession ss = factory.openSession();
		BbsDTO bbs = ss.selectOne(NS + "selectBbsByNo", bbsNo);					// 쿼리문으로 bbsNo 전달하기.
		ss.close();
		return bbs;
	}
	
	// 3. 삽입
	public int insertBbs(BbsDTO bbs) {						// #{title}, #{content} == BbsDTO 
		SqlSession ss = factory.openSession(false);			// autoCommit을 하지 않고, 직접 commit하겠다
		int insertResult = ss.insert(NS + "insertBbs", bbs);
		if(insertResult == 1) {		//삽입 성공 시 
			ss.commit();			// commit 하시오.
		}
		ss.close();
		return insertResult;
	}
	
	// 4. 수정
	public int updateBbs(BbsDTO bbs) {
		SqlSession ss = factory.openSession(false);
		int updateResult = ss.update(NS + "updateBbs", bbs); 
		if(updateResult == 1) {
			ss.commit();
		}
		ss.close();
		return updateResult;
	}
	
	// 5. 삭제
	public int deleteBbs(int bbsNo) {
		SqlSession ss = factory.openSession(false);
		int deleteResult = ss.delete(NS + "deleteBbs", bbsNo);
		if(deleteResult == 1) {
			ss.commit();
		}
		ss.close();
		return deleteResult;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
