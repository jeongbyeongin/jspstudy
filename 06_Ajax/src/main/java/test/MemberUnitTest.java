package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Member;
import repository.MemberDAO;

public class MemberUnitTest {

	@BeforeClass	// MemberUnitTest 클래스 (테스트 파일) 실행 이전에 한 번 먼저 실행된다. (실행되려면 static 처리가 되어 있어야 한다.)
	public static void 삽입테스트() {
		Member member = new Member(0, "admin", "관리자", "M", "seoul"); 	// 원래는 (1, "admin", "관리자", "M", "seoul"); 이건데 1은 시퀀스가 해주니 그냥 0처리
		assertEquals(1, MemberDAO.getInstance().insertMember(member));
	}
	
	@Test
	public void 목록테스트() {
		assertEquals(1, MemberDAO.getInstance().selectAllMembers().size());
	}
	
	@Test
	public void 상세테스트() {
		Member member = new Member(1, "admin", "관리자", "M", "seoul");
		assertEquals(member, MemberDAO.getInstance().selectMemberByNo(1));		// 정상적으로 삽입 되었다면 1번
	}
	
	@Test
	public void 수정테스트() {
		Member member = new Member(1, null, "new관리자", "F", "newseoul");
		assertEquals(1, MemberDAO.getInstance().updateMember(member));
	}
	
	@AfterClass		// MemberUnitTest 클래스 (테스트 파일) 실행 이후에 한 번 먼저 실행된다. (실행되려면 static 처리가 되어 있어야 한다.)
	public static void 삭제테스트() {
		assertEquals(1, MemberDAO.getInstance().deleteMember(1));
	}
	
}
