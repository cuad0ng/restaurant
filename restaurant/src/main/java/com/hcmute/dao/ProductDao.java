package com.hcmute.dao;

import com.hcmute.model.EmployeeModel;
import com.hcmute.model.ProductCategoryModel;
import com.hcmute.model.ProductModel;
import com.hcmute.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private Connection connection;
    private PreparedStatement ps;
    private static final String GET_ALL_PRODUCT_SQL = "SELECT * FROM `restaurant_db`.`product`;";
    private static final String GET_ALL_CATEGORY_ID = "SELECT * FROM `restaurant_db`.`product_category`;";
    private static final String GET_PRODUCT_BY_ID_SQL = "select * from `restaurant_db`.`product`where product.id = ?";
    private static final String GET_8_NEWEST_PRODUCTS = "select * from `restaurant_db`.`product` order by product.id desc limit 8";
    private static final String DELETE_PRODUCT_SQL = "delete from product where id = ?;";
    private static final String UPDATE_PRODUCT_SQL = "update product set name = ?,description= ?, img =?, price =?, amount =?, cate_id =? where id = ?;";
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product" + " (name, description, img, price, amount, cate_id ) VALUES " + " (?, ?, ?, ?, ?, ?);";
    public ProductDao(){

    }

    public List<ProductModel>  getAllProduct() {
        List<ProductModel> result = new ArrayList<>();
        try {
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_PRODUCT_SQL);
            ResultSet rs = ps.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String des = rs.getString("Description");
                String img = rs.getString("img");
                int price = rs.getInt("price");
                int amount = rs.getInt("amount");
                int cate_id = rs.getInt("cate_id");

                result.add(new ProductModel(id, name, des, img, price, amount, cate_id));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ProductModel getProductById(int _id){
        ProductModel product = new ProductModel();
        try{
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(GET_PRODUCT_BY_ID_SQL);
            ps.setInt(1, _id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("Description"));
                product.setImg(rs.getString("img"));
                product.setPrice( rs.getInt("price"));
                product.setAmount(rs.getInt("amount"));
                product.setCate_id(rs.getInt("cate_id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return product;
    }

    public List<ProductModel>  get8NewestProducts() {
        List<ProductModel> result = new ArrayList<>();
        try {
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(GET_8_NEWEST_PRODUCTS);
            ResultSet rs = ps.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String des = rs.getString("Description");
                String img = rs.getString("img");
                int price = rs.getInt("price");
                int amount = rs.getInt("amount");
                int cate_id = rs.getInt("cate_id");

                result.add(new ProductModel(id, name, des, img, price, amount, cate_id));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;

    }
    public boolean deleteProduct(int id) throws SQLException, ClassNotFoundException {
        boolean rowDeleted;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    public boolean updateProduct(ProductModel productModel) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL);) {
            statement.setString(1, productModel.getName());
            statement.setString(2, productModel.getDescription());
            statement.setString(3, productModel.getImg());
            statement.setInt(4, productModel.getPrice());
            statement.setInt(5, productModel.getAmount());
            statement.setInt(6, productModel.getCate_id());
            statement.setInt(7, productModel.getId());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public void insertProduct(ProductModel productModel) throws SQLException {
        System.out.println(INSERT_PRODUCT_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            preparedStatement.setString(1, productModel.getName());
            preparedStatement.setString(2, productModel.getDescription());
            preparedStatement.setString(3, productModel.getImg());
            preparedStatement.setInt(4, productModel.getPrice());
            preparedStatement.setInt(5, productModel.getAmount());
            preparedStatement.setInt(6, productModel.getCate_id());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<ProductCategoryModel>  getAllCategoryId() {
        List<ProductCategoryModel> result = null;
        try {
            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_CATEGORY_ID);
            ResultSet rs = ps.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                result.add(new ProductCategoryModel(id, name));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

}
