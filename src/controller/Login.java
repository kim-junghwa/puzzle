package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;
import service.*;
import vo.*;

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		MemberService memberService = new MemberService();
		Member member = new Member();
		member.setMemberId(request.getParameter("memberId"));
		member.setMemberPw(request.getParameter("memberPw"));
		
		//System.out.println("login memberId : " + member.getMemberId());
		//System.out.println("login memberPw : " + member.getMemberPw());
		
		String memberId = memberService.login(member);
		//System.out.println("session memberId :" + memberId);
		
		HttpSession session = request.getSession();
		session.setAttribute("sessionInfo", memberId);
		
		Gson gson = new Gson();
		String json = gson.toJson(memberId);
		response.getWriter().write(json);
		
	}

}
