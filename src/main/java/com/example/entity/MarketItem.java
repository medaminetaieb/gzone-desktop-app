/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.entity;

import java.util.Date;



/**
 *
 * @author Firas
 */
public class MarketItem {
   private Integer id;
   private Integer storeId;
   private String title;
   private String description;
   private Boolean sold;
   private Date postDate;

    public MarketItem() {
    }
   
    public MarketItem(Integer id, Integer storeId, String title, String description, Boolean sold, Date postDate) {
        this.id = id;
        this.storeId = storeId;
        this.title = title;
        this.description = description;
        this.sold = sold;
        this.postDate = postDate;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isSold() {
        return sold;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
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
        sb.append("MarketItem{");
        sb.append("id=").append(id);
        sb.append(", storeId=").append(storeId);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", sold=").append(sold);
        sb.append(", postDate=").append(postDate);
        sb.append('}');
        return sb.toString();
    }
}
