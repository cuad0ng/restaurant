package com.hcmute.dao;

import com.hcmute.model.CustomerModel;
import com.hcmute.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDao {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public CustomerModel getByID(int id) {
        CustomerModel customer = new CustomerModel();
        try {
            String sql = "select * from customer where id = ?";
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return customer = new CustomerModel(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    public int insert(CustomerModel customer) {
        int id = 0;
        try {
            String sql = "insert into customer(name,address,phone,img) value(?,?,?,?)";
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getPhone());
            ps.setString(4, customer.getImg());
            if (ps.executeUpdate() > 0) {
                id = getNewestID();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public int getNewestID() {
        try {
            String sql = "select id from customer order by id desc";
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getIdByName(String username) {
        try {
            String sql = "select id from customer where name = ?";
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateNamePhone(CustomerModel customer) {
        try {
            String sql = "UPDATE customer SET name = ?, phone = ? WHERE id = ?";
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPhone());
            ps.setInt(3, customer.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
