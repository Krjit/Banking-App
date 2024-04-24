<%@page import="dto.BankAccount"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome to Active Accounts Page</h1>
	<%
	List<BankAccount> list = (List<BankAccount>) request.getSession().getAttribute("acc_list");
	if (list.isEmpty()) {%>
	<h1>No_Active Accounts Found</h1>
	<%} 
	else {%>
	<h2>Activate Bank Account(s):</h2>

	<%for (BankAccount bank_Account : list) {%>
	<a href="setActiveAcc?accno=<%=bank_Account.getAcc_no()%>"><%=bank_Account.getAcc_no()%></a>
	<%}%>

	<%}%>

</body>
</html>