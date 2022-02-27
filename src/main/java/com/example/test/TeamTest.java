/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test;

import com.example.entity.Team;
import com.example.service.Teams;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samib
 */
public class TeamTest {
    
    private static final Teams teams = new Teams();
    private static final List<Team> teamlist = new ArrayList<>();
    
    static {
        teamlist.add(new Team(0, 1, "", "Wolves", "opppa", 1, 5, true, true, new Date()));
        teamlist.add(new Team(0, 2, "", "Spiders", "swimming", 1, 5, true, true, new Date()));
        teamlist.add(new Team(0, 2, "", "Ants", "walking", 3, 5, true, true, new Date()));
    }
    
    public static void insertAll() {
        for (Team t : teamlist) {
            if (teams.insert(t)) {
                System.out.println("Team inserted");
            }
        }
    }

    public static void main() {
        for (Team t : teams.findAll()) {
            System.out.println(t);
        }
        
        if (teams.delete("game_id=3")) {
            System.out.println("Team deleted");
        }
        
        if (teams.modify(new Team(0, 2, "", "Spiders", "Update: not swimming", 1, 5, true, true, new Date()))) {
            System.out.println("Team updated");
        }
    }
}
