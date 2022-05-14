<%@page import="com.PaymentManage.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Payment Management</title>
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery.min.js"></script>
		<script src="Components/payment.js"></script>
	</head>
<style>
body{
background-image: url("Images/payment2.jpg");
}
</style>
	<body> 
		<div class="container"><div class="row"><div class="col-6"> <br>
		
		<h2>&nbsp;Payment Management </h2>
		
			<form id="formPayment" name="formPayment">
			
			    &nbsp;Payment AccountNo : 
 				<input id="Payment_AccountNO" name="Payment_AccountNO" type="text" placeholder="Enter Account No"
 				class="form-control form-control-sm" style="width:500px;margin-left:20px"><br> 
 				
 				&nbsp;Payment Customer Name : 
 				<input id="Payment_CName" name="Payment_CName" type="text" placeholder="Enter Name"
				class="form-control form-control-sm" style="width:500px;margin-left:20px"><br>
 				
 				&nbsp;Payment Date : 
 				<input id="Payment_Date" name="Payment_Date" type="text" placeholder="Enter Date"
 				class="form-control form-control-sm" style="width:500px;margin-left:20px"><br> 
 				
 				&nbsp;Payment  Total Amount : 
 				<input id="Payment_TotalAmount" name="Payment_TotalAmount" type="text" placeholder="Enter Total Amount"
 				class="form-control form-control-sm" style="width:500px;margin-left:20px"><br>
 				
 				<input id="btnSave" name="btnSave" type="button" value="Save" 
 				class="btn btn-primary" style="margin-left:150px">
 				<input type="hidden" id="hidPaymentIDSave" 
				name="hidPaymentIDSave" value="">
				
			</form>
			
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		
		<div id="divPaymentGrid">
 		<%
 			Payment paymentObj = new Payment(); 
 		 		out.print(paymentObj.readPayment());
 		%>
	</div>
	
	</div> </div> </div> 
</body>
</html>
