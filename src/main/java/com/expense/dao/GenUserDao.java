package com.expense.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenUserDao<T> {

	void addUser(T entity) throws SQLException;
	T getUser(String entity);
	T getUser(int entity);
	void deleteUser(int num);
	void updateUser(T entity);
	List<T> getAllUsers(T entity);
	
}
