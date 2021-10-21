package com.expense.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.expense.dao.ExpenseCommandsDao;
import com.expense.dao.ExpenseDbConnection;
import com.expense.dao.UserCommandsDao;
import com.expense.model.ExpenseRequest;
import com.expense.model.UserAccount;
import com.expense.service.ExpenseService;
import com.expense.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExpenseController {
	
	private static ExpenseDbConnection con = new ExpenseDbConnection();
	static UserService service = new UserService(new UserCommandsDao(con));
	static ExpenseService eServ = new ExpenseService(new ExpenseCommandsDao(con));
	
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
		else if(user.getRoleId() == 2){
			//creates session for user with a key of "currentUser" and value of the user object
			//here, I check to see if the user is a manager or a buyer, and send a different html page for each
			req.getSession().setAttribute("currentUser", user);
			return "/html/homeadmin.html";
		}
		else{
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
	
	public static void getExpenseRequests(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		System.out.println("Inside getExpenseRequests method");
		UserAccount currUser = (UserAccount)req.getSession().getAttribute("currentUser");
		int userId = currUser.getUsersId();
		List<ExpenseRequest> userRequests = eServ.getAllExpenses(userId);
		res.getWriter().write(new ObjectMapper().writeValueAsString(userRequests));
	}
	
	public static void newReq(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		System.out.println("Inside the newReq method");
		UserAccount currUser = (UserAccount)req.getSession().getAttribute("currentUser");
		int userId = currUser.getUsersId();
		String amtStr = req.getParameter("amount");
		String typeStr = req.getParameter("type");
		int amount = Integer.parseInt(amtStr);
		int type = Integer.parseInt(typeStr);
		ExpenseRequest newReq = new ExpenseRequest(amount, req.getParameter("description"), userId, type);
		eServ.addNewRequest(newReq);
	}
	
	public static void adminAll(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		System.out.println("inside the adminAll method");
		List<ExpenseRequest> userRequests = eServ.getAllExpAdmin();
		res.getWriter().write(new ObjectMapper().writeValueAsString(userRequests));
	}
	
	public static void adminApprove(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		System.out.println("inside Expense Controller adminApprove");
		UserAccount currUser = (UserAccount)req.getSession().getAttribute("currentUser");
		int userId = currUser.getUsersId();
		String approvalString = req.getParameter("statusChange");
		String idString = req.getParameter("requestId");
		int approvalStatus = Integer.parseInt(approvalString);
		int requestId = Integer.parseInt(idString);
		eServ.adminApprove(requestId, userId, approvalStatus);
	}
}
