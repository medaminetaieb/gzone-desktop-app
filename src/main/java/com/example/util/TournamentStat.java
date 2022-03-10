/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.util;

import com.example.entity.Badge;
import com.example.entity.Match;
import com.example.entity.Team;
import com.example.entity.Tournament;
import com.example.entity.User;
import com.example.service.BadgeShips;
import com.example.service.Badges;
import com.example.service.Matches;
import com.example.service.Teams;
import com.example.service.Tournaments;
import com.example.service.UserGamePreferences;
import com.example.service.Users;
import static com.example.util.TeamStat.getWinRate;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author chayma
 */
public class TournamentStat {

    public static int CountTournaments(Integer gameId) {
        Tournaments tournaments = new Tournaments();
        return tournaments.findAll("`game_id`=" + gameId).size();
    }

    public static int CountBadges(Integer userId) {
        return new BadgeShips().findAll("`user_id`=" + userId).size();

    }

    public static List<User> TopFive() {
        Users users = new Users();
        return users.findAll().stream().sorted((u1, u2) -> {

            if (CountBadges(u1.getId()) > CountBadges(u2.getId())) {
                return -1;
            } else if (CountBadges(u1.getId()) < CountBadges(u2.getId())) {
                return 1;
            } else {
                return 0;
            }
        }
        ).limit(5).collect(Collectors.toList());
    }
    
    
    
    
    
    public static List<Tournament> suggestedTournaments(Integer userId) {
        List<Integer> favoriteGameIds = new UserGamePreferences().findAll("`user_id`="+userId).stream()
                .map(ugp -> ugp.getGameId())
                .collect(Collectors.toList());
        
        return new Tournaments().findAll().stream()
                .filter(t -> favoriteGameIds.indexOf(t.getGameId()) != -1)
                .collect(Collectors.toList());
    }
}
