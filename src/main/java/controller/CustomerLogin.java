package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustDao;
import dto.Customer;

@SuppressWarnings("serial")
@WebServlet("/Customer_login")
public class CustomerLogin extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int custid= Integer.parseInt(req.getParameter("custid"));
		String pwd= req.getParameter("pwd");	
		
		CustDao custDao= new CustDao();
		Customer cInfo= custDao.login(custid);
		
		if(cInfo==null) {
			resp.getWriter().print("<h1>Entered Invalid customer id.</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}
		else {
			if(cInfo.getPwd().equals(pwd)) {
				resp.getWriter().print("<h1>You have Login Successfully.</h1>");
				
				//Session Tracking
				req.getSession().setAttribute("customer", cInfo);
				req.getRequestDispatcher("customer_Home.html").include(req, resp);
			}
			else {
				resp.getWriter().print("<h1>Entered Invalid password.</h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
			}
		}
	}
}
