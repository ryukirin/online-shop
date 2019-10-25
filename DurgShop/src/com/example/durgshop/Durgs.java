package com.example.durgshop;
/**
 * “©∆∑¿‡*/
public class Durgs {
	public String did;
	public String name;
	public String price;
	public Durgs(String did, String name, String price) {
		super();
		this.did = did;
		this.name = name;
		this.price = price;
	}
	public Durgs() {
		super();
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
