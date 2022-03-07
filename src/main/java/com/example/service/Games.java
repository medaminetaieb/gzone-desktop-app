/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.service;

import com.example.entity.Game;
import com.example.util.MySQLValidator;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chayma
 */
public class Games implements AdvancedService<Game> {

    @Override
    public Boolean insert(Game g) {
        try {
            String req = "INSERT INTO `games` ("
                    + "`name`, `photo_url`, `description`"
                    + ") VALUES ("
                    + "?, ?, ?"
                    + ")";
            PreparedStatement pst = connection.prepareStatement(req);
            pst.setString(1, g.getName());
            pst.setString(2, g.getPhotoUrl());
            pst.setString(3, g.getDescription());

            
       
            return pst.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }

    @Override
    public Boolean modify(Game g) {
        try {
            String req = "UPDATE `games` SET "
                    + "`name`=?, `photo_url`=?, `description`=? WHERE `id`=" + g.getId();
            PreparedStatement st = connection.prepareStatement(req);
            st.setString(1, g.getName());
            st.setString(2, g.getPhotoUrl());
            st.setString(3, g.getDescription());
            return st.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return false;
    }

    @Override
    public List<Game> find(Integer offset, Integer rowCount, String filter, String order) {
        List<Game> myList = new ArrayList<>();
        try {
            String req = "SELECT * FROM `games`"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                myList.add(new Game(
                        rs.getObject("id", Integer.class),
                        rs.getString("name"),
                        rs.getString("photo_url"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }

    @Override
    public Boolean delete(String filter) {
        try {
            Statement st = connection.createStatement();
            String req = "DELETE FROM `games`"
                    + MySQLValidator.parseFilter(filter)
                    + ";";

            return st.executeUpdate(req) > 0;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }
}
