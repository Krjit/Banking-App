package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BankAccount;
import dto.Customer;

@WebServlet("/fetchActiveAcc")
public class FetchActiveAccount extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Customer customer = (Customer)req.getSession().getAttribute("customer");
		List<BankAccount> list = customer.getBankAccounts();
		
		List<BankAccount> list2 = new ArrayList<>();
		
		for (BankAccount bank_Account : list) {
			if (bank_Account.isStatus()) {
				list2.add(bank_Account);
			}
		}
			
		req.getSession().setAttribute("acc_list", list2);
		req.getRequestDispatcher("Accounts.jsp").include(req, resp);
	}
}