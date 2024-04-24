package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BankDao;
import dto.BankAccount;
import dto.BankTransaction;

@WebServlet("/withdraw")
public class Withdraw extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long acc_no = (long) req.getSession().getAttribute("acc_no");
		double amount= Double.parseDouble(req.getParameter("amt"));
		
		BankDao bankDao= new BankDao();
		BankAccount bankAccount= bankDao.fetch(acc_no);	
		
		if(amount>bankAccount.getAmount()) {
			resp.getWriter().print("<h1>Insufficient Balance. Your Balace is "+bankAccount.getAmount()+"</h1");
		}
		
		else if(amount>bankAccount.getAcc_limit()) {
			resp.getWriter().print("<h1>You're exceeding limit. Your limit is "+bankAccount.getAcc_limit()+"</h1");
		}
		
		else {
			bankAccount.setAmount(bankAccount.getAmount()-amount);
			
			BankTransaction bankTransaction= new BankTransaction();
			bankTransaction.setwAmount(amount);
			bankTransaction.setdAmount(0);
			bankTransaction.setBalance(bankAccount.getAmount());
			bankTransaction.setData_time(LocalDateTime.now());
			
			List<BankTransaction> list = bankAccount.getListTransaction();
			list.add(bankTransaction);
			bankAccount.setListTransaction(list);
			
			bankDao.update(bankAccount);
			resp.getWriter().print("<h1>Amount withdrawn Successfully. Your current balance ia "+bankAccount.getAmount()+"<h1>");
			req.getRequestDispatcher("accountHome.html").include(req, resp);				
		}
		
	}
}
