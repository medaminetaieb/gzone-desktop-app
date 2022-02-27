/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.entity;

/**
 *
 * @author Mahdi
 */
public enum Role {
    user {
        @Override
        public String toString() {
            return "user";
        }        
    },
    admin {
        @Override
        public String toString() {
            return "admin";
        }
    }
}
