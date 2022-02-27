/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.entity;

import java.util.Date;

/**
 *
 * @author iheb
 */
public class Post {

    private Integer id;
    private Integer posterId;
    private Boolean resolved;
    private String title;
    private String content;
    private String tags;
    private Date postDate;

    public Post(Integer id, Integer posterId, Boolean resolved, String title, String content, String tags, Date postDate) {
        this.id = id;
        this.posterId = posterId;
        this.resolved = resolved;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.postDate = postDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPosterId() {
        return posterId;
    }

    public void setPosterId(Integer posterId) {
        this.posterId = posterId;
    }

    public Boolean isResolved() {
        return this.resolved;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Post{");
        sb.append("id=").append(id);
        sb.append(", posterId=").append(posterId);
        sb.append(", resolved=").append(resolved);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", tags=").append(tags);
        sb.append(", postDate=").append(postDate);
        sb.append('}');
        return sb.toString();
    }
}
