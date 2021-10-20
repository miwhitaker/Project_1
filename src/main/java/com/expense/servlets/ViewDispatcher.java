package com.expense.servlets;

import javax.servlet.http.HttpServletRequest;
import com.expense.controller.ExpenseController;

public class ViewDispatcher {

	public static String process(HttpServletRequest req) {
		
		switch(req.getRequestURI()) {
		
		case "/ExpenseController/login.change":
			System.out.println("in login.change dispatcher");
			return ExpenseController.login(req);
			
		 default:
			 	System.out.println("in  default");
			 	return "html/unsuccessful.html";
		
		} 
		
	}
	
}
