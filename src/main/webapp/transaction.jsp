<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dto.BankTransaction"%>
<%@ page import="dao.BankDao"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome to Transaction History page</h1>

	<%
	long acc_no = (Long) request.getSession().getAttribute("acc_no");
	List<BankTransaction> acc_list = new BankDao().fetch(acc_no).getListTransaction();
	%>

	<table border="2" cellspacing="0">
		<tr>
			<th>Transaction Id</th>
			<th>Deposit Amount</th>
			<th>Withdraw Amount</th>
			<th>Balance</th>
			<th>Transaction Time</th>
		</tr>

		<%for (BankTransaction bankTransaction: acc_list) {%>
		<tr>
			<td><%=bankTransaction.getTid()%></td>
			<td><%=bankTransaction.getdAmount()%></td>
			<td><%=bankTransaction.getwAmount()%></td>
			<td><%=bankTransaction.getBalance()%></td>
			<th><%=bankTransaction.getData_time()%></th>
		</tr>
		<%}%>
	</table>
</body>
</html>