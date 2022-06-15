package com.hcmute.model;

public class ProductModel {
    private int id;
    private String name;
    private String description;
    private String img;
    private int price;
    private int amount;
    private int cate_id;

    //Constructor
    public ProductModel(){}
    public ProductModel(int id, String name, String description, String img, int price, int amount, int cate_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img = img;
        this.price = price;
        this.amount = amount;
        this.cate_id = cate_id;
    }

    //Getter and Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    @Override
    public String toString() {
        return "product_model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", cate_id=" + cate_id +
                '}';
    }
}
