package com.expense.service;

import java.sql.SQLException;
import java.util.List;

import com.expense.dao.UserCommandsDao;
import com.expense.model.ExpenseRequest;
import com.expense.model.UserAccount;

public class UserService {

	 private UserCommandsDao command;
	 
	 public UserService() {}
	 public UserService(UserCommandsDao command) {
		 super();
		 this.command = command;
	 }
	 //Password verification - pulls user and checks the password
	 public UserAccount checkCredentials(String user, String pass) {
		 UserAccount currUser = command.getUser(user);
		 if(currUser != null) {
			 if(currUser.getPassword().equals(pass)) {
				 return currUser;
			 }
		 }
		 return null;
	 }
	 
	 //Tries to add a new user, if username is already in DB, it responds null -- no duplicate usernames
	 public UserAccount addNewUser(UserAccount newUser) {
		 try{
			 command.addUser(newUser);
		 }
		 catch(SQLException e) {
			 return null;
		 }
		 return newUser;
	 }
	 
}
