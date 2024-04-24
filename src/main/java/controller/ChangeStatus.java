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
@WebServlet("/changeStatus")
public class ChangeStatus extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long acc_no= Long.parseLong(req.getParameter("acc_no"));
		
		BankDao bankDao=new BankDao();
		BankAccount acc_details=bankDao.fetch(acc_no);
		
		if(acc_details.isStatus())
			acc_details.setStatus(false);

		else
			acc_details.setStatus(true);
		
		bankDao.update(acc_details);
//		resp.getWriter().print("Status Got Updated in Database.");
		
		
		List<BankAccount> acc_list= bankDao.fetchAll();
		req.getSession().setAttribute("acc_list", acc_list);
		req.getRequestDispatcher("adminHome.jsp").include(req, resp);
	}
}
