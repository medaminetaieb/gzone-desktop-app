/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.entity;

/**
 *
 * @author chayma
 */
public class Game {
    private Integer id;
    private String name;
    private String photoUrl;
    private String description;
    
    public Game(Integer id, String name, String photoUrl, String description) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Game{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", photoUrl=").append(photoUrl);
        sb.append(", description=").append(description);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
