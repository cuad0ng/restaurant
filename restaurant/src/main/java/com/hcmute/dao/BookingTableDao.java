package com.hcmute.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hcmute.utils.DbUtil;
import com.hcmute.model.BookingTableModel;

public class BookingTableDao {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    public boolean insert(BookingTableModel booking){
        Boolean result = false;
        try {
            String sql ="insert into booking_table (cust_id,table_id,arrival_date,arrival_time,booking_status) \n" +
                    "values(?,?,?,?,'reserving')";
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,booking.getCusId());
            ps.setInt(2,booking.getTableId());
            ps.setDate(3,booking.getDate());
            ps.setTime(4,booking.getArrivalTime());
            result = ps.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public List<BookingTableModel> getList(String sql){
        List<BookingTableModel> list = new ArrayList<>();
        try{
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                BookingTableModel model = new BookingTableModel(rs.getInt(1),rs.getInt(2),
                        rs.getInt(3),rs.getDate(4),rs.getTime(5),rs.getString(6));
                list.add(model);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public List<BookingTableModel> getAll(){
        String sql = "select * from booking_table order by arrival_date desc, arrival_time asc";
        return  getList(sql);
    }
    public BookingTableModel get(int id){
        BookingTableModel model = null;
        try{
            String sql = "select * from booking_table where id = ?";
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while(rs.next()){
                return new BookingTableModel(rs.getInt(1),rs.getInt(2),
                        rs.getInt(3),rs.getDate(4),rs.getTime(5),rs.getString(6));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return model;
    }
    public boolean updateState(int id,String state){
        Boolean result = false;
        try{
            String sql = "update booking_table set booking_status = ? where id = ?";
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1,state);
            ps.setInt(2,id);
            result = ps.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public List<BookingTableModel> seachByCustomerPhone(String phone){
        String sql = "select * from booking_table where cust_id in (select id from customer where phone =?)";
        List<BookingTableModel> list = new ArrayList<>();
        try{
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1,phone);
            rs = ps.executeQuery();
            while(rs.next()){
                BookingTableModel model = new BookingTableModel(rs.getInt(1),rs.getInt(2),
                        rs.getInt(3),rs.getDate(4),rs.getTime(5),rs.getString(6));
                list.add(model);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
