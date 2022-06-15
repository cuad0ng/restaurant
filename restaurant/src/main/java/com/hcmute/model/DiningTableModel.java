package com.hcmute.model;

public class DiningTableModel {
    private int id;
    private String status;

    public DiningTableModel(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "dining_table{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
