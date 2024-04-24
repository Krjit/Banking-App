package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/setActiveAcc")
public class SetActiveAccount extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long acc_no = Long.parseLong(req.getParameter("accno"));
		
		req.getSession().setAttribute("acc_no", acc_no);
		req.getRequestDispatcher("accountHome.html").include(req, resp);
	}
}
