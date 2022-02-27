/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test;

import com.example.entity.Comment;
import com.example.service.Comments;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 * @author iheb
 */
public class CommentTest {
    
    private final static Comments comments = new Comments();
    private final static List<Comment> commentlist = new ArrayList<>();
    
    static {
        commentlist.add(new Comment(null, 4, 1, "doesnt work for me", new Date()));
        commentlist.add(new Comment(null, 1, 2, "bla", new Date()));
        commentlist.add(new Comment(null, 2, 1, "sdlkvmks work for me", new Date()));
        commentlist.add(new Comment(null, 3, 2, "doesnt job for me", new Date()));
    }
    
    public static void insertAll() {
        for (Comment c : commentlist) {
            if (comments.insert(c)) {
                System.out.println("Comment inserted");
            }
        }
    }
    
    public static void main() {
        for (Comment c : comments.find(0, 3, null, null)) {
            System.out.println(c);
        }
        if (comments.delete("commenter_id=1")) {
            System.out.println("Comments deleted");
        }
    }
}
