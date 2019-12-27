package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.*;
import vo.*;

@WebServlet("/AddReport")
public class AddReport extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		Report report = new Report();
		report.setMember(new Member());
		report.getMember().setMemberId(request.getParameter("memberId"));
		report.setCount(Integer.parseInt(request.getParameter("count")));
		report.setTimer(Integer.parseInt(request.getParameter("timer")));
		
		//System.out.println(report.getMember().getMemberId());
		//System.out.println(report.getCount());
		//System.out.println(report.getTimer());
		
		ReportService reportService = new ReportService();
		reportService.insertReport(report);
	}

}
