package com.expense.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import com.expense.model.ExpenseRequest;


public class ExpenseCommandsDao implements GenExpenseDao<ExpenseRequest> {
	
	//This lists all the commands for interacting with the database directly - calls ExpenseDbConnection first to connect to DB
	
	private ExpenseDbConnection con;
	
	//Constructors for the class, both a zero arg constructor and one with the connection parameter
	public ExpenseCommandsDao() {};
	public ExpenseCommandsDao(ExpenseDbConnection con) {
		this.con = con;
	}
	
	
	/////This is called when a buyer makes a new purchase order
	@Override
	public void addExpense(ExpenseRequest entity) {
		try(Connection connect = con.getDbConnection()) {
			String sql = "{? = call insert_reimb(?, ?, ?, ?, ?)}";
			CallableStatement cStat = connect.prepareCall(sql);
			cStat.registerOutParameter(1, Types.VARCHAR);
			cStat.setInt(2, entity.getAmount());
			cStat.setString(3, entity.getDesription());
			cStat.setInt(4, entity.getAuthor());
			cStat.setInt(5, 1);
			cStat.setInt(6, entity.getTypeId());
			cStat.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/////This grabs a single request from the database
	@Override
	public ExpenseRequest getExpense(int num) {
		ExpenseRequest currRequest = new ExpenseRequest();
		try(Connection connect = con.getDbConnection()) {
			String sql = "select er.reimb_id, er.reimb_amount, er.reimb_submitted, er.reimb_resolved, er.reimb_description,"
					+ "er.reimb_author, er.reimb_resolver, er.reimb_status_id, er.reimb_type_id, es.reimb_status,"
					+ "et.reimb_type from ers_reimbursement as er" + " "
					+ "left join ers_reimbursement_status as es on er.reimb_status_id = es.reimb_status_id" + " "
					+ "left join ers_reimbursement_type as et on er.reimb_type_id = et.reimb_type_id" + " "
					+ "where er.reimb_id = ?";
			PreparedStatement pStat = connect.prepareStatement(sql);
			pStat.setInt(1, num);
			ResultSet results = pStat.executeQuery();
			
			while(results.next()) {
				currRequest.setId(results.getInt(1));
				currRequest.setAmount(results.getInt(2));
				currRequest.setSubmittedDate(results.getString(3));
				currRequest.setResolvedDate(results.getString(4));
				currRequest.setDesription(results.getString(5));
				currRequest.setAuthor(results.getInt(6));
				currRequest.setResolver(results.getInt(7));
				currRequest.setStatusId(results.getInt(8));
				currRequest.setTypeId(results.getInt(9));
				currRequest.setStatusName(results.getString(10));
				currRequest.setTypeName(results.getString(11));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return currRequest;
	}
	
	
	/////This grabs all requests by an individual user
	@Override
	public List<ExpenseRequest> getAllExpenses(int num) {
		List<ExpenseRequest> reqList = new ArrayList<>();
		try(Connection connect = con.getDbConnection()) {
			String sql = "select er.reimb_id, er.reimb_amount, er.reimb_submitted, er.reimb_resolved, er.reimb_description,"
					+ "er.reimb_author, er.reimb_resolver, er.reimb_status_id, er.reimb_type_id, es.reimb_status,"
					+ "et.reimb_type from ers_reimbursement as er" + " "
					+ "left join ers_reimbursement_status as es on er.reimb_status_id = es.reimb_status_id" + " "
					+ "left join ers_reimbursement_type as et on er.reimb_type_id = et.reimb_type_id" + " "
					+ "where reimb_author = ?";
			PreparedStatement pStat = connect.prepareStatement(sql);
			pStat.setInt(1, num);
			ResultSet results = pStat.executeQuery();
			
			while(results.next()) {
				ExpenseRequest currRequest = new ExpenseRequest();
				currRequest.setId(results.getInt(1));
				currRequest.setAmount(results.getInt(2));
				currRequest.setSubmittedDate(results.getString(3));
				currRequest.setResolvedDate(results.getString(4));
				currRequest.setDesription(results.getString(5));
				currRequest.setAuthor(results.getInt(6));
				currRequest.setResolver(results.getInt(7));
				currRequest.setStatusId(results.getInt(8));
				currRequest.setTypeId(results.getInt(9));
				currRequest.setStatusName(results.getString(10));
				currRequest.setTypeName(results.getString(11));
				reqList.add(currRequest);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return reqList;
	}
	
	
	/////This grabs all requests with a particular status code: "pending," for example
	@Override
	public List<ExpenseRequest> getAllExpAdmin() {
		List<ExpenseRequest> reqList = new ArrayList<>();
		try(Connection connect = con.getDbConnection()) {
			String sql = "select er.reimb_id, er.reimb_amount, er.reimb_submitted, er.reimb_resolved, er.reimb_description,"
					+ "er.reimb_author, er.reimb_resolver, er.reimb_status_id, er.reimb_type_id, es.reimb_status,"
					+ "et.reimb_type from ers_reimbursement as er" + " "
					+ "left join ers_reimbursement_status as es on er.reimb_status_id = es.reimb_status_id" + " "
					+ "left join ers_reimbursement_type as et on er.reimb_type_id = et.reimb_type_id";
			PreparedStatement pStat = connect.prepareStatement(sql);
			ResultSet results = pStat.executeQuery();
			
			while(results.next()) {
				ExpenseRequest currRequest = new ExpenseRequest();
				currRequest.setId(results.getInt(1));
				currRequest.setAmount(results.getInt(2));
				currRequest.setSubmittedDate(results.getString(3));
				currRequest.setResolvedDate(results.getString(4));
				currRequest.setDesription(results.getString(5));
				currRequest.setAuthor(results.getInt(6));
				currRequest.setResolver(results.getInt(7));
				currRequest.setStatusId(results.getInt(8));
				currRequest.setTypeId(results.getInt(9));
				currRequest.setStatusName(results.getString(10));
				currRequest.setTypeName(results.getString(11));
				reqList.add(currRequest);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return reqList;
	}
	
	
	/////This is to approve or deny a request
	@Override
	public void updateExpense(int reqNum, int managerId, int statusId) {
		try(Connection connect = con.getDbConnection()) {
			String sql1 = "update ers_reimbursement set reimb_resolver = ?, reimb_status_id = ? where reimb_id = ?;";
			PreparedStatement pStat1 = connect.prepareStatement(sql1);
			pStat1.setInt(1, managerId);
			pStat1.setInt(2, statusId);
			pStat1.setInt(3, reqNum);
			pStat1.executeQuery();
			
			String sql2 = "update ers_reimbursement set reimb_resolved = cast(current_timestamp as timestamp) where reimb_id = ?";
			PreparedStatement pStat2 = connect.prepareStatement(sql2);
			pStat2.setInt(1, reqNum);
			pStat2.executeQuery();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/////This deletes a request from ers_reimbursement database
	@Override
	public void delete(ExpenseRequest entity) {
		try(Connection connect = con.getDbConnection()) {
			String sql = "delete from ers_reimbursement where id = ?";
			PreparedStatement pStat = connect.prepareStatement(sql);
			pStat.setInt(1, entity.getId());
			pStat.executeQuery();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
