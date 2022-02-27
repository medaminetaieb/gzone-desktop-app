/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.entity;

/**
 *
 * @author mat
 */
public class MarketItemReport {
    private Integer id;
    private Integer marketItemId;
    private Integer reportId;

    public MarketItemReport(Integer id, Integer marketItemId, Integer reportId) {
        this.id = id;
        this.marketItemId = marketItemId;
        this.reportId = reportId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMarketItemId() {
        return marketItemId;
    }

    public void setMarketItemId(Integer marketItemId) {
        this.marketItemId = marketItemId;
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
        sb.append("MarketItemReport{");
        sb.append("id=").append(id);
        sb.append(", marketItemId=").append(marketItemId);
        sb.append(", reportId=").append(reportId);
        sb.append('}');
        return sb.toString();
    }
}
