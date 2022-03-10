/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.entity;

import java.util.Date;

/**
 *
 * @author Mahdi
 */
public class User {
    private Integer id;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;
    private String photoURL;
    private String fullName;
    private String bio;
    private Date birthDate;
    private Date joinDate;
    private Boolean invitable;
    private Role role;

    public User(Integer id, String phoneNumber, String email, String username, String password, String photoURL, String fullName, String bio, Date birthDate, Date joinDate, Boolean invitable, Role role) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.password = password;
        this.photoURL = photoURL;
        this.fullName = fullName;
        this.bio = bio;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
        this.invitable = invitable;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Boolean isInvitable() {
        return invitable;
    }

    public void setInvitable(Boolean invitable) {
        this.invitable = invitable;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", photoURL='" + photoURL + '\'' +
                ", fullName='" + fullName + '\'' +
                ", bio='" + bio + '\'' +
                ", birthDate=" + birthDate +
                ", joinDate=" + joinDate +
                ", invitable=" + invitable +
                ", role=" + role +
                '}';
    }

  
}