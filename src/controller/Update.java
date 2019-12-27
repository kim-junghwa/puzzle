package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.MemberService;
import vo.*;

@WebServlet("/Update")
public class Update extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		Member member = new Member();
		member.setMemberId(request.getParameter("memberId"));
		member.setMemberPw(request.getParameter("memberPw"));
		member.setMemberLevel(request.getParameter("newMemberPw"));
		MemberService memberService = new MemberService();
		int val = memberService.updateMember(member);
		
		
		Gson gson = new Gson();
		String json = gson.toJson(val);
		//System.out.println(json);
		response.getWriter().write(json);
	}

}
