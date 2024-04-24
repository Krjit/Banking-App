<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dto.BankAccount"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
button {
	width: 200px;
	height: 60px;
	background-color: rgb(120, 109, 248);
	box-shadow: 5px 5px;
	font-weight: 800;
	border-radius: 10px;
}
</style>
</head>
<body>
	<h1>Welcome to Admin Home page</h1>

	<%
	List<BankAccount> acc_list = (ArrayList<BankAccount>) request.getSession().getAttribute("acc_list");
	%>

	<table border="2" cellspacing="0">
		<tr>
			<th>Account Number</th>
			<th>Account Type</th>
			<th>Customer Name</th>
			<th>Customer Id</th>
			<th>Account Status</th>
			<th>Change Status</th>
		</tr>

		<%for (BankAccount bankAccount : acc_list) {%>
		<tr>
			<td><%=bankAccount.getAcc_no()%></td>
			<td><%=bankAccount.getAcc_type()%></td>
			<td><%=bankAccount.getCustomer().getCname()%></td>
			<td><%=bankAccount.getCustomer().getCid()%></td>
			<th><%=bankAccount.isStatus()%></th>
			<td><a href="changeStatus?acc_no=<%=bankAccount.getAcc_no()%>"><button>Change
						Status</button></a></td>
		</tr>
		<%}%>
	</table>
</body>
</html>