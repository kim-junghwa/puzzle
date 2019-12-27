package controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.*;
import vo.*;

@WebServlet("/GetRank")
public class GetRank extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		ReportService reportService = new ReportService();
		List<Report> list = new ArrayList<Report>();
		Report report = new Report();
		report.setMember(new Member());
		report.getMember().setMemberId(request.getParameter("memberId"));
		list = reportService.selectRankList(report);
		
		//System.out.println(list);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		//System.out.println(json);
		response.getWriter().write(json);
	}

}
