/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.entity;

import java.util.Date;


/**
 *
 * @author samib
 */
public class Team {
    
    private Integer id;
    private Integer adminId;
    private String photoURL;
    private String name;
    private String description;
    private Integer gameId;
    private Integer teamSize;
    private Boolean requestable;
    private Boolean invitable;
    private Date createDate;

    public Team() {
    }

    public Team(Integer id, Integer adminId, String photoURL, String name, String description, Integer gameId, Integer teamSize, Boolean requestable, Boolean invitable, Date createDate) {
        this.id = id;
        this.adminId = adminId;
        this.photoURL = photoURL;
        this.name = name;
        this.description = description;
        this.gameId = gameId;
        this.teamSize = teamSize;
        this.requestable = requestable;
        this.invitable = invitable;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(Integer teamSize) {
        this.teamSize = teamSize;
    }
    
    public Boolean isRequestable() {
        return requestable;
    }

    public void setRequestable(Boolean Requestable) {
        this.requestable = Requestable;
    }

    public Boolean isInvitable() {
        return invitable;
    }

    public void setInvitable(Boolean Invitable) {
        this.invitable = Invitable;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //sb.append("Team{id=").append(id);
        //sb.append(", adminUserId=").append(adminId);
        sb.append("Name=").append(name);
        sb.append(", Description=").append(description);
        sb.append(", PhotoURL=").append(photoURL);
       //sb.append(", gameId=").append(gameId);
        sb.append(", TeamSize=").append(teamSize);
        sb.append(", Requestable=").append(requestable);
        sb.append(", Invitable=").append(invitable);
        sb.append(", CreateDate=").append(createDate);
        sb.append('}');
        
        return sb.toString();
    }
}