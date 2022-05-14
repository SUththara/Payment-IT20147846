package com.PaymentManage;
import java.sql.*;

public class Payment {
	
	public Connection connect()
	{
			Connection con = null;
			try
			{
			
				Class.forName("com.mysql.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafelectricity", "root", "root");
			
				//For testing
				System.out.print("Successfully connected");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return con;
	}
	
public String insertPayment(String Payment_AccountNO, String Payment_CName, String Payment_Date, String Payment_TotalAmount)
		{
			String output = "";
			try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into payment1 (`payment_ID`,`Payment_AccountNO`,`Payment_CName`,`Payment_Date`,`Payment_TotalAmount`)"
					+ " values ( ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Payment_AccountNO);
			preparedStmt.setString(3, Payment_CName);
			preparedStmt.setString(4, Payment_Date);
			preparedStmt.setString(5, Payment_TotalAmount);
			

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newPayment = readPayment();
			output = "{\"status\":\"success\", \"data\": \"" +newPayment + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the payment1.\"}";
			System.err.println(e.getMessage());
		}
			
		return output;
	}

public String readPayment()
{
		String output = "";
		try
		{
				Connection con = connect();
					if (con == null)
					{
						return "Error while connecting to the database for reading.";
					}

					//Prepare the HTML table to be displayed
					output = "<table border='3'>"
							+ "<tr><th>Payment AccountNO</th>"
							+"<th>Payment Customer Name</th>"
							+ "<th>Payment Date</th>"
							+ "<th>Payment Total Amount</th>"
							+ "<th>Update</th><th>Remove</th></tr>";
					
					String query = "select * from payment1";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);

					// iterate through the rows in the result set
					while (rs.next())
					{
						String payment_ID  = Integer.toString(rs.getInt("payment_ID"));
						String Payment_AccountNO  = rs.getString("Payment_AccountNO");
						String Payment_CName  = rs.getString("Payment_CName");
						String Payment_Date  = rs.getString("Payment_Date");
						String Payment_TotalAmount = rs.getString("Payment_TotalAmount");


						// Add a row into the html table
						output += "<tr><td><input id='hidPaymentIDUpdate'name='hidPaymentIDUpdate'type='hidden' value='" + payment_ID  + "'>"+ Payment_AccountNO  + "</td>";
						output += "<td>" + Payment_CName + "</td>";
						output += "<td>" + Payment_Date + "</td>";
						output += "<td>" + Payment_TotalAmount + "</td>";


						// buttonss
						output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-paymentid='" + payment_ID + "'></td>"
								+"<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-paymentid='" + payment_ID + "'></td></tr>"; 
					}
					con.close();


					// Complete the html table
					output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the payment1.";
				System.err.println(e.getMessage());
			}
			return output;
		}
	public String deletePayment(String payment_ID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting.";
			}


			// create a prepared statement
			String query = "delete from payment1 where payment_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(payment_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			//output = "Deleted successfully";
			String newPayment = readPayment();
			output = "{\"status\":\"success\", \"data\": \"" +newPayment + "\"}";
			}
		catch (Exception e)
		{
			//output = "Error while deleting the payment.";
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the payment1.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	//method to update bill details in DB
	public String updatePayment(String payment_ID, String Payment_AccountNO, String Payment_CName,String Payment_Date,String Payment_TotalAmount)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}
				
			// create a prepared statement
			String query = "UPDATE payment1 SET Payment_AccountNO=?,Payment_CName=?,Payment_Date=?,Payment_TotalAmount=? WHERE payment_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, Payment_AccountNO);
			preparedStmt.setString(2, Payment_CName);
			preparedStmt.setString(3, Payment_Date);
			preparedStmt.setString(4, Payment_TotalAmount);
			preparedStmt.setInt(5, Integer.parseInt(payment_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Updated Customer details successfully";
			String newPayment = readPayment();
			output = "{\"status\":\"success\", \"data\": \"" +newPayment + "\"}"; }
		catch (Exception e)
		{
			//output = "Error while updating the Payment Details.";
			output = "{\"status\":\"error\", \"data\":\"Error while updating the payment1.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}


}

