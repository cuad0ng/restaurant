package com.hcmute.model;

public class AccountModel {
    private int id;
    private String username;
    private String password;
    private String usertype;
private int customer_id;
    //Constructor
    public AccountModel(){}
    public AccountModel(String username, String password, String usertype) {
        this.username = username;
        this.password = password;
        this.usertype = usertype;
    }

    public AccountModel(int id, String username, String password, String usertype, int customer_id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
        this.customer_id = customer_id;
    }

    public AccountModel(int id, String username, String password, String usertype) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
    }
    //Getter and Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "acc_model{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", usertype='" + usertype + '\'' +
                '}';
    }
}
