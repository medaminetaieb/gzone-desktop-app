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
        List<Match> l = matches.find(null, null, "`team1_id`=" + id + " OR `team2_id`=" + id, null);
        int wins = (int) l.stream().filter(m -> m.getWinnerTeamId().equals(id)).count();

        return wins / l.stream().count();
    }

    public static List<Team> topTenTeams() {
        Teams teams = new Teams();
        return teams.findAll().stream()
                .sorted((t1, t2) -> {
                    if (getWinRate(t1.getId()) > getWinRate(t2.getId())) {
                        return 1;
                    } else if (getWinRate(t1.getId()) < getWinRate(t2.getId())) {
                        return -1;
                    } else {
                        return 0;
                    }
                }).limit(10).collect(Collectors.toList());
    }
}
