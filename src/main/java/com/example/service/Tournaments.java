/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.service;

import com.example.entity.Tournament;
import com.example.util.MySQLValidator;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mat
 */
public class Tournaments implements AdvancedService<Tournament> {

    @Override
    public Boolean modify(Tournament t) {
        try {
            String req = "UPDATE `tournaments` SET "
                    + "`admin_id`=?, `game_id`=?, `name`=?, `description`=?, `required_teams`=?, `team_size`=?, `close_requests_date`=?, `approved`=?, `create_date`=? WHERE `id`=" + t.getId();
            PreparedStatement ps = connection.prepareStatement(req);
            Integer i = 0;
            ps.setObject(++i, t.getAdminId(), java.sql.Types.INTEGER);
            ps.setObject(++i, t.getGameId(), java.sql.Types.INTEGER);
            ps.setString(++i, t.getName());
            ps.setString(++i, t.getDescription());
            ps.setObject(++i, t.getRequiredTeams(), java.sql.Types.INTEGER);
            ps.setObject(++i, t.getTeamSize(), java.sql.Types.INTEGER);
            ps.setDate(++i, new Date(t.getCloseRequestsDate().getTime()));
            ps.setObject(++i, t.isApproved(), java.sql.Types.BOOLEAN);
            ps.setDate(++i, new Date(t.getCreateDate().getTime()));

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    @Override
    public Boolean insert(Tournament t) {
        try {
            String req = "INSERT INTO `tournaments` ("
                    + "`id`, `admin_id`, `game_id`, `name`, `description`, `required_teams`, `team_size`, `close_requests_date`, `approved`, `create_date`"
                    + ") VALUES ("
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(req);
            Integer i = 0;
            ps.setObject(++i, t.getId(), java.sql.Types.INTEGER);
            ps.setObject(++i, t.getAdminId(), java.sql.Types.INTEGER);
            ps.setObject(++i, t.getGameId(), java.sql.Types.INTEGER);
            ps.setString(++i, t.getName());
            ps.setString(++i, t.getDescription());
            ps.setObject(++i, t.getRequiredTeams(), java.sql.Types.INTEGER);
            ps.setObject(++i, t.getTeamSize(), java.sql.Types.INTEGER);
            ps.setDate(++i, new Date(t.getCloseRequestsDate().getTime()));
            ps.setObject(++i, t.isApproved(), java.sql.Types.BOOLEAN);
            ps.setDate(++i, new Date(t.getCreateDate().getTime()));

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    @Override
    public List<Tournament> find(Integer offset, Integer rowCount, String filter, String order) {
        List<Tournament> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM tournaments"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                l.add(new Tournament(
                        rs.getObject("id", Integer.class),
                        rs.getObject("admin_id", Integer.class),
                        rs.getObject("game_id", Integer.class),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getObject("required_teams", Integer.class),
                        rs.getObject("team_size", Integer.class),
                        rs.getDate("close_requests_date"),
                        rs.getObject("approved", Boolean.class),
                        rs.getDate("create_date")
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
            String req = "DELETE FROM `tournaments`"
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
