package com.zx.model;


import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Books implements Serializable{
	private int bid;
	private String bookname;
	private double price;
	private String image;
	private int stock;
	
	public Books() {
	}
	
	public Books(int bid, String bookname, double price, String image, int stock) {
		this.bid = bid;
		this.bookname = bookname;
		this.price = price;
		this.image = image;
		this.stock = stock;
	}

	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

}