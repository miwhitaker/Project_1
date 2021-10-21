package com.expense.dao;

import java.util.List;

public interface GenExpenseDao<T> {
	
	void addExpense(T entity);
	T getExpense(int num);
	List<T> getAllExpenses(int num);
	List<T> getAllExpAdmin();
	void updateExpense(int num1, int num2, int num3);
	void delete(T entity);
	
}
