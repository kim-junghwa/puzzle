package dao;

import vo.*;
import java.sql.*;

public class MemberDao {
	/*
	 * SELECT member_id FROM member WHERE member_id=? AND member_pw=? AND member_level='Y' 
	 */
	public Member login(Member member, Connection conn) throws Exception {
		Member returnMember = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id FROM member WHERE member_id=? AND member_pw=? AND member_level='Y'";
		try {
			returnMember = new Member();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember.setMemberId(rs.getString("member_id"));
			}
		}
		finally {
			try {
				stmt.close();
				rs.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return returnMember;
	}
	
	/*
	 * INSERT INTO member(member_id, member_pw, member_level) values(?,?,'Y')
	 */
	public void insertMember(Member member, Connection conn) throws Exception {
		//System.out.println(member.getMemberId());
		//System.out.println(member.getMemberPw());
		//System.out.println(conn);
		PreparedStatement stmt = null;
		String sql = "INSERT INTO member(member_id, member_pw, member_level) values(?,?,'Y')";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			stmt.executeUpdate();
		}
		finally {
			stmt.close();
		}
	}
	
	/*
	 * UPDATE member
	 * SET member_pw = ?
	 * WHERE member_id = ? AND member_pw = ?
	 */
	public int updateMember(Member member, Connection conn) throws Exception {
		int val = 0;
		PreparedStatement stmt = null;
		String sql = "UPDATE member " + 
					 "SET member_pw = ? " + 
					 "WHERE member_id = ? AND member_pw = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberLevel());
			stmt.setString(2, member.getMemberId());
			stmt.setString(3, member.getMemberPw());
			val = stmt.executeUpdate();
			//System.out.println(val);
		}
		finally {
			stmt.close();
		}
		
		return val;
	}
	
	/*
	 * DELETE FROM member
	 * WHERE member_id = ? AND member_pw = ?
	 */
	public int deleteMember(Member member, Connection conn) throws Exception {
		int val = 0;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM member " + 
					 "WHERE member_id = ? AND member_pw = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			val = stmt.executeUpdate();
		}
		finally {
			stmt.close();
		}
		return val;
	}
}
