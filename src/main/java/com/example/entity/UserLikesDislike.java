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
public class UserLikesDislike {

    private Integer id;
    private Integer userId;
    private Integer storeId;
    private Integer postId;
    private Integer commentId;
    private Boolean like;

    public UserLikesDislike() {
    }

    public UserLikesDislike(Integer id, Integer userId, Integer storeId, Integer postId, Integer commentId, Boolean like) {
        this.id = id;
        this.userId = userId;
        this.storeId = storeId;
        this.postId = postId;
        this.commentId = commentId;
        this.like = like;
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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Boolean isLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserLikesDislike{id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", storeId=").append(storeId);
        sb.append(", postId=").append(postId);
        sb.append(", commentId=").append(commentId);
        sb.append(", like=").append(like);
        sb.append('}');
        return sb.toString();
    }

}
