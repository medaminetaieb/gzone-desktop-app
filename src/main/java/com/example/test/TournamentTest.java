/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.test;

import com.example.service.Tournaments;
import com.example.entity.Tournament;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mat
 */
public class TournamentTest {
    private static final Tournaments tournaments = new Tournaments();
    private static final List<Tournament> tournamentlist = new ArrayList<>();
    
    static {
        tournamentlist.add(new Tournament(0, 2, 1, "fifa tournament", "sdf", 8, 1, true, true, new Date()));
        tournamentlist.add(new Tournament(0, 1, 3, "CS GO tournament", "oppa", 4, 5, true, true, new Date()));
        tournamentlist.add(new Tournament(0, 1, 1, "fifa tournament", "hhxcd", 16, 1, true, true, new Date()));
    }
    
    public static void insertAll() {
        for (Tournament t : tournamentlist) {
            if (tournaments.insert(t)) {
                System.out.println("Tournament inserted");
            }
        }
    }
    
    public static void main() {
        for (Tournament t : tournaments.findAll()) {
            System.out.println(t);
        }
        if (tournaments.delete("game_id=1")) {
            System.out.println("Tournaments deleted");
        }
        if (tournaments.modify(new Tournament(2, 1, 3, "CS GO tournament updated name", "oppa", 4, 5, false, true, new Date()))) {
            System.out.println("Tournament updated");
        }
    }
}
