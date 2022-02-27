/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.entity;

import java.util.Date;

/**
 *
 * @author chayma
 */
public class HappyHour {
    private Integer id;
    private Integer gameId;
    private Date startDate;
    private Date endDate;
    private String badge;

    public HappyHour(Integer id, Integer gameId, Date startDate, Date endDate, String badge) {
        this.id = id;
        this.gameId = gameId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.badge = badge;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("HappyHour{");
        sb.append("id=").append(id);
        sb.append(", gameId=").append(gameId);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", badge=").append(badge);
        sb.append('}');
        return sb.toString();
    }
}
