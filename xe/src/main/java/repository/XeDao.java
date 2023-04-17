package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.XeDto;

public class XeDao {
	
	SqlSessionFactory factory;
	
	private static XeDao dao = new XeDao();
	private XeDao() {
		try{
			 
			 String resource = "config/mybatis-config.xml"; 
			 InputStream inputStream = Resources.getResourceAsStream(resource);
			 factory = new SqlSessionFactoryBuilder().build(inputStream);
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
	 }
	public static XeDao getInstance() {
		return dao;
	}
	
	private final String NS = "repository.xe.";
	
	public List<XeDto> list(){
		SqlSession ss = factory.openSession();
		List<XeDto> result = ss.selectList(NS + "list");
		
		ss.close();
		return result;
	}
	
	}


