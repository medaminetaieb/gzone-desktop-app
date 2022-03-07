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
public class Comment {
    
    private Integer id;
    private Integer postId;
    private Integer commenterId;
    private String commentBody;
    private Date commentDate;
    
    public Comment(Integer id, Integer postId, Integer commenterId, String commentBody, Date commentDate) {
        this.id = id;
        this.postId = postId;
        this.commenterId = commenterId;
        this.commentBody = commentBody;
        this.commentDate = commentDate;
    }

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

    public Integer getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(Integer commenterId) {
        this.commenterId = commenterId;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    public String toString() {
        return "Comments{"+", commenterUserId=" + commenterId + ", commentBody=" + commentBody + ", commentDate=" + commentDate + '}';
    }
}
