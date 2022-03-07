/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.util;

import com.example.entity.Match;
import com.example.service.Matches;
import com.example.entity.Team;
import com.example.service.Teams;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author samib
 */
public class TeamStat {

    public static double getWinRate(Integer id) {

        final Matches matches = new Matches();
        List<Match> l = matches.findAll("`team1_id`=" + id + " OR `team2_id`=" + id);
        int wins = (int) l.stream().filter(m -> m.getWinnerTeamId().equals(id)).count();
        double winrate=((double)wins / (double)l.stream().count())*100;
        double w = Math.round(winrate*100.0)/100.0;
        return w;
    }

    public static List<Team> topThreeTeams() {
        Teams teams = new Teams();
        return teams.findAll().stream()
                .sorted((Team t1, Team t2) -> {
                    if (getWinRate(t1.getId()) > getWinRate(t2.getId())) {
                        return -1;
                    } else if (getWinRate(t1.getId()) < getWinRate(t2.getId())) {
                        return 1;
                    } else {
                        return 0;
                    }
                }).limit(3).collect(Collectors.toList());
    }
}
