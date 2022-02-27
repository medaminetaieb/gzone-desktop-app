/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.test;

import com.example.entity.Game;
import com.example.service.Games;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chayma
 */
public class GameTest {

    private static final Games games = new Games();
    private static final List<Game> gamelist = new ArrayList<>();

    static {
        gamelist.add(new Game(null, "Valorant", "", "Valorant is an FPS games mad by Riot Games"));
        gamelist.add(new Game(null, "League Of Legend", "", "LoL is a game by Riot Games"));
        gamelist.add(new Game(null, "Counter-Strike: Global Offensive", "", "CS GO is an FPS games mad by Valve"));
    }

    public static void insertAll() {
        for (Game g : gamelist) {
            if (games.insert(g)) {
                System.out.println("Game inserted");
            }
        }
    }

    public static void main() {
        Game g = games.findById(3);
        if (g != null) {
            System.out.println(g);
        }

        if (games.deleteById(5)) {
            System.out.println("Game deleted");
        }

        if (games.modify(new Game(1, "Fifa", "fifa.com", "second game"))) {
            System.out.println("Game updated");
        }
    }
}
