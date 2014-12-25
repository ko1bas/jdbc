package com.simbirsoft.entities;

import java.util.UUID;

public class Product {
	private String id;
	private String category;
	private String name;
	private String description;
	private int count;
	private Float price;

	public Product() {
		this.id = UUID.randomUUID().toString();
	}

	public Product(String id, String category, String name, String description,
			int count, Float price) {
		this();
		this.id = id;
		this.name = name;
		this.category = category;
		this.description = description;
		this.count = count;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

}
