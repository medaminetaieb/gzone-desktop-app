/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.service;

import com.example.entity.Store;
import com.example.util.MySQLValidator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Firas
 */
public class Stores implements Service<Store> {

    @Override
    public Boolean insert(Store s) {
        try {
            String req = "INSERT INTO `stores` ("
                    + "`id`, `owner_id`, `game_id`, `name`"
                    + ") VALUES ("
                    + "?,?,?,?"
                    + ")";
            PreparedStatement pst = connection.prepareStatement(req);
            Integer i = 0;
            pst.setObject(++i, s.getId(), java.sql.Types.INTEGER);
            pst.setObject(++i, s.getOwnerId(), java.sql.Types.INTEGER);
            pst.setObject(++i, s.getGameId(), java.sql.Types.INTEGER);
            pst.setString(++i, s.getName());

            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return false;
    }

    @Override
    public List<Store> find(Integer offset, Integer rowCount, String filter, String order) {
        List<Store> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM stores"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                l.add(new Store(
                        rs.getObject("id", Integer.class),
                        rs.getObject("owner_id", Integer.class),
                        rs.getObject("game_id", Integer.class),
                        rs.getString("name")

                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return l;
    }

    public List<Store> findAllByOwnerId(Integer ownerId) {
        return find(null, null, "`owner_id`=" + ownerId, null);
    }

    public Store findByName(String storeName) {
        List<Store> l = find(null, null, "`name` REGEXP '" +storeName+"'", null);

        return (!l.isEmpty()) ? l.get(0) : null;

    }

    public Boolean deleteAllByOwnerId(Integer ownerId) {
        return delete("`owner_id`=" + ownerId);
    }

    @Override
    public Boolean delete(String filter) {
        try {
            String req = "DELETE FROM `stores`"
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
