package com.expense.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ExpenseDbConnection {

	ClassLoader loader = getClass().getClassLoader();
	InputStream stream;
	Properties props = new Properties();
	
	public ExpenseDbConnection() {
		stream = loader.getResourceAsStream("connection.properties");
		try {
			props.load(stream);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public Connection getDbConnection() throws SQLException{
		final String URL = props.getProperty("url");
		final String USERNAME = props.getProperty("username");
		final String PASSWORD = props.getProperty("password");
		 try {
	            Class.forName("org.postgresql.Driver");
	        }
		 catch(ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		 return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}
