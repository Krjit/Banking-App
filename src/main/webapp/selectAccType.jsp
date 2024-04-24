<%@page import="dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
button {
	display: inline-block;
	background-color: blue;
	color: #fff;
	padding: 10px 20px;
	text-decoration: none;
	border-radius: 5px;
	font-weight: 600;
}
</style>
</head>

<body>
	<h2>Welcome to account creation page</h2>
	<%Customer customer = (Customer) request.getSession().getAttribute("customer");%>

	<h3>
		Hello Dear
		<%=customer.getCname()%></h3>
	<hr>
	<form action="create_acc">
		<span style="font-weight: 800">Account type: </span> <input
			type="radio" name="acc_type" value="current">Current &nbsp; <input
			type="radio" name="acc_type" value="savings">Savings <br>
		<br>

		<button type="submit">Submit</button>
		&nbsp;&nbsp;&nbsp;
		<button type="reset">Cancel</button>
	</form>
</body>
</html>