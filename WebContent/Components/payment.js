$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
	 	$("#alertSuccess").hide();
	 }
	 	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();

// Form validation-------------------
var status = validatePaymentForm();
	if (status != true)
	 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
     }
 
// If valid------------------------
var type = ($("#hidPaymentIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "PaymentAPI",
 type : type,
 data : $("#formPayment").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onPaymentSaveComplete(response.responseText, status);
 }
 });
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidPaymentIDSave").val($(this).data("paymentid"));
 $("#Payment_AccountNO").val($(this).closest("tr").find('td:eq(0)').text());
 $("#Payment_CName").val($(this).closest("tr").find('td:eq(1)').text());
 $("#Payment_Date").val($(this).closest("tr").find('td:eq(2)').text());
 $("#Payment_TotalAmount").val($(this).closest("tr").find('td:eq(3)').text());
});

//DELETE==========================================================
$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "PaymentAPI",
 type : "DELETE",
 data : "payment_ID=" + $(this).data("paymentid"),
 dataType : "text",
 complete : function(response, status)
 {
 onPaymentDeleteComplete(response.responseText, status);
 }
 });
});

// CLIENT-MODEL================================================================
function validatePaymentForm()
{
	

// payment_accountno
if ($("#Payment_AccountNO").val().trim() == "")
 {
 return "Insert Payment_AccountNO.";
 } 

// payment_cname-------------------------------
if ($("#Payment_CName").val().trim() == "")
 {
 return "Insert Payment_CName.";
 }

// payment_date-------------------------------
if ($("#Payment_Date").val().trim() == "")
 {
 return "Insert Payment_Date.";
 }
  
 // payment_totalAmount-------------------------------
if ($("#Payment_TotalAmount").val().trim() == "")
 {
 return "Insert Payment_TotalAmount.";
 }
 
return true;
}

function onPaymentSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divPaymentGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 
$("#hidPaymentIDSave").val("");
 $("#formPayment")[0].reset();
}

function onPaymentDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divPaymentGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}
