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
        userlist.add(new User(null, "+216 99 856 236", "mahdi.soussi@gamil.com", "mahdi", "mahdi", "", "Mahdi Soussi", "Xd", new Date(0), new Date(), true, Role.admin));
        userlist.add(new User(null, "+216 23 356 236", "mamine.taieb@gamil.com", "amine", "amine", "", "M. Amine Taieb", "Oppa", new Date(32), new Date(), true, Role.admin));
        userlist.add(new User(null, "+216 50 345 236", "sami.benmeshlia@gamil.com", "samibm", "samibm", "", "Sami Ben Meshlia", "blabla", new Date(20), new Date(), false, Role.user));
        userlist.add(new User(null, "+216 54 825 123", "firas.garci@gamil.com", "firas", "firas", "", "Firas Garci", "dfgdfg", new Date(40), new Date(), true, Role.user));
        userlist.add(new User(null, "+216 92 826 936", "iheb.benahmed@gamil.com", "iheb", "iheb", "", "Iheb Ben Ahmed", "dfgdfg", new Date(10), new Date(), false, Role.admin));
        userlist.add(new User(null, "+216 70 816 222", "chayma.dhahri@gamil.com", "chayma", "chayma", "", "Chayma Dhahri", "Xsdfd", new Date(5), new Date(), true, Role.user));
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
