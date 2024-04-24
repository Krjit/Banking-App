package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BankDao;
import dao.CustDao;
import dto.BankAccount;
import dto.Customer;

@SuppressWarnings("serial")
@WebServlet("/create_acc")
public class CreateBankAccount extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acc_type= req.getParameter("acc_type");
		Customer customer = (Customer) req.getSession().getAttribute("customer");
		
		List<BankAccount> list=customer.getBankAccounts();
		boolean flag =true;
		for (BankAccount bankAccount: list) {
			if(bankAccount.getAcc_type().equals(acc_type)) {
				flag = false;
				break;
			}
		}
		
		if(flag) {
			BankAccount bankAccount=new BankAccount();
			//bankAccount.setAcc_no(0);
			bankAccount.setAcc_type(acc_type);
			
			if(bankAccount.getAcc_type().equals("savings"))
				bankAccount.setAcc_limit(50000.00);
			else
				bankAccount.setAcc_limit(100000.00);
			
			bankAccount.setCustomer(customer);
			
			BankDao bankDao=new BankDao();
			bankDao.saveAccount(bankAccount);
			
			
			list.add(bankAccount);
			customer.setBankAccounts(list);
			
			CustDao custDao = new CustDao();
			custDao.update(customer);
			
			resp.getWriter().print("<h1>Congratulations your "+acc_type+" account created successfully</h1>");
			resp.getWriter().print("<h1>You have to take admin approval. First login with admin credential.</h1>");	
			req.getRequestDispatcher("adminLogin.html").include(req, resp);
		}
		else {
			resp.getWriter().print("<h1>Account Already Exist</h1>");
//			resp.getWriter().print("<h1>You have to take admin approval. First login with admin credential.</h1>");	
			req.getRequestDispatcher("customer_Home.html").include(req, resp);
		}
	}

}
