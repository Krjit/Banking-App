package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustDao;
import dto.Customer;

import java.time.*;

@SuppressWarnings("serial")
@WebServlet("/Customer_signup")
public class CustomerSignUp extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		String mob = req.getParameter("mob");
		long mobile = Long.parseLong(mob);
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		String dob = req.getParameter("dob");

//		resp.getWriter().println("<h1>Name: "+name+
//								"<h1>Password: "+pwd+
//								"<h1>Mobile No.: "+mob+
//								"<h1>Email: "+email+
//								"<h1>Gender: "+gender+
//								"<h1>Date of Birth: "+dob+
//								"</h1");

		Date date = Date.valueOf(dob);
//		System.out.println(date);
		Period period = Period.between(date.toLocalDate(), LocalDate.now());
//		System.out.println(period);
		int age = period.getYears();
//		System.out.println(age);

		Customer customer = new Customer();
		CustDao custDao = new CustDao();

		if (age >= 18) {
			customer.setCname(name);
			customer.setPwd(pwd);
			customer.setMob(mobile);
			customer.setEmail(email);
			customer.setGender(gender);
			customer.setDob(date);
						
			if (custDao.checkEmail(email).isEmpty() && custDao.checkMobile(mobile).isEmpty()) {
//			resp.getWriter().print("<h1>You are elligible to create account");
				custDao.insert(customer);
				
				Customer custinfo= custDao.checkEmail(email).get(0);
				if (custinfo.getGender().equals("female")){
					resp.getWriter().print("<h1>Hello Madam. </h1>");
				}
				else {
					resp.getWriter().print("<h1>Hello Sir. </h1>");
				}
				
				resp.getWriter().print("<h1>Your Account created Successfully. Your id is " + custinfo.getCid()+"</h1>");
				
				req.getRequestDispatcher("login.html").include(req, resp);
			}
			
			else {
				resp.getWriter().print("<h1>The email ("+email+") or the mobile no. ("+mobile+") already exist. please signup with other credential.</h1>"); 
			}
		}

		else {
			resp.getWriter().print("<h1>You are not elligible to create account");

		}
	}
}
