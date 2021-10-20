package com.expense.dao;

import java.util.List;

public interface GenExpenseDao<T> {
	
	void addExpense(T entity);
	T getExpense(int num);
	List<T> getAllExpenses(int num);
	List<T> getAllExpAdmin(int num);
	void updateExpense(T entity, int num1, int num2);
	void delete(T entity);
	
}
