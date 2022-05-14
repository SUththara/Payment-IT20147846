package com.PaymentManage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


	@WebServlet("/PaymentAPI")
	public class PaymentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Payment paymentObj = new Payment();

	public PaymentAPI() {
	super();
	}

	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request)
		{
		 Map<String, String> map = new HashMap<String, String>();
		try
		 {
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		 String queryString = scanner.hasNext() ?
		 scanner.useDelimiter("\\A").next() : "";
		 scanner.close();
		 String[] params = queryString.split("&");
		 for (String param : params)
		 { 
			 String[] p = param.split("=");
			 map.put(p[0], p[1]);
			 }
			 }
			catch (Exception e)
			 {
			 }
			return map;
			}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String output = paymentObj.insertPayment(request.getParameter("Payment_AccountNO"),
					request.getParameter("Payment_CName"),
			        request.getParameter("Payment_Date"),
			        request.getParameter("Payment_TotalAmount"));
					response.getWriter().write(output); 

		}

		protected void doPut(HttpServletRequest request, HttpServletResponse response)
				 throws ServletException, IOException
				{
				 Map paras = getParasMap(request);
				 String output = paymentObj.updatePayment(paras.get("hidPaymentIDSave").toString(),
				 paras.get("Payment_AccountNO").toString(),
				 paras.get("Payment_CName").toString(),
				 paras.get("Payment_Date").toString(),
				 paras.get("Payment_TotalAmount").toString());
				 response.getWriter().write(output);
				} 

		protected void doDelete(HttpServletRequest request, HttpServletResponse response)
				 throws ServletException, IOException
				{
				 Map paras = getParasMap(request);
				 String output = paymentObj.deletePayment(paras.get("payment_ID").toString());
				response.getWriter().write(output);
				}

	}
