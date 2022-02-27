/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.test;

import com.example.entity.MarketItem;
import com.example.service.MarketItems;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 * @author Firas
 */
public class MarketItemTest {
    
    private static final MarketItems marketItems = new MarketItems();
    private static final List<MarketItem> marketitemlist = new ArrayList<>();
    
    static {
        marketitemlist.add(new MarketItem(0, 2, "csgo account", "sdf", false, new Date()));
        marketitemlist.add(new MarketItem(0, 2, "skin", "sdfdsfsdfsdfsdf", true, new Date(500000000)));
        marketitemlist.add(new MarketItem(0, 2, "hhh", "lol", false, new Date()));
        marketitemlist.add(new MarketItem(0, 2, "xxxxx", "xxxx", true, new Date()));
    }
    
    public static void insertAll() {
        for (MarketItem mi : marketitemlist) {
            if (marketItems.insert(mi)) {
                System.out.println("MarketItem inserted");
            }
        }
    }

    public static void main() {
        for (MarketItem mi : marketItems.find(0, 3, "sold=true", "title DESC")) {
            System.out.println(mi);
        }
        if (marketItems.delete("title='hhh'")) {
            System.out.println("MarketItem deleted");
        }
    }
}
