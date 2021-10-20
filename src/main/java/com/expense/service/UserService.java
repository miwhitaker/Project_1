package com.expense.service;

import java.sql.SQLException;

import com.expense.dao.UserCommandsDao;
import com.expense.model.UserAccount;

public class UserService {

	 private UserCommandsDao command;
	 
	 public UserService() {}
	 public UserService(UserCommandsDao command) {
		 super();
		 this.command = command;
	 }
	 public UserAccount checkCredentials(String user, String pass) {
		 UserAccount currUser = command.getUser(user);
		 if(currUser != null) {
			 if(currUser.getPassword().equals(pass)) {
				 return currUser;
			 }
		 }
		 return null;
	 }
	 
	 public UserAccount addNewUser(UserAccount newUser) {
		 try{
			 command.addUser(newUser);
		 }
		 catch(SQLException e) {
			 return null;
		 }
		 return newUser;
	 }
	 
	 //checks to see if a username exists already and returns null if so
	 public UserAccount checkUserName(String user, String pass) {
		 UserAccount currUser = command.getUser(user);
		 if(currUser.equals(null)) {
			 return null;
		 }
		 return currUser;
	 }
	 
}
