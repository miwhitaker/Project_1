package com.expense.model;

public class ExpenseRequest {

	private int id;
	private int amount;
	private String submittedDate;
	private String resolvedDate;
	private String desription;
	private int author;
	private int resolver;
	private int statusId;
	private int typeId;
	private String statusName;
	private String typeName;
	
	
	public ExpenseRequest() {}
	public ExpenseRequest(	
			int id, 
			int amount,
			String submittedDate,
			String resolvedDate,
			String desription,
			int author,
			int resolver,
			int statusId,
			int typeId) 
	{
		super();
		this.id = id;
		this.amount = amount;
		this.submittedDate = submittedDate;
		this.resolvedDate = resolvedDate;
		this.desription = desription;
		this.author = author;
		this.resolver = resolver;
		this.statusId = statusId;
		this.typeId = typeId;
		
	};
	
	public ExpenseRequest(	
			int amount,
			String submittedDate,
			String resolvedDate,
			String desription,
			int author,
			int resolver,
			int statusId,
			int typeId) 
	{
		this.amount = amount;
		this.submittedDate = submittedDate;
		this.resolvedDate = resolvedDate;
		this.desription = desription;
		this.author = author;
		this.resolver = resolver;
		this.statusId = statusId;
		this.typeId = typeId;	
	}
	
	public ExpenseRequest(	
			int amount,
			String desription,
			int author,
			int typeId) 
	{
		this.amount = amount;
		this.desription = desription;
		this.author = author;
		this.typeId = typeId;	
	}
	
	public ExpenseRequest(	
			int id, 
			int amount,
			String submittedDate,
			String resolvedDate,
			String desription,
			int author,
			int resolver,
			int statusId,
			int typeId,
			String statusName,
			String typeName) 
	{
		super();
		this.id = id;
		this.amount = amount;
		this.submittedDate = submittedDate;
		this.resolvedDate = resolvedDate;
		this.desription = desription;
		this.author = author;
		this.resolver = resolver;
		this.statusId = statusId;
		this.typeId = typeId;
		this.statusName = statusName;
		this.typeName = typeName;
	};
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getSubmittedDate() {
		return submittedDate;
	}
	public void setSubmittedDate(String submittedDate) {
		this.submittedDate = submittedDate;
	}
	public String getResolvedDate() {
		return resolvedDate;
	}
	public void setResolvedDate(String resolvedDate) {
		this.resolvedDate = resolvedDate;
	}
	public String getDesription() {
		return desription;
	}
	public void setDesription(String desription) {
		this.desription = desription;
	}
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	public int getResolver() {
		return resolver;
	}
	public void setResolver(int resolver) {
		this.resolver = resolver;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return "ExpenseRequest [id=" + id + ", amount=" + amount + ", submittedDate=" + submittedDate
				+ ", resolvedDate=" + resolvedDate + ", desription=" + desription + ", author=" + author + ", resolver="
				+ resolver + ", statusId=" + statusId + ", typeId=" + typeId + ", statusName=" + statusName
				+ ", typeName=" + typeName + "]";
	}
	
}