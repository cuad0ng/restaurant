package com.hcmute.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static Connection connection;
    private static String url = "jdbc:mysql://localhost:3306/restaurant_db";
    private static String user = "root";
    //private static String password = "p07112001";
    private static String password = "$o_Nic332177";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public static void main(String[] args) {
        try (Connection con = DbUtil.getConnection() ) {
            System.out.println("Connected to MySql Server.");
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Connection Error!");
        }
    }

    public void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
