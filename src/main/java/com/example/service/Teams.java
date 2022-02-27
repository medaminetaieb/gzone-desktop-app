/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.entity.Team;
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
public class Teams implements AdvancedService<Team> {

    @Override
    public Boolean modify(Team t) {
        try {
            String req = "UPDATE `teams` SET "
                    + "`admin_id`=?, `photo_url`=?, `name`=?, `description`=?, `team_size`=?, `requestable`=?, `invitable`=? WHERE `id`=" + t.getId();
            PreparedStatement pst = connection.prepareStatement(req);
            Integer i = 0;
            pst.setObject(++i, t.getAdminId(),java.sql.Types.INTEGER);
            pst.setString(++i, t.getPhotoURL());
            pst.setString(++i, t.getName());
            pst.setString(++i, t.getDescription());
            pst.setObject(++i, t.getTeamSize(),java.sql.Types.INTEGER);
            pst.setObject(++i, t.isRequestable(),java.sql.Types.BOOLEAN);
            pst.setObject(++i, t.isInvitable(),java.sql.Types.BOOLEAN);

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    @Override
    public Boolean insert(Team t) {
        try {
            String requete = "INSERT INTO `teams` ("
                    + "`id`, `admin_id`, `photo_url`, `name`, `description`, `game_id`, `team_size`, `requestable`, `invitable`, `create_date`"
                    + ") VALUES ("
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
                    + ")";

            PreparedStatement pst = connection.prepareStatement(requete);

            Integer i = 0;

            pst.setObject(++i, t.getId(),java.sql.Types.INTEGER);
            pst.setObject(++i, t.getAdminId(),java.sql.Types.INTEGER);
            pst.setString(++i, t.getPhotoURL());
            pst.setString(++i, t.getName());
            pst.setString(++i, t.getDescription());
            pst.setObject(++i, t.getGameId(),java.sql.Types.INTEGER);
            pst.setObject(++i, t.getTeamSize(),java.sql.Types.INTEGER);
            pst.setObject(++i, t.isRequestable(),java.sql.Types.BOOLEAN);
            pst.setObject(++i, t.isInvitable(),java.sql.Types.BOOLEAN);
            pst.setDate(++i, new java.sql.Date(t.getCreateDate().getTime()));

            return pst.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return false;
    }

    @Override
    public List<Team> find(Integer offset, Integer rowCount, String filter, String order) {
        List<Team> teamList = new ArrayList<>();
        try {
            String req = "SELECT * FROM teams"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                Team team = new Team();

                team.setId(rs.getObject("id", Integer.class));
                team.setAdminId(rs.getObject("admin_id", Integer.class));
                team.setPhotoURL(rs.getString("photo_url"));
                team.setName(rs.getString("name"));
                team.setDescription(rs.getString("description"));
                team.setGameId(rs.getObject("game_id", Integer.class));
                team.setTeamSize(rs.getObject("team_size", Integer.class));
                team.setRequestable(rs.getObject("requestable", Boolean.class));
                team.setInvitable(rs.getObject("invitable", Boolean.class));
                team.setCreateDate(rs.getDate("create_date"));

                teamList.add(team);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return teamList;
    }

    public Boolean deleteByAdminId(Integer adminId) {
        return delete("`admin_id`=" + adminId);
    }

    @Override
    public Boolean delete(String filter) {
        try {
            String req = "DELETE FROM `teams`"
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
