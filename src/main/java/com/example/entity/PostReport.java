/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.entity;

/**
 *
 * @author Asus
 */
public class PostReport {
    
    private Integer id;
    private Integer postId;
    private Integer reportId;

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public PostReport(Integer id, Integer postId, Integer reportId) {
        this.id = id;
        this.postId = postId;
        this.reportId = reportId;
    }
    @Override
    public String toString() {
        return "PostReport{" + "id=" + id + ", postId=" + postId + ", reportId=" + reportId + '}';
    }
}
