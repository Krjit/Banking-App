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

@WebServlet("/deposit")
public class Deposit extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long acc_no = (long) req.getSession().getAttribute("acc_no");
		double amount= Double.parseDouble(req.getParameter("amt"));
		
		BankDao bankDao= new BankDao();
		BankAccount bankAccount= bankDao.fetch(acc_no);	
		bankAccount.setAmount(bankAccount.getAmount()+amount);
		
		BankTransaction bankTransaction= new BankTransaction();
		bankTransaction.setdAmount(amount);
		bankTransaction.setwAmount(0);
		bankTransaction.setBalance(bankAccount.getAmount());
		bankTransaction.setData_time(LocalDateTime.now());
		
		List<BankTransaction> list = bankAccount.getListTransaction();
		list.add(bankTransaction);
		bankAccount.setListTransaction(list);
		
		bankDao.update(bankAccount);
		resp.getWriter().print("<h1>Amount Added Successfully. Your current balance is "+bankAccount.getAmount()+"</h1");		
		req.getRequestDispatcher("accountHome.html").include(req, resp);
	}
}
