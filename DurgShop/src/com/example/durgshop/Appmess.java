package com.example.durgshop;
/**
 * π‹¿Ì‘±¡Ù—‘*/
public class Appmess {
	String id;
	String uid;
	String cont;
	public Appmess(String id, String uid, String cont) {
		super();
		this.id = id;
		this.uid = uid;
		this.cont = cont;
	}
	public Appmess(){
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
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
}
