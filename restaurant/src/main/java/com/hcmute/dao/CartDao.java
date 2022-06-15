package com.hcmute.dao;


import com.hcmute.model.CartModel;
import com.hcmute.model.EmployeeModel;
import com.hcmute.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartDao {
    private static final String INSERT_CART = "INSERT INTO cart (cust_id, state) VALUES (?, 0)";
    private static final String INSERT_CART_ITEM = "INSERT INTO cart_item (cart_id, product_id, product_amount) VALUES (?, ?, ?)";
    private static final String SELECT_CART = "select cart.id, cust_id, state, cart_item.product_id, product_amount, img, name, price from cart, cart_item, product where cart.id = cart_item.cart_id and cart_item.product_id = product.id and cust_id = ? and state = 0";
    private static final String EXIST_CART = "select * from cart where cust_id = ? and state = 0";
    private static final String GET_ID_CART = "select id from cart where cust_id = ?";
    private static final String UPDATE_CART = "update cart_item set product_amount = ? where cart_id = ? and product_id = ?";
    private static final String DELETE_CART = "delete from cart_item where cart_id = ? and product_id = ?";
    private static final String TOTAL_AMOUNT = "select sum(product_amount) as totalAmount from cart, cart_item where cart.id = cart_item.cart_id and cust_id = ? and state = 0;";
    private static final String UPDATE_STATUS_CART = "UPDATE cart set state = 1 WHERE id = ?";

    public boolean existCart(int customer_id) {
        boolean exist = false;
        // Step 1: Establishing a Connection
        try (Connection connection = DbUtil.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(EXIST_CART);) {
            preparedStatement.setInt(1, customer_id);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                exist = true;
                return exist;

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return exist;

    }

    public void createCart(CartModel itemCart) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CART);
        ) {
            preparedStatement.setInt(1, itemCart.getCusId());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getIdCart(int customer_id) {
        int cart_id = 0;
        // Step 1: Establishing a Connection
        try (Connection connection = DbUtil.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ID_CART);) {
            preparedStatement.setInt(1, customer_id);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                cart_id = id;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cart_id;


    }

    public void insertCart(CartModel cart) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CART_ITEM);
        ) {
            preparedStatement.setInt(1, cart.getId());
            preparedStatement.setInt(2, cart.getProductId());
            preparedStatement.setInt(3, cart.getProductAmount());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean updateCart(CartModel cart) {
        boolean rowUpdated;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CART);) {
            statement.setInt(1, cart.getProductAmount());
            statement.setInt(2, cart.getId());
            statement.setInt(3, cart.getProductId());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }

    public List<CartModel> selectCart(int customer_id) {

        List<CartModel> cart = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = DbUtil.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CART);) {
            preparedStatement.setInt(1, customer_id);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                int cust_id = rs.getInt("cust_id");
                int status = rs.getInt("state");
                int product_id = rs.getInt("product_id");
                int product_amount = rs.getInt("product_amount");
                String img = rs.getString("img");
                String product_name = rs.getString("name");
                int product_price = rs.getInt("price");
                CartModel itemCart = new CartModel(id, cust_id, product_id, product_amount, status, img, product_name, product_price);
                cart.add(itemCart);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cart;
    }

    public boolean deleteCart(int cart_id, int product_id) {
        boolean rowDeleted;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CART);) {
            statement.setInt(1, cart_id);
            statement.setInt(2, product_id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public int totalAmount(int customer_id) {
        int totalAmount = 0;
        List<CartModel> cart = selectCart(customer_id);
        for (CartModel itemCart : cart) {
            totalAmount += itemCart.getProductAmount();
        }
        return totalAmount;
    }

    public double totalPrice(int customer_id) {
        double totalPrice = 0;
        List<CartModel> cart = selectCart(customer_id);
        for (CartModel itemCart : cart) {
            totalPrice += itemCart.getProduct_price() * itemCart.getProductAmount();
        }
        return totalPrice;
    }

    public boolean updateStatusCart(int cart_id) {
        boolean rowUpdated;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS_CART);) {
            statement.setInt(1, cart_id);

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }
}
