package com.expense.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class JSONServlet extends HttpServlet{


		  @Override
		  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
			  System.out.println("in JSON Servlet, doGet");
			  JSONDispatcher.process(req, res);
		  }
	
		  @Override
		  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
			  System.out.println("in JSON Servlet, doPost");
			  JSONDispatcher.process(req, res);
		  }
	
}
