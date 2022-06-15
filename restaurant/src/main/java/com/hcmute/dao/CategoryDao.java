package com.hcmute.dao;

import com.hcmute.model.CartModel;
import com.hcmute.model.ProductCategoryModel;
import com.hcmute.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    public static final String SELECT_CATEGORY = "SELECT * FROM product_category";
    public static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM product_category WHERE id = ?";
    public static final String INSERT_CATEGORY = "INSERT INTO product_category (name) VALUES (?)";
    public static final String UPDATE_CATEGORY = "UPDATE product_category SET name = ? WHERE id = ?";
    public static final String DELETE_CATEGORY = "DELETE FROM product_category WHERE id = ?";

    public ProductCategoryModel selectCategoryByID(int id) {
        ProductCategoryModel category = null;
        // Step 1: Establishing a Connection
        try (Connection connection = DbUtil.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int _id = rs.getInt("id");
                String name = rs.getString("name");
                category = new ProductCategoryModel(_id, name);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return category;
    }

    public List<ProductCategoryModel> selectCategory() {
        List<ProductCategoryModel> categories = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = DbUtil.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                ProductCategoryModel category = new ProductCategoryModel(id, name);
                categories.add(category);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public void insertCategory(ProductCategoryModel category) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY);
        ) {

            preparedStatement.setString(1, category.getName());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean updateCategory(ProductCategoryModel category) {
        boolean rowUpdated;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY);) {
            statement.setString(1, category.getName());
            statement.setInt(2, category.getId());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }

    public boolean deleteCategory(int id) {
        boolean rowDeleted;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }
}
