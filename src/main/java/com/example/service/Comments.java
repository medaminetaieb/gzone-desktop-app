/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.entity.Comment;
import com.example.util.Badwords;
import com.example.util.MySQLValidator;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iheb
 */
public class Comments implements Service<Comment> {
    
    @Override
    public Boolean insert(Comment c) {
        try {
            String req = "INSERT INTO `comments` ("
                    + "`id`, `post_id`, `commenter_id`, `comment_body`, `comment_date`"
                    + ") VALUES ("
                    + "?, ?, ?, ?, ?"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(req);
            int i = 0 ;
            ps.setObject(++i, c.getId(), java.sql.Types.INTEGER);
            ps.setObject(++i, c.getPostId(), java.sql.Types.INTEGER);
            ps.setObject(++i, c.getCommenterId(), java.sql.Types.INTEGER);
            ps.setString(++i, Badwords.filter(c.getCommentBody()));
            ps.setDate(++i, new java.sql.Date(c.getCommentDate().getTime()));
            
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    @Override
    public List<Comment> find(Integer offset, Integer rowCount, String filter, String order) {
        List<Comment> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `comments`"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                l.add(new Comment(
                        rs.getObject("id", Integer.class),
                        rs.getObject("post_id", Integer.class),
                        rs.getObject("commenter_id", Integer.class),
                        rs.getString("comment_body"),
                        rs.getDate("comment_date")
                        
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return l;
    }
    
    public Boolean deleteByPostId(Integer postId) {
        return delete("`post_id`=" + postId);
    }
    
    @Override
    public Boolean delete(String filter) {
        try {
            String req = "DELETE FROM `comments`"
                    + MySQLValidator.parseFilter(filter)
                    + ";";
            Statement s = connection.createStatement();
            
            return s.executeUpdate(req) > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return false;
    }
}
