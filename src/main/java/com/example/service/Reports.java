/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.service;

import com.example.entity.Report;
import com.example.entity.Subject;
import com.example.util.MySQLValidator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mahdi
 */
public class Reports implements Service<Report> {

    @Override
    public Boolean insert(Report r) {
        try {
            String req = "INSERT INTO `reports`("
                    + "`reporter_id`, `subject`, `head`, `body`, `report_date`"
                    + ") VALUES ("
                    + "?, ?, ?, ?, ?"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(req);
            int i = 0;
            ps.setObject(++i, r.getReporterId(), java.sql.Types.INTEGER);
            ps.setString(++i, r.getSubject().toString());
            ps.setString(++i, r.getHead());
            ps.setString(++i, r.getBody());
            ps.setDate(++i, new Date(r.getReportDate().getTime()));

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }

    @Override
    public List<Report> find(Integer offset, Integer rowCount, String filter, String order) {
        List<Report> lr = new ArrayList<>();
        try {
            String req = "SELECT * FROM reports"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                lr.add(new Report(
                        rs.getObject("id", Integer.class),
                        rs.getObject("reporter_id", Integer.class),
                        Subject.valueOf(rs.getString("subject")),
                        rs.getString("head"),
                        rs.getString("body"),
                        rs.getDate("report_date")
                ));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return lr;

    }

    @Override
    public Boolean delete(String filter) {
        try {
            String req = "DELETE FROM `reports`"
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
