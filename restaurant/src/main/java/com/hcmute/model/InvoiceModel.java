package com.hcmute.model;

import java.util.Date;

public class InvoiceModel {
    private int id;
    private int cusId;
    private int empId;
    private int cartId;
    private double totalCost;
    private Date createDate;

    //Constructor
    public InvoiceModel(){}
    public InvoiceModel(int id, int cust_id, int emp_id, int cart_id, double total_cost, Date create_date) {
        this.id = id;
        this.cusId = cust_id;
        this.empId = emp_id;
        this.cartId = cart_id;
        this.totalCost = total_cost;
        this.createDate = create_date;
    }

    //Getter and Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "invoice_model{" +
                "id=" + id +
                ", cust_id=" + cusId +
                ", emp_id=" + empId +
                ", cart_id=" + cartId +
                ", total_cost=" + totalCost +
                ", create_date=" + createDate +
                '}';
    }
}
