package com.hcmute.model;

public class CommentModel {
    private int id;
    private int userId;
    private int productId;
    private String comment;

    public CommentModel() {
    }

    public CommentModel(int id, int userId, int productId, String comment) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
