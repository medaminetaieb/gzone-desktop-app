package com.example.entity;

public class BadgeShip {
    private Integer id;
    private Integer badgeId;
    private Integer userId;

    public BadgeShip(Integer id, Integer badgeId, Integer userId) {
        this.id = id;
        this.badgeId = badgeId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Integer badgeId) {
        this.badgeId = badgeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BadgeShip{" +
                "id=" + id +
                ", badgeId=" + badgeId +
                ", userId=" + userId +
                '}';
    }
}
