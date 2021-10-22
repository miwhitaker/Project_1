package com.expense.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.expense.controller.ExpenseController;
import com.expense.model.UserAccount;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONDispatcher {

	
	  public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		switch(req.getRequestURI()) {
		
		 	case "/ExpenseController/getuseraccount.json":
		 		ExpenseController.getUserAccount(req, res);
		 		break;
			 
		 	case "/Project_1/JSONServlet/requests.json":
		 		System.out.println("in JSON Dispatcher class");
		 		ExpenseController.getExpenseRequests(req, res);
		 		break;
		 		
		 	case "/Project_1/JSONServlet/newreq.json":
		 		System.out.println("inside JSON dispatcher - new requests");
		 		ExpenseController.newReq(req, res);
		 		break;
		 		
		 	case "/Project_1/JSONServlet/getadmin.json":
		 		System.out.println("inside JSON dispatcher - admin requests");
		 		ExpenseController.adminAll(req, res);
		 		break;
		 	
		 	case "/Project_1/JSONServlet/changestatus.json":
		 		System.out.println("inside JSON dispatcher - change status");
		 		ExpenseController.adminApprove(req, res);
		 		break;
			 
		default:
			res.getWriter().write(new ObjectMapper().writeValueAsString(new UserAccount()));
		
		}
	}
	 
	
	
	
}
