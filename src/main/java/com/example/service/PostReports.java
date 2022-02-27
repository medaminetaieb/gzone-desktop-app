/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.service;

import com.example.entity.PostReport;
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
public class PostReports implements Service<PostReport> {

    @Override
    public Boolean insert(PostReport ugp) {
        try {
            String req = "INSERT INTO `post_reports` ("
                    + "`id`, `post_id`, `report_id`"
                    + ") VALUES ("
                    + "?, ?, ?"
                    + ")";

            PreparedStatement ps = connection.prepareStatement(req);
            Integer i = 0;
            ps.setObject(++i, ugp.getId());
            ps.setObject(++i, ugp.getPostId());
            ps.setObject(++i, ugp.getReportId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

   
    @Override
    public List<PostReport> find(Integer offset, Integer rowCount, String filter, String order) {
        List<PostReport> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `post_reports`"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                l.add(new PostReport(
                        rs.getObject("id", Integer.class),
                        rs.getObject("post_id", Integer.class),
                        rs.getObject("report_id", Integer.class)
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
            String req = "DELETE FROM `post_reports`"
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
