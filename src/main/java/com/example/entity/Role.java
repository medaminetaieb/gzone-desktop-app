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
    ROLE_USER {
        @Override
        public String toString() {
            return "ROLE_USER";
        }        
    },
    ROLE_ADMIN {
        @Override
        public String toString() {
            return "ROLE_ADMIN";
        }
    }
}
