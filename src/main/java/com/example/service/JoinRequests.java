/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.entity.JoinRequest;
import com.example.util.MySQLValidator;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samib
 */
public class JoinRequests implements Service<JoinRequest> {

    public Boolean respondToJoinRequestById(Integer id, Boolean response) {
        try {
            String req = "UPDATE `join_requests` SET "
                    + "`accepted`=?, `response_date`=? WHERE id=" + id;
            PreparedStatement pst = connection.prepareStatement(req);

            Integer i = 0;

            pst.setObject(++i, response,java.sql.Types.BOOLEAN);
            pst.setDate(++i, new java.sql.Date((new java.util.Date()).getTime()));

            return pst.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return false;
    }

    @Override
    public Boolean insert(JoinRequest jr) {
        try {
            String req = "INSERT INTO `join_requests` ("
                    + "`id`, `user_id`, `team_id`, `tournament_id`, `message`, `request_date`, `accepted`, `response_date`, `invitation`"
                    + ") VALUES ("
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?"
                    + ")";
            PreparedStatement pst = connection.prepareStatement(req);

            Integer i = 0;

            pst.setObject(++i, jr.getId(), java.sql.Types.INTEGER);
            pst.setObject(++i, jr.getUserId(), java.sql.Types.INTEGER);
            pst.setObject(++i, jr.getTeamId(), java.sql.Types.INTEGER);
            pst.setObject(++i, jr.getTournamentId());
            pst.setString(++i, jr.getMessage());
            pst.setDate(++i, new java.sql.Date(jr.getRequestDate().getTime()));
            pst.setObject(++i, jr.isAccepted(), java.sql.Types.BOOLEAN);
            pst.setDate(++i, (jr.getResponseDate() == null)? null : new java.sql.Date(jr.getResponseDate().getTime()));
            pst.setObject(++i, jr.isInvitation(), java.sql.Types.BOOLEAN);

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    @Override
    public List<JoinRequest> find(Integer offset, Integer rowCount, String filter, String order) {
        List<JoinRequest> joinRequestList = new ArrayList<>();
        try {
            String req = "SELECT * FROM `join_requests`"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                JoinRequest jr = new JoinRequest();

                jr.setId(rs.getObject("id", Integer.class));
                jr.setUserId(rs.getObject("user_id", Integer.class));
                jr.setTeamId(rs.getObject("team_id", Integer.class));
                jr.setTournamentId(rs.getObject("tournament_id", Integer.class));
                jr.setMessage(rs.getString("message"));
                jr.setRequestDate(rs.getDate("request_date"));
                jr.setAccepted(rs.getObject("accepted", Boolean.class));
                jr.setResponseDate(rs.getDate("response_date"));
                jr.setInvitation(rs.getObject("invitation", Boolean.class));

                joinRequestList.add(jr);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return joinRequestList;
    }

    @Override
    public Boolean delete(String filter) {
        try {
            String req = "DELETE FROM `join_requests`"
                    + MySQLValidator.parseFilter(filter)
                    + ";";
            Statement s = connection.createStatement();

            return s.executeUpdate(req) > 0;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }

}
