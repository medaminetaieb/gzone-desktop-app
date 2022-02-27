/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.service;

import com.example.entity.Match;
import com.example.util.MySQLValidator;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mat
 */
public class Matches implements AdvancedService<Match> {

    @Override
    public Boolean modify(Match m) {
        try {
            String req = "UPDATE `matches` SET "
                    + "`tournament_id`=?, `start_time`=?, `round`=?, `team1_id`=?, `team2_id`=?, `winner_team_id`=? WHERE `id`=" + m.getId();
            PreparedStatement ps = connection.prepareStatement(req);
            Integer i = 0;
            ps.setObject(++i, m.getTournamentId(),java.sql.Types.INTEGER);
            ps.setDate(++i, new Date(m.getStartTime().getTime()));
            ps.setObject(++i, m.getRound(),java.sql.Types.INTEGER);
            ps.setObject(++i, m.getTeam1Id(),java.sql.Types.INTEGER);
            ps.setObject(++i, m.getTeam2Id(),java.sql.Types.INTEGER);
            ps.setObject(++i, m.getWinnerTeamId(),java.sql.Types.INTEGER);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean insert(Match m) {
        try {
            String req = "INSERT INTO `matches` ("
                    + "`id`, `tournament_id`, `start_time`, `round`, `team1_id`, `team2_id`, `winner_team_id`"
                    + ") VALUES ("
                    + "?, ?, ?, ?, ?, ?, ?"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(req);
            Integer i = 0;
            ps.setObject(++i, m.getId(),java.sql.Types.INTEGER);
            ps.setObject(++i, m.getTournamentId(),java.sql.Types.INTEGER);
            ps.setDate(++i, new Date(m.getStartTime().getTime()));
            ps.setObject(++i, m.getRound(),java.sql.Types.INTEGER);
            ps.setObject(++i, m.getTeam1Id(),java.sql.Types.INTEGER);
            ps.setObject(++i, m.getTeam2Id(),java.sql.Types.INTEGER);
            ps.setObject(++i, m.getWinnerTeamId(),java.sql.Types.INTEGER);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Match> find(Integer offset, Integer rowCount, String filter, String order) {
        List<Match> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `matches`"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                l.add(new Match(
                        rs.getObject("id", Integer.class),
                        rs.getObject("tournament_id", Integer.class),
                        rs.getDate("start_time"),
                        rs.getObject("round", Integer.class),
                        rs.getObject("team1_id", Integer.class),
                        rs.getObject("team2_id", Integer.class),
                        rs.getObject("winner_team_id", Integer.class)
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
            String req = "DELETE FROM `matches`"
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
