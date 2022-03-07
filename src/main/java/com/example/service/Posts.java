/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.entity.Post;
import com.example.util.Badwords;
import com.example.util.MySQLValidator;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iheb
 */
public class Posts implements AdvancedService<Post> {

    @Override
    public Boolean insert(Post p) {
        try {
            String req = "INSERT INTO `posts` ("
                    + "`id`, `poster_id`, `resolved`, `title`, `content`, `tags`, `post_date`"
                    + ") VALUES ("
                    + "?, ?, ?, ?, ?, ?, ?"
                    + ")";

            PreparedStatement ps = connection.prepareStatement(req);
            Integer i = 0;
            ps.setObject(++i, p.getId());
            ps.setObject(++i, p.getPosterId());
            ps.setObject(++i, p.isResolved());
            ps.setString(++i,Badwords.filter(p.getTitle()));
            ps.setString(++i,Badwords.filter(p.getContent()));
            ps.setString(++i, p.getTags());
            ps.setDate(++i, new java.sql.Date(p.getPostDate().getTime()));
           

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public List<Post> find(Integer offset, Integer rowCount, String filter, String order) {
        List<Post> l = new ArrayList<>();
        try {String req = "SELECT * FROM posts"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                l.add(new Post(
                        rs.getObject("id", Integer.class),
                        rs.getObject("poster_id", Integer.class),
                        rs.getObject("resolved", Boolean.class),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("tags"),
                        rs.getDate("post_date")
                       
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return l;
    }

    public Boolean deleteByPosterId(Integer posterId) {
        return delete("`poster_id`=" + posterId);
    }

    public Boolean deleteByPostDate(java.util.Date minPostDate, java.util.Date maxPostDate) {
        return delete("`post_date` BETWEEN " + minPostDate + " AND " + maxPostDate);
    }

    @Override
    public Boolean delete(String filter) {
        try {
            String req = "DELETE FROM `posts`"
                    + MySQLValidator.parseFilter(filter)
                    + ";";
            PreparedStatement ps = connection.prepareStatement(req);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }
    
    public Boolean deleteById(int id) {
        try {
            String req = "DELETE FROM `posts` WHERE `id`="+id+";";
            PreparedStatement ps = connection.prepareStatement(req);

            return     new Comments().deleteByPostId(id) && ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    @Override
    public Boolean modify(Post p) {
        try {
            String req = "UPDATE `posts` SET "
                    + "`poster_id`=?, `resolved`=?, `title`=?, `content`=?, `tags`=?, `post_date`=? WHERE `id`="+p.getId();

            PreparedStatement ps = connection.prepareStatement(req);
            Integer i = 0;
            ps.setObject(++i, p.getPosterId());
            ps.setObject(++i, p.isResolved());
            ps.setString(++i, p.getTitle());
            ps.setString(++i, p.getContent());
            ps.setString(++i, p.getTags());
            ps.setDate(++i, new java.sql.Date(p.getPostDate().getTime()));
           

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
