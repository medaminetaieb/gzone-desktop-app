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
    private String fullName;
    private String photoURL;
    private Date birthDate;
    private Date joinDate;
    private String email;
    private String username;
    private String password;
    private Boolean invitable;
    private String bio;
    private Role role;
    private String badge;

    
    public User() {
    }

    public User(Integer id, String fullName, String photoURL, Date birthDate, Date joinDate, String email, String username, String password, Boolean invitable, String bio, Role role, String badge) {
        this.id = id;
        this.fullName = fullName;
        this.photoURL = photoURL;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
        this.email = email;
        this.username = username;
        this.password = password;
        this.invitable = invitable;
        this.bio = bio;
        this.role = role;
        this.badge = badge;
    }

    public User(String fullName, String photoURL, Date birthDate, Date joinDate, String email, String username, String password, Boolean invitable, String bio, Role role, String badge) {
        this.fullName = fullName;
        this.photoURL = photoURL;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
        this.email = email;
        this.username = username;
        this.password = password;
        this.invitable = invitable;
        this.bio = bio;
        this.role = role;
        this.badge = badge;
    }

    public User(String fullName, String photoURL, Date birthDate, Date joinDate, String email, String username, String password, Boolean invitable, String bio, String badge) {
        this.fullName = fullName;
        this.photoURL = photoURL;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
        this.email = email;
        this.username = username;
        this.password = password;
        this.invitable = invitable;
        this.bio = bio;
        this.badge = badge;
    }

    public User(String fullName, String photoURL, Date birthDate, Date joinDate, String email, String username, String password, Boolean invitable, String bio, Role role) {
        this.fullName = fullName;
        this.photoURL = photoURL;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
        this.email = email;
        this.username = username;
        this.password = password;
        this.invitable = invitable;
        this.bio = bio;
        this.role = role;
    }

    public User(String fullName, String photoURL, Date birthDate, Date joinDate, String email, String username, String password, Boolean invitable, String bio) {
        this.fullName = fullName;
        this.photoURL = photoURL;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
        this.email = email;
        this.username = username;
        this.password = password;
        this.invitable = invitable;
        this.bio = bio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirtDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
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

    public Boolean isInvitable() {
        return invitable;
    }

    public void setInvitable(Boolean invitable) {
        this.invitable = invitable;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", full_name=" + fullName + ", photo_url=" + photoURL + ", birth_date=" + birthDate + ", email=" + email + ", username=" + username + ", password=" + password + ", is_invitable=" + invitable + ", bio=" + bio + ", role=" + role + ", join_date=" + joinDate + ", badge=" + badge + '}' + "\n";
    }

}
