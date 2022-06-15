package com.hcmute.dao;

import com.hcmute.model.InvoiceModel;
import com.hcmute.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class InvoiceDao {
    private DbUtil dbUtil;
    private static final String STATISTIC_SALE_BY_PRODUCT = "select p.id, p.name, sum(p.price * ci.product_amount) as total_cost from cart_item ci, product p, cart c, invoice i where i.cart_id = c.id and c.id = ci.cart_id and p.id = ci.product_id and state = 1  group by p.id;";
    private static final String STATISTIC_SALE_BY_PRODUCT_OPTIONS = "select p.id, p.name, sum(p.price * ci.product_amount) as total_cost from cart_item ci, product p, cart c, invoice i where i.cart_id = c.id and p.id = ci.product_id and c.id = ci.cart_id and state = 1 AND p.name LIKE CONCAT('%', ? , '%') AND i.create_date >= ? AND create_date <= ?  group by p.id;";
    private static final String SELECT_ALL_INVOICE = "SELECT * FROM invoice";
    private static final String SELECT_PART_INVOICE = "SELECT * FROM invoice ORDER BY create_date DESC LIMIT 5";
    private static final String GET_TOTAL_SALE_TODAY = "SELECT SUM(total_cost) AS total_sale_today FROM invoice WHERE  DATE(create_date) = CURRENT_DATE()";
    private static final String GET_TOTAL_SALE_ALL = "SELECT SUM(total_cost) AS total_sale_all FROM invoice";
    private static final String DELETE_INVOICE = "DELETE FROM invoice WHERE id = ?";
    private static final String UPDATE_INVOICE = "UPDATE invoice SET cust_id = ?, emp_id= ?, cart_id =?, total_cost =?, create_date =? where id = ?";
    private static final String SELECT_INVOICE_BY_ID = "SELECT * FROM invoice WHERE id = ?";
    private static final String INSERT_INVOICE = "INSERT INTO invoice (cust_id, emp_id, cart_id, total_cost, create_date) VALUES (?,1,?,?,?)";

    public InvoiceDao() {
        this.dbUtil = new DbUtil();
    }

    public List<HashMap<String, String>> statisticProductOptions(String key, Date fromDate, Date toDate) {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<HashMap<String, String>> statistic_product_options = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = dbUtil.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(STATISTIC_SALE_BY_PRODUCT_OPTIONS);) {
            preparedStatement.setString(1, key);
            preparedStatement.setDate(2, (java.sql.Date) fromDate);
            preparedStatement.setDate(3, (java.sql.Date) toDate);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                HashMap<String, String> temp = new HashMap<>();
                temp.put("name", rs.getString("name"));
                temp.put("total_cost", rs.getString("total_cost"));
                statistic_product_options.add(temp);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return statistic_product_options;
    }

    public List<HashMap<String, String>> statisticProduct() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<HashMap<String, String>> statistic_product = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = dbUtil.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(STATISTIC_SALE_BY_PRODUCT);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                HashMap<String, String> temp = new HashMap<>();
                temp.put("name", rs.getString("name"));
                temp.put("total_cost", rs.getString("total_cost"));
                statistic_product.add(temp);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return statistic_product;
    }

    public int getTotalSaleAll() {
        int total_sale_all = 0;
        // Step 1: Establishing a Connection
        try (Connection connection = dbUtil.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(GET_TOTAL_SALE_ALL);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            if (rs.next()) {
                total_sale_all = rs.getInt("total_sale_all");
            }
        } catch (SQLException e) {
            dbUtil.printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return total_sale_all;
    }

    public int getTotalSaleToday() {
        int total_sale_today = 0;
        // Step 1: Establishing a Connection
        try (Connection connection = dbUtil.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(GET_TOTAL_SALE_TODAY);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            if (rs.next()) {
                total_sale_today = rs.getInt("total_sale_today");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return total_sale_today;
    }

    public boolean updateInvoice(InvoiceModel Invoice) {
        boolean rowUpdated;
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_INVOICE);) {
            statement.setInt(1, Invoice.getCusId());
            statement.setInt(2, Invoice.getEmpId());
            statement.setInt(3, Invoice.getCartId());
            statement.setDouble(4, Invoice.getTotalCost());
            statement.setDate(5, (java.sql.Date) Invoice.getCreateDate());
            statement.setInt(6, Invoice.getId());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }

    public boolean deleteInvoice(int id) {
        boolean rowDeleted;
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_INVOICE);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public InvoiceModel selectInvoiceByID(int _id) {
        InvoiceModel invoice = null;
        // Step 1: Establishing a Connection
        try (Connection connection = dbUtil.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INVOICE_BY_ID);) {
            preparedStatement.setInt(1, _id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                int cust_id = rs.getInt("cust_id");
                int emp_id = rs.getInt("emp_id");
                int cart_id = rs.getInt("cart_id");
                double total_cost = rs.getDouble("total_cost");
                Date create_date = rs.getDate("create_date");
                invoice = new InvoiceModel(id, cust_id, emp_id, cart_id, total_cost, create_date);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return invoice;
    }

    public List<InvoiceModel> selectPartInvoice() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<InvoiceModel> invoices = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = dbUtil.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PART_INVOICE);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                int cust_id = rs.getInt("cust_id");
                int emp_id = rs.getInt("emp_id");
                int cart_id = rs.getInt("cart_id");
                double total_cost = rs.getDouble("total_cost");
                Date create_date = rs.getDate("create_date");

                invoices.add(new InvoiceModel(id, cust_id, emp_id, cart_id, total_cost, create_date));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    public List<InvoiceModel> selectAllInvoice() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<InvoiceModel> invoices = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = dbUtil.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_INVOICE);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                int cust_id = rs.getInt("cust_id");
                int emp_id = rs.getInt("emp_id");
                int cart_id = rs.getInt("cart_id");
                double total_cost = rs.getDouble("total_cost");
                Date create_date = rs.getDate("create_date");

                invoices.add(new InvoiceModel(id, cust_id, emp_id, cart_id, total_cost, create_date));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    public void createInvoice(InvoiceModel invoiceModel) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INVOICE);
        ) {
            preparedStatement.setInt(1, invoiceModel.getCusId());
            preparedStatement.setInt(2, invoiceModel.getCartId());
            preparedStatement.setDouble(3, invoiceModel.getTotalCost());
            preparedStatement.setDate(4, new java.sql.Date(invoiceModel.getCreateDate().getTime()));

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}