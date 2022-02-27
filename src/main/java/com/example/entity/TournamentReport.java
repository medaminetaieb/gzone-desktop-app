/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.entity;

/**
 *
 * @author mat
 */
public class TournamentReport {
    private Integer id;
    private Integer tournamentId;
    private Integer reportId;

    public TournamentReport(Integer id, Integer tournamentId, Integer reportId) {
        this.id = id;
        this.tournamentId = tournamentId;
        this.reportId = reportId;
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

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TournamentReport{");
        sb.append("id=").append(id);
        sb.append(", tournamentId=").append(tournamentId);
        sb.append(", reportId=").append(reportId);
        sb.append('}');
        return sb.toString();
    }
}
