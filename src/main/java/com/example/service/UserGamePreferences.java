/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.service;

import com.example.entity.UserGamePreference;
import com.example.util.MySQLValidator;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class UserGamePreferences implements Service<UserGamePreference> {

    @Override
    public Boolean insert(UserGamePreference ugp) {
        try {
            String req = "INSERT INTO `user_game_preferences` ("
                    + "`id`, `user_id`, `game_id`"
                    + ") VALUES ("
                    + "?, ?, ?"
                    + ")";

            PreparedStatement ps = connection.prepareStatement(req);
            Integer i = 0;
            ps.setObject(++i, ugp.getId());
            ps.setObject(++i, ugp.getUserId());
            ps.setObject(++i, ugp.getGameId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

   
    @Override
    public List<UserGamePreference> find(Integer offset, Integer rowCount, String filter, String order) {
        List<UserGamePreference> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `user_game_preferences`"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                l.add(new UserGamePreference(
                        rs.getObject("id", Integer.class),
                        rs.getObject("user_id", Integer.class),
                        rs.getObject("game_id", Integer.class)
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
            String req = "DELETE FROM `user_game_preferences`"
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
