package com.expense.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.expense.model.UserAccount;

public class UserCommandsDao implements GenUserDao<UserAccount>{
	
	//This lists all the commands for interacting with the database directly - calls ExpenseDbConnection first to connect to DB
	
	private ExpenseDbConnection con;
	
	//Constructors for the class, both a zero arg constructor and one with the connection parameter
	public UserCommandsDao() {};
	public UserCommandsDao(ExpenseDbConnection con) {
		this.con = con;
	}

	/////This retrieves an individual user's information from the database --> CHECKED
	@Override
	public UserAccount getUser(String user) {
		UserAccount currentUser = new UserAccount();
		try(Connection connect = con.getDbConnection()) {
			String sql = "select eu.ers_users_id, eu.ers_username, eu.ers_password, eu.user_first_name, eu.user_last_name,eu.user_email,"
					+ "eu.user_role_id, eur.user_role from ers_users as eu left join ers_user_roles as eur on eu.user_role_id"
					+ "=eur.ers_user_role_id where ers_username = ?";
			PreparedStatement pStat = connect.prepareStatement(sql);
			pStat.setString(1, user);
			ResultSet results = pStat.executeQuery();
			
			while(results.next()) {
				currentUser.setUsersId(results.getInt(1));
				currentUser.setUsername(results.getString(2));
				currentUser.setPassword(results.getString(3));
				currentUser.setFirstName(results.getString(4));
				currentUser.setLastName(results.getString(5));
				currentUser.setEmail(results.getString(6));
				currentUser.setRoleId(results.getInt(7));
				currentUser.setUserRole(results.getString(8));
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return currentUser;
	}
	
	
	/////This adds a new buyer (ers_role_id = 1) to the er_users table (used when a new person registers)
	@Override
	public void addUser(UserAccount entity) throws SQLException{
		Connection connect = con.getDbConnection();
			String sql = "{? = call insert_user(?, ?, ?, ?, ?, ?)}";
			CallableStatement cStat = connect.prepareCall(sql);
			cStat.registerOutParameter(1, Types.VARCHAR);
			cStat.setString(2, entity.getUsername());
			cStat.setString(3, entity.getPassword());
			cStat.setString(4, entity.getFirstName());
			cStat.setString(5, entity.getLastName());
			cStat.setString(6, entity.getEmail());
			cStat.setInt(7, 1);
			cStat.execute();
		
	}
	
	/////This gets a user from DB when only the id number is given --> CHECKED
	@Override
	public UserAccount getUser(int num) {
		UserAccount currentUser = new UserAccount();
		try(Connection connect = con.getDbConnection()) {
			String sql = "select eu.ers_users_id, eu.ers_username, eu.ers_password, eu.user_first_name, eu.user_last_name,eu.user_email,"
					+ "eu.user_role_id, eur.user_role from ers_users as eu left join ers_user_roles as eur on eu.user_role_id"
					+ "=eur.ers_user_role_id where ers_users_id = ?";
			PreparedStatement pStat = connect.prepareStatement(sql);
			pStat.setInt(1, num);
			ResultSet results = pStat.executeQuery();
			
			while(results.next()) {
				currentUser.setUsersId(results.getInt(1));
				currentUser.setUsername(results.getString(2));
				currentUser.setPassword(results.getString(3));
				currentUser.setFirstName(results.getString(4));
				currentUser.setLastName(results.getString(5));
				currentUser.setEmail(results.getString(6));
				currentUser.setRoleId(results.getInt(7));
				currentUser.setUserRole(results.getString(8));
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return currentUser;
	}
	
	
	/////This is used to delete a user account
	@Override
	public void deleteUser(int num) {
		try(Connection connect = con.getDbConnection()) {
			String sql = "delete from ers_users where ers_users_id = ?";
			PreparedStatement pStat = connect.prepareStatement(sql);
			pStat.setInt(1, num);
			pStat.executeQuery();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void updateUser(UserAccount entity) {
		// TODO Auto-generated method stub
		
	}
	
	
	/////This gets all users from the database
	@Override
	public List<UserAccount> getAllUsers(UserAccount entity) {
		List<UserAccount> allAccounts = new ArrayList<>();
		try(Connection connect = con.getDbConnection()) {
			String sql = "select eu.ers_users_id, eu.ers_username, eu.ers_password, eu.user_first_name, eu.user_last_name,"
					+ "eu.user_email, eur.user_role from ers_users as eu left join ers_user_roles as eur on eu.user_role_id"
					+ "=eur.ers_user_role_id";
			PreparedStatement pStat = connect.prepareStatement(sql);
			ResultSet results = pStat.executeQuery();
			
			while(results.next()) {
				UserAccount currAccount = new UserAccount(results.getInt(1), 
														results.getString(2), 
														results.getString(3),
														results.getString(4),
														results.getString(5),
														results.getString(6),
														results.getInt(7),
														results.getString(8));
				allAccounts.add(currAccount);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return allAccounts;
	}
	


}
