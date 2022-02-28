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
                    + "`badge_id`=?, `start_date`=?, `end_date`=? WHERE `id`=" + happy.getId();
            PreparedStatement st = connection.prepareStatement(req);
            int i = 0;
            st.setObject(++i, happy.getBadgeId(), java.sql.Types.INTEGER);
            st.setDate(++i, new Date(happy.getStartDate().getTime()));
            st.setDate(++i, new Date(happy.getEndDate().getTime()));

            return st.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return false;
    }

    @Override
    public Boolean insert(HappyHour happy) {
        try {
            String req = "INSERT INTO `happy_hours` ("
                    + "`id`, `badge_id`, `start_date`, `end_date`"
                    + ") VALUES ("
                    + "?, ?, ?, ?"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(req);
            int i = 0;
            ps.setObject(++i, happy.getId(), java.sql.Types.INTEGER);
            ps.setObject(++i, happy.getBadgeId(), java.sql.Types.INTEGER);
            ps.setDate(++i, new Date(happy.getStartDate().getTime()));
            ps.setDate(++i, new Date(happy.getEndDate().getTime()));

            return ps.executeUpdate() > 0;

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
                        rs.getObject("badge_id", Integer.class),
                        rs.getDate("start_date"),
                        rs.getDate("end_date")
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
