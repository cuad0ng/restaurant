package com.hcmute.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hcmute.utils.DbUtil;
import com.hcmute.model.AccountModel;

public class AccountDao {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public AccountModel getAccount(String username, String password) {
        AccountModel account = null;
        try {
            String sql = "select * from account where username = ? and password = ?";
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while ((rs.next())) {
                account = new AccountModel();
                account.setUsername(rs.getString(1));
                account.setPassword(rs.getString(2));
                account.setUsertype(rs.getString(3));
                account.setCustomer_id(rs.getInt("customer_id"));
                account.setId(rs.getInt(4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    public Boolean insert(AccountModel account) {
        Boolean result = false;
        try {
            String sql = "insert into account (username,password,usertype) values(?,?,?)";
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getUsertype());
            result = (ps.executeUpdate() == 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void update(AccountModel acc) {
        try {
            String sql = "update account set username=?, password = ? \n" +
                    "where id=?";
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, acc.getUsername());
            ps.setString(2, acc.getPassword());
            ps.setInt(3, acc.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCustomerID(int customer_id, String username) {
        try {
            String sql = "update account set customer_id = ? \n" +
                    "where username=?";
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, customer_id);
            ps.setString(2, username);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AccountModel model = new AccountModel("test", "123", "user");
        AccountDao dao = new AccountDao();
        Boolean r = dao.insert(model);
        System.out.println(r.toString());
    }
}
