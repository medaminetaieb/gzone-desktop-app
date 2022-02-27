/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.example.entity;

import java.util.Date;

/**
 *
 * @author mat
 */
public class Match {
    private Integer id;
    private Integer tournamentId;
    private Date startTime;
    private Integer round;
    private Integer team1Id;
    private Integer team2Id;
    private Integer winnerTeamId;

    public Match(Integer id, Integer tournamentId, Date startTime, Integer round, Integer team1Id, Integer team2Id, Integer winnerTeamId) {
        this.id = id;
        this.tournamentId = tournamentId;
        this.startTime = startTime;
        this.round = round;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.winnerTeamId = winnerTeamId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Integer getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(Integer team1Id) {
        this.team1Id = team1Id;
    }

    public Integer getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(Integer team2Id) {
        this.team2Id = team2Id;
    }

    public Integer getWinnerTeamId() {
        return winnerTeamId;
    }

    public void setWinnerTeamId(Integer winnerTeamId) {
        this.winnerTeamId = winnerTeamId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Match{");
        sb.append("id=").append(id);
        sb.append(", tournamentId=").append(tournamentId);
        sb.append(", startTime=").append(startTime);
        sb.append(", round=").append(round);
        sb.append(", team1Id=").append(team1Id);
        sb.append(", team2Id=").append(team2Id);
        sb.append(", winnerTeamId=").append(winnerTeamId);
        sb.append('}');
        return sb.toString();
    }
}