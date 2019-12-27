package service;

import java.sql.*;
import java.util.Random;
import dao.*;
import vo.*;

public class MemberService {
	public String login(Member member) {
		Connection conn = null;
		Member returnMember = null;
		try {
			DBService dbService = new DBService();
			MemberDao memberDao = new MemberDao();
			returnMember = new Member();
			conn = dbService.getConnection();
			conn.setAutoCommit(false);
			
			// befor
			
			returnMember = memberDao.login(member, conn);
			//System.out.println(returnMember);
			// after
			
			conn.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} 
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return returnMember.getMemberId();
	}
	
	public void insertMember(Member member) {
		Connection conn = null;
		try {
			MemberDao memberDao = new MemberDao();
			DBService dbService = new DBService();
			conn = dbService.getConnection();
			conn.setAutoCommit(false);
			Random rnd = new Random();
			String randomStr = "";
			for(int i=0; i<5; i++) {
				randomStr += String.valueOf((char) ((int) (rnd.nextInt(26)) + 97));
			}
			for(int i=0; i<2; i++) {
				randomStr += String.valueOf(rnd.nextInt(10));
			}
			
			System.out.println("join random String : "+randomStr);
			member.setRand(randomStr);
			
			memberDao.insertMember(member, conn);
			
			conn.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int updateMember(Member member) {
		int val = 0;
		Connection conn = null;
		try {
			DBService dbService = new DBService();
			MemberDao memberDao = new MemberDao();
			conn = dbService.getConnection();
			conn.setAutoCommit(false);
			
			val = memberDao.updateMember(member, conn);
			
			conn.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return val;
	}
	
	public int deleteMember(Member member) {
		int val = 0;
		Connection conn = null;
		try {
			DBService dbService = new DBService();
			MemberDao memberDao = new MemberDao();
			conn = dbService.getConnection();
			conn.setAutoCommit(false);
			
			val = memberDao.deleteMember(member, conn);
			
			conn.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return val;
	}
}
