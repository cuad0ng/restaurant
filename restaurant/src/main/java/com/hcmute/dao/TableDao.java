package com.hcmute.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hcmute.utils.DbUtil;
import com.hcmute.model.DiningTableModel;

public class TableDao {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    public DiningTableModel getTable(int id){
        DiningTableModel table = null;
        try{
            String sql = "select * from dining_table where id = ?";
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
                table = new DiningTableModel(rs.getInt(1),rs.getString(2));
                return  table;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return table;
    }
    public List<DiningTableModel> getListTable(String sql){
        List<DiningTableModel> list = new ArrayList<>();
        try{
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()){
                DiningTableModel table_model = new DiningTableModel(rs.getInt(1),rs.getString(2));
                list.add(table_model);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  list;
    }
    public List<DiningTableModel> getAll(){
    	String sql = "select * from dining_table";
    	List<DiningTableModel> list = getListTable(sql);
    	return list;
    }
    public boolean updateStatus(int id,String state){
        Boolean result = false;
        try{
            String sql = "update dining_table set status = ? where id=?";
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1,state);
            ps.setInt(2,id);
            result = ps.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }
}
