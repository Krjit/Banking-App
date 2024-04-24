<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dto.BankAccount"%>
<%@ page import="dao.BankDao"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome to Check Balance page</h1>
	<%long acc_no = (long) request.getSession().getAttribute("acc_no");	
	BankDao bankDao= new BankDao();
	BankAccount bankAccount= bankDao.fetch(acc_no);	 
	
	if(bankAccount.getCustomer().getGender().equals("male")){%>
	<h2>
		Hello Mr.
		<%=bankAccount.getCustomer().getCname()%></h2>
	<%}else{%>
	<h2>
		Hello Ms.
		<%=bankAccount.getCustomer().getCname()%></h2>
	<%}%>

	<h1>
		your account balance is
		<%=bankAccount.getAmount()%></h1>

</body>
</html>