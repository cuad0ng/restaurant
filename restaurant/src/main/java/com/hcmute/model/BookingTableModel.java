package com.hcmute.model;

import java.sql.Date;
import java.sql.Time;

public class BookingTableModel {
    private int id;
    private int cusId;
    private int tableId;
    private Date date;
    private Time arrivalTime;
    private String status;

    public BookingTableModel(int cust_id, int table_id, Date date, Time arrival_time) {
        this.cusId = cust_id;
        this.tableId = table_id;
        this.date = date;
        this.arrivalTime = arrival_time;
    }

    public BookingTableModel(int id, int cust_id, int table_id, Date date, Time arrival_time, String status) {
        this.id = id;
        this.cusId = cust_id;
        this.tableId = table_id;
        this.date = date;
        this.arrivalTime = arrival_time;
        this.status = status;
    }

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

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "booking_table_model [id=" + id + ", cust_id=" + cusId + ", table_id=" + tableId + ", date=" + date
				+ ", arrival_time=" + arrivalTime + ", status=" + status + "]";
	}
    

    
}
