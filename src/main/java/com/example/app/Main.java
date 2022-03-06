/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.app;

import com.example.test.MatchTest;
import com.example.util.TeamStat;

/**
 * @author mat
 */
public class Main {
    public static void main(String[] args) {
       //MatchTest.insertAll();


        //System.out.println(TeamStat.getWinRate(2));
   // MembershipTest.insertAll();

        //MatchTest.insertAll();
      //  TeamStat.topTenTeams();
       System.out.println(TeamStat.getWinRate(15));
        System.out.println(TeamStat.getWinRate(24));
        System.out.println(TeamStat.getWinRate(1));
        System.out.println(TeamStat.getWinRate(2));
       System.out.println(TeamStat.topThreeTeams());
    }
}


