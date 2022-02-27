/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.entity;

/**
 *
 * @author Firas
 */
public class Store {
    private Integer id;
    private Integer ownerId;
    private Integer gameId;
    private String name;

    
    
    public Store(Integer id, Integer ownerId, Integer gameId, String name) {
        this.id = id;
        this.ownerId = ownerId;
        this.gameId = gameId;
        this.name = name;
          
    }
  
   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;}
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Store{");
        sb.append("id=").append(id);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", gameId=").append(gameId);
        sb.append(", name=").append(name);
        
        
        sb.append('}');
        return sb.toString();
    }
}