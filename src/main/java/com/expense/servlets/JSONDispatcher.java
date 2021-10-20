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
			 
		default:
			res.getWriter().write(new ObjectMapper().writeValueAsString(new UserAccount()));
		
		}
	}
	 
	
	
	
}
