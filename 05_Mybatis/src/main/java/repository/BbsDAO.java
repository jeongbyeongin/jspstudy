package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.BbsDTO;

public class BbsDAO {

	// 필드
	SqlSessionFactory factory;
	
	//Singleton Pattern
	 private static BbsDAO dao = new BbsDAO();
	 private BbsDAO() {
		 try{
			 
			 String resource = "mybatis/config/mybatis-config.xml";
			 InputStream inputStream = Resources.getResourceAsStream(resource);
			 factory = new SqlSessionFactoryBuilder().build(inputStream);
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
	 }
	public static BbsDAO getInstance() {
		return dao;
	}
	
	/* 메소드명과 쿼리문의 id를 맞추자. */
	
	// mapper의 namespace (쿼리문을 불러올 때 계속 사용되어 변수처리)
	private final String NS = "mybatis.mapper.bbs.";
	
	// 1. 목록
	public List<BbsDTO> selectAllBbsList(){
		SqlSession ss = factory.openSession();
		// 바로 객체에 담지 않고 return 하면 close()를 못함
		List<BbsDTO> bbsList = ss.selectList(NS + "selectAllBbsList");
		ss.close();
		return bbsList;
	}
	
	// 2. 상세
	public BbsDTO selectBbsByNo(int bbsNo) {
		SqlSession ss = factory.openSession();
		BbsDTO bbs = ss.selectOne(NS + "selectBbsByNo", bbsNo);
		ss.close();
		return bbs;
	}
	
	// 3. 삽입
	public int insertBbs(BbsDTO bbs) {
		SqlSession ss= factory.openSession(false); // autoCommit을 하지 않고, 직접 commit 할거다.
		int insertResult = ss.insert(NS + "insertBbs", bbs);
		if(insertResult == 1) { // 삽입 성공 시 commit
			ss.commit();
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
