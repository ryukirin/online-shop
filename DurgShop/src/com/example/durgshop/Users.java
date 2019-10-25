package com.example.durgshop;
/**
 * ”√ªß¿‡*/
public class Users {
    public int id;
    public String name;
    public String password;

    public Users(){

    }
    public Users(String name){
        this.name = name;
    }
    public Users(String name,String password){
        this.name = name;
        this.password = password;
    }
}

