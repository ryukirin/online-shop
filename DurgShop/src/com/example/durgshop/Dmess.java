package com.example.durgshop;
/**
 * …Ã∆∑¡Ù—‘¿‡*/
public class Dmess {
	String id;
	String uid;
	String did;
	String cont;
	public Dmess(String id, String uid, String did, String cont) {
		super();
		this.id = id;
		this.uid = uid;
		this.did = did;
		this.cont = cont;
	}
	public Dmess(){
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
}
