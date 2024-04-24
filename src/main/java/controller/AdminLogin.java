package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BankDao;
import dto.BankAccount;

@SuppressWarnings("serial")
@WebServlet("/Admin_login")
public class AdminLogin extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String un= req.getParameter("un");
		String pwd= req.getParameter("pwd");
		
		if(un.equals("admin") && pwd.equals("Admin@123")) {
//			resp.getWriter().print("<h1>Admin Login Successfully</h1>");
			
			BankDao bankDao=new BankDao();
			// Session Tracking
			List<BankAccount> acc_list= bankDao.fetchAll();
			req.getSession().setAttribute("acc_list", acc_list);
			req.getRequestDispatcher("adminHome.jsp").include(req, resp);
		}
		else {
			resp.getWriter().print("<h1>Wrong username or password</h1>");
		}
	}
}