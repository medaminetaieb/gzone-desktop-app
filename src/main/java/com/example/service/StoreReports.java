/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.entity.StoreReport;

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
public class StoreReports implements Service<StoreReport> {

    @Override
    public Boolean insert(StoreReport sr) {
        try {
            String req = "INSERT INTO `store_reports` ("
                    + "`id`, `store_id`, `report_id`"
                    + ") VALUES ("
                    + "?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(req);
            Integer i = 0;
            ps.setObject(++i, sr.getId(), java.sql.Types.INTEGER);
            ps.setObject(++i, sr.getStoreId(), java.sql.Types.INTEGER);
            ps.setObject(++i, sr.getReportId(), java.sql.Types.INTEGER);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    @Override
    public List<StoreReport> find(Integer offset, Integer rowCount, String filter, String order) {
        List<StoreReport> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `store_reports`"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                l.add(new StoreReport(
                        rs.getObject("id", Integer.class),
                        rs.getObject("store_id", Integer.class),
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
            String req = "DELETE FROM `store_reports`"
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
