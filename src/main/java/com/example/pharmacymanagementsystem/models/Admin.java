package com.example.pharmacymanagementsystem.models;

public class Admin {
    public static String adminID;
    public static String adminUsername;

    public  String getAdminID(){
        return adminID;
    }
    public String getAdminUsername(){
        return adminUsername;
    }
    public void setAdminID(String ID){
        adminID = ID;
    }
    public void setAdminUsername(String username){
        adminUsername = username;
    }
}

