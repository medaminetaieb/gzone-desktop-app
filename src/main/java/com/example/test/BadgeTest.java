/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.test;

import com.example.entity.BadgeShip;
import com.example.service.BadgeShips;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chayma
 */
public class BadgeTest {
    private static final BadgeShips badgeships = new BadgeShips();
    private static final List<BadgeShip> list = new ArrayList<>();
   static {
   list.add(new BadgeShip(null, 2, 2));
      list.add(new BadgeShip(null, 1, 2));
            list.add(new BadgeShip(null, 3, 1));



   }
   
   public static void insertAll(){
    for (BadgeShip b : list){
    if(badgeships.insert(b)){
        System.out.println("inserted");}
    }
   }
    
}
