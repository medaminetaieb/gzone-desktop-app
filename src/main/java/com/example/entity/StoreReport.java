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
public class StoreReport {

    private Integer id;
    private Integer storeId;
    private Integer reportId;

    public StoreReport() {
    }

    public StoreReport(Integer id, Integer storeId, Integer reportId) {
        this.id = id;
        this.storeId = storeId;
        this.reportId = reportId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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
        sb.append("StoreReport{id=").append(id);
        sb.append(", storeId=").append(storeId);
        sb.append(", reportId=").append(reportId);
        sb.append('}');
        return sb.toString();
    }

}
