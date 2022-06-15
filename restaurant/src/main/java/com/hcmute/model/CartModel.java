package com.hcmute.model;

public class CartModel {
    private int id;
    private int cusId;
    private int productId;
    private int productAmount;
    private int state;
    private String img;
    private String product_name;
    private int product_price;

    //Constructor
    public CartModel() {
    }

    public CartModel(int id, int cust_id, int product_id, int product_amount) {
        this.id = id;
        this.cusId = cust_id;
        this.productId = product_id;
        this.productAmount = product_amount;
    }

    public CartModel(int id, int cusId, int productId, int productAmount, int status, String img, String product_name, int product_price) {
        this.id = id;
        this.cusId = cusId;
        this.productId = productId;
        this.productAmount = productAmount;
        this.state = status;
        this.img = img;
        this.product_name = product_name;
        this.product_price = product_price;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    @Override
    public String toString() {
        return "cart_model{" +
                "id=" + id +
                ", cust_id=" + cusId +
                ", product_id=" + productId +
                ", product_amount=" + productAmount +
                '}';
    }
}
