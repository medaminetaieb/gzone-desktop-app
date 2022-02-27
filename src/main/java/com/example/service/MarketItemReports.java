/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.service;

import com.example.entity.MarketItemReport;
import com.example.util.MySQLValidator;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mat
 */
public class MarketItemReports implements Service<MarketItemReport> {

    @Override
    public Boolean insert(MarketItemReport mir) {
        try {
            String req = "INSERT INTO `market_item_reports` ("
                    + "`id`, `market_item_id`, `report_id`"
                    + ") VALUES ("
                    + "? , ?, ?"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(req);
            int i = 0;
            ps.setObject(++i, mir.getId(), java.sql.Types.INTEGER);
            ps.setObject(++i, mir.getMarketItemId(), java.sql.Types.INTEGER);
            ps.setObject(++i, mir.getReportId(), java.sql.Types.INTEGER);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    @Override
    public List<MarketItemReport> find(Integer offset, Integer rowCount, String filter, String order) {
        List<MarketItemReport> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `market_item_reports`"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                l.add(new MarketItemReport(
                        rs.getObject("id", Integer.class),
                        rs.getObject("market_item_id", Integer.class),
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
            String req = "DELETE FROM `market_item_reports`"
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
