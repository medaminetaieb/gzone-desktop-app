/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.entity;

import java.util.Date;

/**
 *
 * @author mat
 */
public class Tournament {
    private Integer id;
    private Integer adminId;
    private Integer gameId;
    private String name;
    private String description;
    private Integer requiredTeams;
    private Integer teamSize;
    private Boolean requestable;
    private Boolean approved;
    private Date createDate;

    public Tournament(Integer id, Integer adminId, Integer gameId, String name, String description, Integer requiredTeams, Integer teamSize, Boolean requestable, Boolean approved, Date createDate) {
        this.id = id;
        this.adminId = adminId;
        this.gameId = gameId;
        this.name = name;
        this.description = description;
        this.requiredTeams = requiredTeams;
        this.teamSize = teamSize;
        this.requestable = requestable;
        this.approved = approved;
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

    public void setAdminUserId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
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

    public Integer getRequiredTeams() {
        return requiredTeams;
    }

    public void setRequiredTeamsNumber(Integer requiredTeams) {
        this.requiredTeams = requiredTeams;
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

    public void setRequestable(Boolean requestable) {
        this.requestable = requestable;
    }

    public Boolean isApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
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
        sb.append("Tournament{");
        sb.append("id=").append(id);
        sb.append(", adminId=").append(adminId);
        sb.append(", gameId=").append(gameId);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", requiredTeams=").append(requiredTeams);
        sb.append(", teamSize=").append(teamSize);
        sb.append(", requestable=").append(requestable);
        sb.append(", approved=").append(approved);
        sb.append(", createDate=").append(createDate);
        sb.append('}');
        return sb.toString();
    }
}