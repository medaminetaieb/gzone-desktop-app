/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.entity;

/**
 *
 * @author Asus
 */
public class UserGamePreference {
    
    private Integer id;
    private Integer userId;
    private Integer gameId;

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public UserGamePreference(Integer id, Integer userId, Integer gameId) {
        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
    }
    @Override
    public String toString() {
        return "UserGamePreference{" + "id=" + id + ", userId=" + userId + ", gameId=" + gameId + '}';
    }

    
}
