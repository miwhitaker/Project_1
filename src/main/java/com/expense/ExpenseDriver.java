package com.expense;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.expense.dao.ExpenseCommandsDao;
import com.expense.dao.ExpenseDbConnection;
import com.expense.dao.UserCommandsDao;
import com.expense.model.ExpenseRequest;
import com.expense.model.UserAccount;

public class ExpenseDriver {
	
	public static final Logger log = Logger.getLogger(ExpenseDriver.class);

	public static void main(String[] args) {
		
		//log.setLevel(Level.DEBUG);
		
		ExpenseDbConnection con = new ExpenseDbConnection();
		UserCommandsDao uCommand = new UserCommandsDao(con);
		ExpenseCommandsDao command = new ExpenseCommandsDao(con);
		System.out.println(command.getAllExpAdmin(1));
		///format is (amount, description, author, typeId)
		//ExpenseRequest newReq = new ExpenseRequest(20000, "Purchase Vesta", 1, 3);
		//command.addExpense(newReq);
		//System.out.println();
		
	}
	
}
