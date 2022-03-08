/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.service;

import com.example.entity.TournamentReport;
import com.example.util.MySQLValidator;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author mat
 */
public class TournamentReports implements Service<TournamentReport> {

    @Override
    public Boolean insert(TournamentReport tr) {
        try {
            String req = "INSERT INTO `tournament_reports` ("
                    + "`id`, `tournament_id`, `report_id`"
                    + ") VALUES ("
                    + "? , ?, ?"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(req);
            int i = 0;
            ps.setObject(++i, tr.getId(), java.sql.Types.INTEGER);
            ps.setObject(++i, tr.getTournamentId(), java.sql.Types.INTEGER);
            ps.setObject(++i, tr.getReportId(), java.sql.Types.INTEGER);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    @Override
    public List<TournamentReport> find(Integer offset, Integer rowCount, String filter, String order) {
        List<TournamentReport> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `tournament_reports`"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                l.add(new TournamentReport(
                        rs.getObject("id", Integer.class),
                        rs.getObject("tournament_id", Integer.class),
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
            String req = "DELETE FROM `tournament_reports`"
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
