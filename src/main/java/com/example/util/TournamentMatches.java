/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.util;

import com.example.entity.Tournament;

/**
 * @author mat
 */
public class TournamentMatches {
    /**
     * @param t An instance of entity Tournament to calculate its first round
     * @return The calculated first round based on the number of requiredTeams or 0 if error
     */
    public static int firstRoundOf(Tournament t) {
        double b = t.getRequiredTeams();

        if (b == 4) {
            return 2;
        }

        if (b == 8) {
            return 3;
        }

        if (b == 16) {
            return 4;
        }

        if (b == 32) {
            return 5;
        }

        return 0;
    }
}
