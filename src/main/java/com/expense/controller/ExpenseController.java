package com.expense.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expense.dao.ExpenseDbConnection;
import com.expense.dao.UserCommandsDao;
import com.expense.model.UserAccount;
import com.expense.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExpenseController {
	
	private static ExpenseDbConnection con = new ExpenseDbConnection();
	static UserService service = new UserService(new UserCommandsDao(con));
	
	public static String login(HttpServletRequest req) {
		System.out.println("in expense controller login");
		if(!req.getMethod().equals("POST")) {
			return "/html/home.html";
		}
		
		//pulls in user name, pass from request object and assigns to a user account object
		UserAccount user = service.checkCredentials(req.getParameter("username"), req.getParameter("password"));
		
		if(user == null) {
			return "/html/unsuccesful.html";
		}
		else {
			//creates session for user with a key of "currentUser" and value of the user object
			req.getSession().setAttribute("currentUser", user);
			return "/html/home.html";
		}
	}
	
	public static void getUserAccount(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		UserAccount currUser = (UserAccount)req.getSession().getAttribute("currentUser");
		res.getWriter().write(new ObjectMapper().writeValueAsString(currUser));
	}
	
	public static String register(HttpServletRequest req) {
		System.out.println("in the expense controller login");
		UserAccount user = new UserAccount(req.getParameter("username"),
											req.getParameter("password"),
											req.getParameter("firstName"),
											req.getParameter("lastName"),
											req.getParameter("email"));
		System.out.println(user);
		if(service.addNewUser(user)==null) {
			return "/html/regfail.html";
		}
		return "/html/regsuccess.html";
	}
}
