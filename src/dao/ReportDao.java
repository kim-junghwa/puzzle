package dao;

import java.sql.*;
import java.util.*;
import vo.*;

public class ReportDao {
	/*
	 * 게임이 종료되면 자동으로 기록을 insert하는 dao 메소드
	 * INSERT INTO report(member_id, report_date, count, timer) VALUES(?,now(),?,?)
	 */
	public void insertReport(Report report, Connection conn) throws Exception {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO report(member_id, report_date, count, timer) VALUES(?,now(),?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, report.getMember().getMemberId());
			stmt.setInt(2, report.getCount());
			stmt.setInt(3, report.getTimer());
			stmt.executeUpdate();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/*
	 * 오늘의 랭킹을 리턴하는 dao메소드
	 */
	public List<Report> todayRank(Connection conn) throws Exception {
		List<Report> list = null;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM today_rank";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			list = new ArrayList<Report>();
			Report report = null;
			while (rs.next()) {
				report = new Report();
				report.setMember(new Member());
				report.getMember().setMemberId(rs.getString("member_id"));
				report.setCount(rs.getInt("count"));
				report.setTimer(rs.getInt("timer"));
				list.add(report);
			}
		}
		finally {
			stmt.close();
			rs.close();
		}
		return list;
	}
	
	/*
	 * 이달의 랭킹을 리턴하는 dao메소드
	 */
	public List<Report> tomonthRank(Connection conn) throws Exception {
		List<Report> list = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM tomonth_rank";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			list = new ArrayList<Report>();
			Report report = null;
			while(rs.next()) {
				report = new Report();
				report.setMember(new Member());
				report.getMember().setMemberId(rs.getString("member_id"));
				report.setCount(rs.getInt("count"));
				report.setTimer(rs.getInt("timer"));
				list.add(report);
			}
		}
		finally {
			stmt.close();
			rs.close();
		}
		return list;
	}
	
	/*
	 * 모든 top10을 리턴하는 dao메소드
	 */
	public List<Report> topRank(Connection conn) throws Exception {
		List<Report> list = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM top_rank";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			list = new ArrayList<Report>();
			Report report = null;
			while(rs.next()) {
				report = new Report();
				report.setMember(new Member());
				report.getMember().setMemberId(rs.getString("member_id"));
				report.setCount(rs.getInt("count"));
				report.setTimer(rs.getInt("timer"));
				list.add(report);
			}
		}
		finally {
			stmt.close();
			rs.close();
		}
		return list;
	}
	/*
	 * 지금까지의 로그인한 사람의 기록을 리턴하는  dao 메소드
	 * SELECT member_id, count, timer
	 * FROM report 
	 * WHERE member_id = ?  
	 * ORDER BY count ASC, timer ASC 
	 * LIMIT 10
	 */
	public List<Report> myRank(Connection conn, Report report) throws Exception {
		List<Report> list = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id, count, timer " + 
					 "FROM report " + 
					 "WHERE member_id = ? " + 
					 "ORDER BY count ASC, timer ASC " + 
					 "LIMIT 10";
		//System.out.println(report.getMember().getMemberId());
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, report.getMember().getMemberId());
			rs = stmt.executeQuery();
			list = new ArrayList<Report>();
			
			Report report2 = null;
			while(rs.next()) {
				report2 = new Report();
				report2.setMember(new Member());
				report2.getMember().setMemberId(rs.getString("member_id"));
				report2.setCount(rs.getInt("count"));
				report2.setTimer(rs.getInt("timer"));
				list.add(report2);
			}
		}
		finally {
			stmt.close();
			rs.close();
		}
		return list;
	}
}
