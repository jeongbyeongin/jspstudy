package repository;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Member;

public class MemberDAO {
	
	private SqlSessionFactory factory;
	
	private static MemberDAO dao = new MemberDAO();
	private MemberDAO() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static MemberDAO getInstance() {
		return dao;
	}
	
	private final String MAPPER = "mybatis.mapper.member.";
	
	public Member login(Member member) {
		SqlSession ss = factory.openSession();
		Member login = ss.selectOne(MAPPER + "login", member);
		ss.close();
		return login;
	}

}
