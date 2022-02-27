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
public class JoinRequest {

    private Integer id;
    private Integer userId;
    private Integer teamId;
    private Integer tournamentId;
    private String message;
    private Date requestDate;
    private Boolean accepted;
    private Date responseDate;
    private Boolean invitation;

    public JoinRequest() {
    }

    public JoinRequest(Integer id, Integer userId, Integer teamId, Integer tournamentId, String message, Date requestDate, Boolean accepted, Date responseDate, Boolean invitation) {
        this.id = id;
        this.userId = userId;
        this.teamId = teamId;
        this.tournamentId = tournamentId;
        this.message = message;
        this.requestDate = requestDate;
        this.accepted = accepted;
        this.responseDate = responseDate;
        this.invitation = invitation;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public Boolean isInvitation() {
        return invitation;
    }

    public void setInvitation(Boolean invitation) {
        this.invitation = invitation;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("JoinRequest{id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", teamId=").append(teamId);
        sb.append(", tournamentId=").append(tournamentId);
        sb.append(", message=").append(message);
        sb.append(", requestDate=").append(requestDate);
        sb.append(", accepted=").append(accepted);
        sb.append(", responseDate=").append(responseDate);
        sb.append(", invitation=").append(invitation);
        sb.append('}');
        return sb.toString();
    }

}
