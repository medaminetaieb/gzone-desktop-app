/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.test;

import com.example.entity.HappyHour;
import com.example.service.HappyHours;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author chayma
 */
public class HappyHourTest {
    
    private final static HappyHours happyHours = new HappyHours();
    private final static List<HappyHour> happyhourlist = new ArrayList<>();
    
    static {
        happyhourlist.add(new HappyHour(null, 1, new Date(0), new Date()));
        happyhourlist.add(new HappyHour(null, 2, new Date(0), new Date()));
        happyhourlist.add(new HappyHour(null, 3, new Date(0), new Date()));
    }
    
    public static void insertAll() {
        for (HappyHour h : happyhourlist) {
            if (happyHours.insert(h)) {
                System.out.println("HappyHour inserted");
            }
        }
    }

    public static void main() {
        for (HappyHour h : happyHours.findAll()) {
            System.out.println(h);
        }
        
        if (happyHours.modify(new HappyHour(4, 3, new Date(0), new Date()))) {
            System.out.println("HappyHour updated");
        }
        
        if (happyHours.delete("badge_id=1")) {
            System.out.println("HappyHours deleted");
        }
    }
}
