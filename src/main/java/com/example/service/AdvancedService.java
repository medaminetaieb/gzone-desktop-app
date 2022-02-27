/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.service;

/**
 *
 * @author mat
 * @param <T>
 */
public interface AdvancedService<T> extends Service<T> {

    // Update methods
    public Boolean modify(T instance);
}
