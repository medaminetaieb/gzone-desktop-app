/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.entity.User;
import com.example.entity.Role;
import static com.example.util.EncryptPassword.cryptWithMD5;
import com.example.util.MySQLValidator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Mahdi
 */
public class Users implements AdvancedService<User> {

    @Override
    public Boolean modify(User t) {
        try {
            PreparedStatement st = connection.prepareStatement(
                    "UPDATE `users` SET `bio`=?,`birth_date`=?,"
                    + "`email`=?,`full_name`=?,`invitable`=?,"
                    + "`password`=?,`photo_url`=?,"
                    + "`role`=?,`username`=?,`join_date`=? WHERE id=?");
            st.setString(1, t.getBio());
            st.setDate(2, new java.sql.Date(t.getBirthDate().getTime()));
            st.setString(3, t.getEmail());
            st.setString(4, t.getFullName());
            st.setObject(5, t.isInvitable(), java.sql.Types.BOOLEAN);
            st.setString(6, t.getPassword());
            st.setString(7, t.getPhotoURL());
            st.setString(8, t.getRole().toString());
            st.setString(9, t.getUsername());
            st.setDate(10, t.getJoinDate() != null ? new java.sql.Date(t.getJoinDate().getTime()) : null);
            st.setObject(11, t.getId(), java.sql.Types.INTEGER);

            return st.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }

    @Override
    public Boolean insert(User u) {
        try {
            String req = "INSERT INTO `users` ("
                    + "`id`, `email`, `username`, `password`, `photo_url`, `full_name`, `badges`, `bio`, `birth_date`, `join_date`, `invitable`, `role`"
                    + ") VALUES ("
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
                    + ")";
            PreparedStatement ps = connection.prepareStatement(req);
            Integer i = 0;
            ps.setObject(++i, u.getId(), java.sql.Types.INTEGER);
            ps.setString(++i, u.getEmail());
            ps.setString(++i, u.getUsername());
            ps.setString(++i, u.getPassword());
            ps.setString(++i, u.getPhotoURL());
            ps.setString(++i, u.getFullName());
            ps.setString(++i, u.getBadge());
            ps.setString(++i, u.getBio());
            ps.setDate(++i, new Date(u.getBirthDate().getTime()));
            ps.setDate(++i, new Date(u.getJoinDate().getTime()));
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
                User u = new User();
                u.setBio(rs.getString("bio"));
                u.setBirtDate(rs.getDate("birth_date"));
                u.setEmail(rs.getString("email"));
                u.setId(rs.getObject("id", Integer.class));
                u.setFullName(rs.getString("full_name"));
                u.setInvitable(rs.getObject("invitable", Boolean.class));
                u.setPassword(rs.getString("password"));
                u.setPhotoURL(rs.getString("photo_url"));
                u.setUsername(rs.getString("username"));
                u.setRole(Role.valueOf(rs.getString("role")));
                u.setJoinDate(rs.getDate("join_date"));

                lu.add(u);
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

    public boolean checklogin(String username, String password) {
        try {
            cryptWithMD5(password);
            Statement st = connection.createStatement();
            String query = "SELECT * FROM `users` WHERE `username`='" + username + "' AND `password`='" + password + "'";
            ResultSet rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
