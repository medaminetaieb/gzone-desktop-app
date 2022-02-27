/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.service;

import com.example.entity.HappyHour;
import com.example.util.MySQLValidator;
import java.sql.Date;
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
public class HappyHours implements AdvancedService<HappyHour> {

    @Override
    public Boolean modify(HappyHour happy) {
        try {
            String req = "UPDATE `happy_hours` SET "
                    + "`game_id`=?, `start_date`=?, `end_date`=?, `badge`=? WHERE `id`=" + happy.getId();
            PreparedStatement st = connection.prepareStatement(req);
            st.setObject(1, happy.getGameId(), java.sql.Types.INTEGER);
            st.setDate(2, new Date(happy.getStartDate().getTime()));
            st.setDate(3, new Date(happy.getEndDate().getTime()));
            st.setString(4, happy.getBadge());

            return st.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return false;
    }

    @Override
    public Boolean insert(HappyHour h) {
        try {
            String req = "INSERT INTO `happy_hours` ("
                    + "`game_id`, `start_date`, `end_date`, `badge`"
                    + ") VALUES ("
                    + "?, ?, ?, ?"
                    + ")";
            PreparedStatement pst = connection.prepareStatement(req);
            pst.setObject(1, h.getGameId(), java.sql.Types.INTEGER);
            pst.setDate(2, new Date(h.getStartDate().getTime()));
            pst.setDate(3, new Date(h.getEndDate().getTime()));
            pst.setString(4, h.getBadge());

            return pst.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<HappyHour> find(Integer offset, Integer rowCount, String filter, String order) {
        List<HappyHour> myList = new ArrayList<>();
        try {
            String req = "SELECT * FROM `happy_hours`"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                myList.add(new HappyHour(
                        rs.getObject("id", Integer.class),
                        rs.getObject("game_id", Integer.class),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("badge")
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
            String query = "DELETE FROM `happy_hours`"
                    + MySQLValidator.parseFilter(filter)
                    + ";";

            return st.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }
}
