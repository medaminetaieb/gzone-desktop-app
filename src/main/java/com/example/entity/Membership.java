/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.entity;

/**
 *
 * @author samib
 */
public class Membership {

    private Integer id;
    private Integer userId;
    private Integer teamId;
    private Integer tournamentId;

    public Membership() {
    }

    public Membership(Integer id, Integer userId, Integer teamId, Integer tournamentId) {
        this.id = id;
        this.userId = userId;
        this.teamId = teamId;
        this.tournamentId = tournamentId;
    }

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

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("membership{id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", teamId=").append(teamId);
        sb.append(", tournamentId=").append(tournamentId);
        sb.append('}');
        return sb.toString();
    }

}
