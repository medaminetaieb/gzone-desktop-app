package com.example.util;

import com.example.entity.Store;
import com.example.service.Stores;
import com.example.service.UserLikesDislikes;

import java.util.List;
import java.util.stream.Collectors;

public class StoreStat {
    public static List<Store> Top3() {
        List<Store> l = new Stores().findAll();
        return l.stream().sorted((s1, s2) -> {
            int s1Rate = new UserLikesDislikes().findAll("`store_id`=" + s1.getId() + " and `like`=true").size() - new UserLikesDislikes().findAll("`store_id`=" + s1.getId() + " and `like`=false").size();
            int s2Rate = new UserLikesDislikes().findAll("`store_id`=" + s2.getId() + " and `like`=true").size() - new UserLikesDislikes().findAll("`store_id`=" + s2.getId() + " and `like`=false").size();
            return s2Rate - s1Rate;
        }).limit(3).collect(Collectors.toList());


    }

}
