package com.expense.servlets;

import javax.servlet.http.HttpServletRequest;

import com.expense.controller.ExpenseController;

public class RequestDispatcher {

	public static String process(HttpServletRequest req) {
		
		System.out.println(req.getRequestURI());
		switch(req.getRequestURI()) {
		case "/Project_1/MainServlet/login.change":
			System.out.println("inside login.change dispatcher");
			return ExpenseController.login(req);
			
		case "/Project_1/MainServlet/register.change":
			System.out.println("inside registration.change dispatcher");
			return ExpenseController.register(req);
		
		default:
			System.out.println("in default");
			return "/html/unsucessful.html";
		}
		
	}
	
}
