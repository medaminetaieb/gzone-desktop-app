package com.example.service;

import com.example.entity.Badge;
import com.example.util.MySQLValidator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Badges implements Service<Badge> {
    @Override
    public Boolean insert(Badge b) {
        try {
            String req = "INSERT INTO `badges` ("
                    + "`id`, `game_id`, `title`"
                    + ") VALUES ("
                    + "?, ?, ?"
                    + ")";

            PreparedStatement ps = connection.prepareStatement(req);
            Integer i = 0;
            ps.setObject(++i, b.getId());
            ps.setObject(++i, b.getGameId());
            ps.setObject(++i, b.getTitle());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public List<Badge> find(Integer offset, Integer rowCount, String filter, String order) {
        List<Badge> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `badges`"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                l.add(new Badge(
                        rs.getObject("id", Integer.class),
                        rs.getObject("game_id", Integer.class),
                        rs.getString("title")
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
            String req = "DELETE FROM `badges`"
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
