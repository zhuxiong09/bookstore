package com.zx.model;

import java.io.Serializable;

/**
 * 详细订单实体类
 * @author 图灵教育
 *
 */
public class Items implements Serializable{
	private int iid;			//购买编号
	private int oid;			//订单编号
	private int bid;			//图书编号
	private String createdate;	//订购时间
	private int count;			//订购数量
	private double price;		//订购价格
	private double total_price;	//总计金额

	public Items() {
	}
	public Items(int iid, int oid, int bid, String createdate, int count,
			double price, double total_price) {
		this.iid = iid;
		this.oid = oid;
		this.bid = bid;
		this.createdate = createdate;
		this.count = count;
		this.price = price;
		this.total_price = total_price;
	}

	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double totalPrice) {
		total_price = totalPrice;
	}

}

