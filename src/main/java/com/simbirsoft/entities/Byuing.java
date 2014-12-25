package com.simbirsoft.entities;

import java.sql.Date;
import java.util.UUID;

public class Byuing {

	private String id;
	private Date date;
	private String userId;
	private String productId;
	
	
	public Byuing() {
		this.id = UUID.randomUUID().toString();
	}
	
	public Byuing(String id, Date date, String userId, String productId) {
		this();
		this.id = id;
		this.date = date;
		this.userId = userId;
		this.productId = productId;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


}
