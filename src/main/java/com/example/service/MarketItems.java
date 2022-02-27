/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.service;

import com.example.entity.MarketItem;
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
 * @author Firas
 */
public class MarketItems implements Service<MarketItem> {

    @Override
    public Boolean insert(MarketItem m) {
        try {
            String req = "INSERT INTO `market_items` ("
                    + "`id`, `store_id`, `title`, `description`, `sold`, `post_date`"
                    + ") VALUES ("
                    + "?, ?, ?, ?, ?, ?"
                    + ")";
            PreparedStatement pst = connection.prepareStatement(req);
            Integer i = 0;
            pst.setObject(++i, m.getId(),java.sql.Types.INTEGER);
            pst.setObject(++i, m.getStoreId(),java.sql.Types.INTEGER);
            pst.setString(++i, m.getTitle());
            pst.setString(++i, m.getDescription());
            pst.setObject(++i, m.isSold(),java.sql.Types.BOOLEAN);
            pst.setDate(++i, new Date(m.getPostDate().getTime()));

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<MarketItem> find(Integer offset, Integer rowCount, String filter, String order) {
        List<MarketItem> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `market_items`"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                l.add(new MarketItem(
                        rs.getObject("id", Integer.class),
                        rs.getObject("store_id", Integer.class),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getObject("sold", Boolean.class),
                        rs.getDate("post_date")
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
            String req = "DELETE FROM `market_items`"
                    + MySQLValidator.parseFilter(filter)
                    + ";";
            PreparedStatement pst = connection.prepareStatement(req);

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }
}
