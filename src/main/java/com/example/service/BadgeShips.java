package com.example.service;

import com.example.entity.BadgeShip;
import com.example.util.MySQLValidator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BadgeShips implements Service<BadgeShip> {
    @Override
    public Boolean insert(BadgeShip bs) {
        try {
            String req = "INSERT INTO `badge_ships` ("
                    + "`id`, `badge_id`, `user_id`"
                    + ") VALUES ("
                    + "?, ?, ?"
                    + ")";

            PreparedStatement ps = connection.prepareStatement(req);
            Integer i = 0;
            ps.setObject(++i, bs.getId());
            ps.setObject(++i, bs.getBadgeId());
            ps.setObject(++i, bs.getUserId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public List<BadgeShip> find(Integer offset, Integer rowCount, String filter, String order) {
        List<BadgeShip> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `badge_ships`"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                l.add(new BadgeShip(
                        rs.getObject("id", Integer.class),
                        rs.getObject("badge_id", Integer.class),
                        rs.getObject("user_id", Integer.class)
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
            String req = "DELETE FROM `badge_ships`"
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
