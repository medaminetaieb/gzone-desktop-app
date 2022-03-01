/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mat
 */
public class MyConnection {
    private final String url = "jdbc:mysql://localhost:3306/gzoneDB";
    private final String login = "root";
    private final String pwd = "";
    private Connection connection;
    private static MyConnection instance;
    
    public MyConnection() {
        try {
            connection = DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        
        return instance;
    }
}