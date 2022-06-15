package com.hcmute.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hcmute.utils.DbUtil;
import com.hcmute.model.EmployeeModel;

public class EmployeeDao {
    DbUtil ddUtil;
    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee" + "  (name, address, phone, salary, img ) VALUES "
            + " (?, ?, ?, ?, ?);";

    private static final String SELECT_EMPLOYEE_BY_ID = "select id,name,address,phone,salary,img from employee where id =?";
    private static final String SELECT_ALL_EMPLOYEE = "select * from employee";
    private static final String DELETE_EMPLOYEE_SQL = "delete from employee where id = ?;";
    private static final String UPDATE_EMPLOYEE_SQL = "update employee set name = ?,address= ?, phone =?, salary =?, img =? where id = ?;";

    public EmployeeDao() {}


    public void insertEmployee(EmployeeModel empModel) throws SQLException {
        System.out.println(INSERT_EMPLOYEE_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = ddUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {
            preparedStatement.setString(1, empModel.getName());
            preparedStatement.setString(2, empModel.getAddress());
            preparedStatement.setString(3, empModel.getPhone());
            preparedStatement.setInt(4, empModel.getSalary());
            preparedStatement.setString(5, empModel.getImg());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            ddUtil.printSQLException((SQLException) e);
        }
    }


    public EmployeeModel selectEmployee(int id) {
        EmployeeModel employee = null;
        // Step 1: Establishing a Connection
        try (Connection connection = ddUtil.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int salary = rs.getInt("salary");
                String country = rs.getString("img");
                employee = new EmployeeModel(id, name, address, phone, salary, country);
            }
        } catch (SQLException | ClassNotFoundException e) {
            ddUtil.printSQLException((SQLException) e);
        }
        return employee;
    }

    public List<EmployeeModel> selectAllEmployee() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<EmployeeModel> employees = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = ddUtil.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEE);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int salary = rs.getInt("salary");
                String img = rs.getString("img");
                employees.add(new EmployeeModel(id, name, address, phone, salary, img));
                System.out.println(employees.toString());
            }
        } catch (SQLException | ClassNotFoundException e) {
            ddUtil.printSQLException((SQLException) e);
        }
        return employees;
    }

    public boolean deleteEmployee(int id) throws SQLException, ClassNotFoundException {
        boolean rowDeleted;
        try (Connection connection = ddUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateEmployee(EmployeeModel Employee) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = ddUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL);) {
            statement.setString(1, Employee.getName());
            statement.setString(2, Employee.getAddress());
            statement.setString(3, Employee.getPhone());
            statement.setInt(4, Employee.getSalary());
            statement.setString(5, Employee.getImg());
            statement.setInt(6, Employee.getId());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }


}
