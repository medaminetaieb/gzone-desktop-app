

package com.example.entity;

import java.util.Date;

/**
 *
 * @author Mahdi
 */
public class Report {

    private Integer id;
    private Integer reporterId;
    private Subject subject;
    private String head;
    private String body;
    private Date reportDate;

    public Report(Integer id, Integer reporterId, Subject subject, String head, String body, Date reportDate) {
        this.id = id;
        this.reporterId = reporterId;
        this.subject = subject;
        this.head = head;
        this.body = body;
        this.reportDate = reportDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReporterId() {
        return reporterId;
    }

    public void setReporterId(Integer reporterId) {
        this.reporterId = reporterId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Report{");
        sb.append("id=").append(id);
        sb.append(", reporterId=").append(reporterId);
        sb.append(", subject=").append(subject);
        sb.append(", head=").append(head);
        sb.append(", body=").append(body);
        sb.append(", reportDate=").append(reportDate);
        sb.append('}');
        return sb.toString();
    }
}
