package com.expense.model;


public class UserAccount {

	private int usersId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int roleId;
	private String userRole;
	
	//Constructors(4 types): no args, all args, one without id(PK), one without user_role -- added two more as needed
	public UserAccount() {}
	
	public UserAccount(
			int usersId, 
			String username, 
			String password, 
			String firstName, 
			String lastName,
			String email,
			int roleId,
			String userRole) 
	{
		this.usersId = usersId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roleId = roleId;
		this.userRole = userRole;
	}
	
	public UserAccount(
			int usersId, 
			String username, 
			String password, 
			String firstName, 
			String lastName,
			String email,
			String userRole) 
	{
		this.usersId = usersId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole = userRole;
	}
	
	public UserAccount(
			int usersId, 
			String username, 
			String password, 
			String firstName, 
			String lastName,
			String email, 
			int roleId) 
	{
		this.usersId = usersId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roleId = roleId;
	}
	
	public UserAccount(
			String username, 
			String password, 
			String firstName, 
			String lastName,
			String email, 
			int roleId) 
	{
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roleId = roleId;
	}
	
	public UserAccount(
			String username, 
			String password, 
			String firstName, 
			String lastName,
			String email) 
	{
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public int getUsersId() {
		return usersId;
	}
	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getUserRole() {
		return usersId;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	@Override
	public String toString() {
		return "UserAccount [usersId=" + usersId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", roleId=" + roleId + ", userRole="
				+ userRole + "]";
	}
	

}
