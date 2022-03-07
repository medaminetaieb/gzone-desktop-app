/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.entity.User;
import com.example.entity.Role;
import static com.example.util.CryptWithMD5.cryptWithMD5;


import com.example.util.MySQLValidator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Mahdi
 */
public class Users implements AdvancedService<User> {

    @Override
    public Boolean modify(User u) {
        try {
            String req = "UPDATE `users` SET "
                    + "`phone_number`=?, `email`=?, `username`=?, `password`=?, `photo_url`=?, `full_name`=?, `bio`=?, `birth_date`=?, `join_date`=?, `invitable`=?, `role`=?"
                    + " WHERE `id`=" + u.getId();
            PreparedStatement ps = connection.prepareStatement(req);
            int i = 0;
            ps.setString(++i, u.getPhoneNumber());
            ps.setString(++i, u.getEmail());
            ps.setString(++i, u.getUsername());
            ps.setString(++i, cryptWithMD5(u.getPassword()));
            ps.setString(++i, u.getPhotoURL());
            ps.setString(++i, u.getFullName());
            ps.setString(++i, u.getBio());
            ps.setDate(++i, new java.sql.Date(u.getBirthDate().getTime()));
            ps.setDate(++i, u.getJoinDate() != null ? new java.sql.Date(u.getJoinDate().getTime()) : null);
            ps.setObject(++i, u.isInvitable(), java.sql.Types.BOOLEAN);
            ps.setString(++i, u.getRole().toString());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }

    @Override
    public Boolean insert(User u) {
        try {
            String req = "INSERT INTO `users` ("
                    + "`id`, `phone_number`, `email`, `username`, `password`, `photo_url`, `full_name`, `bio`, `birth_date`, `join_date`, `invitable`, `role`"
                    + ") VALUES ("
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(req);
            Integer i = 0;
            ps.setObject(++i, u.getId(), java.sql.Types.INTEGER);
            ps.setString(++i, u.getPhoneNumber());
            ps.setString(++i, u.getEmail());
            ps.setString(++i, u.getUsername());
            ps.setString(++i, cryptWithMD5(u.getPassword()));
            ps.setString(++i, u.getPhotoURL());
            ps.setString(++i, u.getFullName());
            ps.setString(++i, u.getBio());
            ps.setDate(++i, new java.sql.Date(u.getBirthDate().getTime()));
            ps.setDate(++i, u.getJoinDate() != null ? new java.sql.Date(u.getJoinDate().getTime()) : null);
            ps.setObject(++i, u.isInvitable(), java.sql.Types.BOOLEAN);
            ps.setString(++i, u.getRole().toString());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }

    @Override
    public List<User> find(Integer offset, Integer rowCount, String filter, String order) {
        List<User> lu = new ArrayList<>();
        try {
            String req = "SELECT * FROM `users`"
                    + MySQLValidator.parseFilter(filter)
                    + MySQLValidator.parseOrder(order)
                    + MySQLValidator.parseLimit(offset, rowCount)
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                lu.add(new User(
                        rs.getObject("id", Integer.class),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("photo_url"),
                        rs.getString("full_name"),
                        rs.getString("bio"),
                        rs.getDate("birth_date"),
                        rs.getDate("join_date"),
                        rs.getObject("invitable", Boolean.class),
                        Role.valueOf(rs.getString("role"))
                        ));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return lu;
    }

    @Override
    public Boolean delete(String filter) {
        try {
            String req = "DELETE FROM `users`"
                    + MySQLValidator.parseFilter(filter)
                    + ";";
            Statement s = connection.createStatement();

            return s.executeUpdate(req) > 0;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }

    public Integer checklogin(String username, String password) {
        try {
            
            Statement st = connection.createStatement();
            String query = "SELECT `id` FROM `users` WHERE `username`='" + username + "' AND `password`='" + cryptWithMD5(password) + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()){
                return rs.getObject(1,Integer.class);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<User> sortByDate() {
        List<User> users = findAll();
        List<User> result = users.stream().sorted(Comparator.comparing(User::getBirthDate)).collect(Collectors.toList());
        return result;

    }

    public List<User> sortById() {
        List<User> users = findAll();
        List<User> result = users.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
        return result;
    }

    public List<User> sortByNom() {
        List<User> users = findAll();
        List<User> result = users.stream().sorted(Comparator.comparing(User::getFullName)).collect(Collectors.toList());
        return result;
    }
}
