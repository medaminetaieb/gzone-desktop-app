/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.service;

import com.example.util.MyConnection;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author mat
 * @param <T>
 */
public interface Service<T> {

    static final Connection connection = MyConnection.getInstance().getConnection();

    // Create methods
    public Boolean insert(T instance);

    // Read methods
    public List<T> find(Integer offset, Integer rowCount, String filter, String order);

    // Delete methods
    public Boolean delete(String filter);

    // Read default methods
    public default T findById(int id) {
        List<T> l = find(null, null, "`id`=" + id, null);

        return (!l.isEmpty()) ? l.get(0) : null;
    }

    public default List<T> find(Integer offset, Integer rowCount, String filter) {
        return find(offset, rowCount, filter, null);
    }

    public default List<T> findAll(String filter) {
        return find(null, null, filter, null);
    }

    public default List<T> findAll(String filter, String order) {
        return find(null, null, filter, order);
    }

    public default List<T> findAll() {
        return find(null, null, null, null);
    }

    // Delete default methods
    public default Boolean deleteById(int id) {
        return delete("`id`=" + id);
    }
}