/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.util;

import java.sql.SQLException;

/**
 *
 * @author mat
 */
public class MySQLValidator {
    public static String parseFilter(String filter) throws SQLException {
        if (filter == null) {
            return "";
        }
        
        if (filter.isBlank()) {
            throw new SQLException("Invalid SQL filter syntax! Must contain non-empty characters.");
        }
        
        if (filter.indexOf(';') > -1) {
            throw new SQLException("Invalid SQL filter syntax! Unexpected ';'.");
        }
        
        filter = " WHERE " + filter.strip();
        
        return filter;
    }
    
    public static String parseOrder(String order) throws SQLException {
        if (order == null) {
            return "";
        }
        
        if (order.isBlank()) {
            throw new SQLException("Invalid SQL order syntax! Must contain non-empty characters.");
        }
        
        if (order.indexOf(';') > -1) {
            throw new SQLException("Invalid SQL order syntax! Unexpected ';'.");
        }
        
        order = " ORDER BY " + order.strip();
        
        return order;
    }
    
    public static String parseLimit(Integer offset, Integer rowCount) throws SQLException {
        if (rowCount == null) {
            if (offset != null) {
                throw new SQLException("Invalid SQL limit syntax! offset must be null if row_count is.");
            }
            
            return "";
        }
        
        if (rowCount < 1) {
            throw new SQLException("Invalid SQL limit syntax! row_count must be at least 1.");
        }
        
        StringBuilder sb = new StringBuilder(" LIMIT ");
        if (offset != null) {
            sb.append(offset).append(',');
        }
        sb.append(rowCount);
        
        return sb.toString();
    }
}