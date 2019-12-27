package service;

import java.sql.*;
import java.util.*;
import dao.*;
import vo.*;

public class ReportService {
	
	//게임이 종료되면 자동으로 기록 insert하는 service
	public void insertReport(Report report) {
		//System.out.println(report.getMember().getMemberId());
		//System.out.println(report.getCount());
		//System.out.println(report.getTimer());
		Connection conn = null;
		try {
			DBService dbService = new DBService();
			ReportDao reportDao = new ReportDao();
			conn = dbService.getConnection();
			conn.setAutoCommit(false);
			
			reportDao.insertReport(report, conn);
			
			conn.commit();
		}
		catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	//index의 랭킹을 출력해주는 service
	public List<Report> selectRankList(Report report){
		List<Report> list =null;
		Connection conn = null;
		//System.out.println("service : "+report.getMember().getMemberId());
		try {
			list = new ArrayList<Report>();
			DBService dbService = new DBService();
			conn = dbService.getConnection();
			ReportDao reportDao = new ReportDao();
			conn.setAutoCommit(false);
			
			List<Report> today = new ArrayList<Report>();
			List<Report> tomonth = new ArrayList<Report>();
			List<Report> top = new ArrayList<Report>();
			List<Report> myRank	= new ArrayList<Report>();
			
			today = reportDao.todayRank(conn);
			tomonth = reportDao.tomonthRank(conn);
			top = reportDao.topRank(conn);
			myRank = reportDao.myRank(conn, report);
			
			if(today.size() <= 10) {
				for(Report day : today) {
					list.add(day);
				}
				for(int i=today.size(); i<10; i++) {
					list.add(null);
				}
			}
			if(tomonth.size() <= 10) {
				for(Report month : tomonth) {
					list.add(month);
				}
				for(int i=tomonth.size(); i<10; i++) {
					list.add(null);
				}
			}
			if(top.size() <= 10) {
				for(Report t : top) {
					list.add(t);
				}
				for(int i=top.size(); i<10; i++) {
					list.add(null);
				}
			}
			if(myRank.size() <= 10) {
				for(Report my : myRank) {
					list.add(my);
				}
				for(int i=myRank.size(); i<10; i++) {
					list.add(null);
				}
			}
			conn.commit();
		}
		catch(Exception e ) {
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
		return list;
	}
}
