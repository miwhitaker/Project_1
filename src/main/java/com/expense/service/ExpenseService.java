package com.expense.service;

import java.util.List;

import com.expense.dao.ExpenseCommandsDao;
import com.expense.model.ExpenseRequest;

public class ExpenseService {

	private ExpenseCommandsDao command;
	 
	 public ExpenseService() {}
	 public ExpenseService(ExpenseCommandsDao command) {
		 super();
		 this.command = command;
	 }
	
	
	public List<ExpenseRequest> getAllExpenses(int num) {
		 List<ExpenseRequest> currList = command.getAllExpenses(num);
		 return currList;
	 }
	
	public void addNewRequest(ExpenseRequest request) {
		command.addExpense(request);
	}
	
	public List<ExpenseRequest> getAllExpAdmin() {
		List<ExpenseRequest> expenseList = command.getAllExpAdmin();
		return expenseList;
	}
	
	public void adminApprove(int reqNum, int managerId, int statusId) {
		command.updateExpense(reqNum, managerId, statusId);
	}
}
