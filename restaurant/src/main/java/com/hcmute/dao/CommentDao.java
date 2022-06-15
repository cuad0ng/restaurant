package com.hcmute.dao;

import com.hcmute.model.CommentModel;
import com.hcmute.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private final String SQL_INSERT_NEW_COMMENT  = "INSERT INTO `restaurant_db`.`comment` (`cusId`, `productId`, `comment`) VALUES ( ?, ?, ?);";
    private final String SQL_GET_ALL_COMMENT_BY_PRODUCT_ID = "SELECT * FROM restaurant_db.comment where productId = ?;";
    private final String SQL_UPDATE_COMMENT = "UPDATE `restaurant_db`.`comment` SET comment = ? where id = ?; ";
    private final String SQL_DELETE_COMMENT  = "DELETE FROM `restaurant_db`.`comment`  where id = ?;";

    public CommentDao() {
    }

    public boolean insertNewComment(CommentModel commentModel){
        try{
            connection = DbUtil.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_COMMENT);
            preparedStatement.setInt(1, commentModel.getUserId());
            preparedStatement.setInt(2, commentModel.getProductId());
            preparedStatement.setString(3, commentModel.getComment());
            return preparedStatement.execute();
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteComment(int id){
        try{
            connection = DbUtil.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_COMMENT);
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateComment(int id,String content){
        try{
            connection = DbUtil.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_COMMENT);
            preparedStatement.setInt(2, id);
            preparedStatement.setString(1, content);
            return preparedStatement.executeUpdate() > 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<CommentModel> getALlCommentById(int productId){
        List<CommentModel> list =  new ArrayList<>();

        try{
            connection = DbUtil.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_ALL_COMMENT_BY_PRODUCT_ID);
            preparedStatement.setInt(1, productId);

            ResultSet rs  = preparedStatement.executeQuery();
            while(rs.next()){
                CommentModel commentModel = new CommentModel(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
                list.add(commentModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

}
