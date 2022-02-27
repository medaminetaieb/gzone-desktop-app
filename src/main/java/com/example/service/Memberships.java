/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.entity.Membership;
import static com.example.service.Service.connection;
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
public class Memberships implements Service<Membership> {

    @Override
    public Boolean insert(Membership m) {
        try {
            String req = "INSERT INTO `memberships` ("
                    + "`id`, `user_id`, `team_id`, `tournament_id`"
                    + ") VALUES ("
                    + "?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(req);
            Integer i = 0;
            ps.setObject(++i, m.getId(), java.sql.Types.INTEGER);
            ps.setObject(++i, m.getUserId(), java.sql.Types.INTEGER);
            ps.setObject(++i, m.getTeamId(), java.sql.Types.INTEGER);
            ps.setObject(++i, m.getTournamentId(), java.sql.Types.INTEGER);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    @Override
    public List<Membership> find(Integer offset, Integer rowCount, String filter, String order) {

        List<Membership> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `memberships`"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);

            while (rs.next()) {
                l.add(new Membership(
                        rs.getObject("id", Integer.class),
                        rs.getObject("User_id", Integer.class),
                        rs.getObject("team_id", Integer.class),
                        rs.getObject("tournament_id", Integer.class)
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
            String req = "DELETE FROM `memberships`"
                    + MySQLValidator.parseFilter(filter);
            Statement s = connection.createStatement();

            return s.executeUpdate(req) > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

}
