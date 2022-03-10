/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.entity.UserLikesDislike;
import com.example.util.MySQLValidator;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samib
 */
public class UserLikesDislikes implements Service<UserLikesDislike> {

    @Override
    public Boolean insert(UserLikesDislike uld) {
        try {
            String req = "INSERT INTO `user_likes_dislikes` ("
                    + "`id`, `user_id`, `store_id`, `post_id`, `comment_id`, `like`"
                    + ") VALUES ("
                    + "?, ?, ?, ?, ?, ?"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(req);
            Integer i = 0;
            ps.setObject(++i, uld.getId(), java.sql.Types.INTEGER);
            ps.setObject(++i, uld.getUserId(), java.sql.Types.INTEGER);
            ps.setObject(++i, uld.getStoreId(), java.sql.Types.INTEGER);
            ps.setObject(++i, uld.getPostId(), java.sql.Types.INTEGER);
            ps.setObject(++i, uld.getCommentId(), java.sql.Types.INTEGER);
            ps.setObject(++i, uld.isLike(), java.sql.Types.BOOLEAN);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    @Override
    public List<UserLikesDislike> find(Integer offset, Integer rowCount, String filter, String order) {
        List<UserLikesDislike> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `user_likes_dislikes`"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                l.add(new UserLikesDislike(
                        rs.getObject("id", Integer.class),
                        rs.getObject("user_id", Integer.class),
                        rs.getObject("store_id", Integer.class),
                        rs.getObject("post_id", Integer.class),
                        rs.getObject("comment_id", Integer.class),
                        rs.getObject("like", Boolean.class)
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return l;
    }

    @Override
    public Boolean delete(String filter) {
        try {
            String req = "DELETE FROM `user_likes_dislikes`"
                    + MySQLValidator.parseFilter(filter)
                    + ";";
            Statement s = connection.createStatement();

            return s.executeUpdate(req) > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }
    public Boolean deleteByPostId(Integer postId){
    
        return delete("`post_id`="+postId);
        
    
    }
    public Boolean deleteByStoreId(Integer storeId){

        return delete("`store_id`="+storeId);


    }


}
