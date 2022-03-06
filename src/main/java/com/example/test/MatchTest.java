/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.test;

import com.example.entity.Match;
import com.example.service.Matches;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mat
 */
public class MatchTest {
    
    private static final Matches matches = new Matches();
    private static final List<Match> matchlist = new ArrayList<>();
    
    static {
        /*matchlist.add(new Match(null, 2, new Date(20200506), 2, 1, 2, 2));
        matchlist.add(new Match(null, 2, new Date(), 1, 2, 1, 1));
        matchlist.add(new Match(null, 2, new Date(20200506), 1, 2, 1, 1));
        matchlist.add(new Match(null, 2, new Date(20200506), 3, 2, 15, 15));
        matchlist.add(new Match(null, 2, new Date(20200506), 1, 2, 24, 24));
        matchlist.add(new Match(null, 2, new Date(20200506), 2, 24, 2, 24));*/
        matchlist.add(new Match(null, 2, new Date(20200506), 2, 24, 15, 24));
    }
    
    public static void insertAll() {
        for (Match m : matchlist) {
            if (matches.insert(m)) {
                System.out.println("Match inserted");
            }
        }
    }
    
    public static void main() {
        for (Match m : matches.find(2, 3, null, null)) {
            System.out.println(m);
        }
        
        if (matches.modify(new Match(1, 2, new Date(20200506), 100, 2, 1, 2))) {
            System.out.println("Match updated");
        }
        if (matches.delete("winner_team_id=1")) {
            System.out.println("Matches deleted");
        }
    }
}
