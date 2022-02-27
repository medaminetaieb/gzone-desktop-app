/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test;

import com.example.entity.Role;
import com.example.entity.User;
import com.example.service.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserTest {

    private static final Users users = new Users();
    private static final List<User> userlist = new ArrayList<>();

    static {
        userlist.add(new User(null, "Mahdi Soussi", "", new Date(9999999), new Date(), "mahdi.soussi@gmail.com", "mahdisoussi", "mahdisoussi", true, "I am the first user", Role.admin, "batman"));
        userlist.add(new User(null, "Mohamed Amine Taieb", "", new Date(99999999), new Date(), "mohamedamine.taieb@gmail.com", "maminetaieb", "maminetaieb", true, "I am the second user", Role.admin, "bunny hopper"));
        userlist.add(new User(null, "Sami Ben Meshlia", "", new Date(9939999), new Date(), "samin.benmeshlia@gmail.com", "samibm", "samibm", true, "I am the third user", Role.user, "headshooter"));
        userlist.add(new User(null, "Firas Garci", "", new Date(9999499), new Date(), "firas.garci@gmail.com", "firasgarci", "firasgarci", true, "I am the fourth user", Role.user, "silent killer"));
        userlist.add(new User(null, "Iheb Ben Salem", "", new Date(9992999), new Date(), "iheb.bensalem@gmail.com", "ihebbensalem", "ihebbensalem", true, "I am the fifth user", Role.user, "horny devil"));
        userlist.add(new User(null, "Chayma Dhahri", "", new Date(9992199), new Date(), "chayma.dhahri@gmail.com", "chaymadhahri", "chaymadhahri", true, "I am the sixth user", Role.user, "female gamer"));
    }

    public static void insertAll() {
        for (User u : userlist) {
            if (users.insert(u)) {
                System.out.println("User inserted");
            }
        }
    }

    public static void main() {
        for (User u : users.find(0, 2, "role='user'", "birth_date ASC")) {
            System.out.println(u);
        }

        if (users.delete("role='user'")) {
            System.out.println("User deleted");
        }
    }

}
