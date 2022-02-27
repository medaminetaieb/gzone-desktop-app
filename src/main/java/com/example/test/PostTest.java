/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test;

import com.example.entity.Post;
import com.example.service.Posts;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iheb
 */
public class PostTest {

    private static final Posts posts = new Posts();
    private static final List<Post> postlist = new ArrayList<>();

    static {
        postlist.add(new Post(null, 1, true, "How to", "do this", "bb", new Date()));
        postlist.add(new Post(null, 2, false, "How to", "do that", "resolveme foryou", new Date()));
        postlist.add(new Post(null, 1, false, "Where to", "go from here", "travel blabla chill", new Date()));
        postlist.add(new Post(null, 1, true, "Where to", "go from here", "travel blabla chill", new Date()));
    }

    public static void insertAll() {
        for (Post p : postlist) {
            if (posts.insert(p)) {
                System.out.println("Post inserted");
            }
        }
    }

    public static void main() {
        for (Post p : posts.find(0, 99, "resolved=false", null)) {
            System.out.println(p);
        }
        
        if (posts.delete("resolved=true")) {
            System.out.println("Posts deleted");
        }
    }

}
