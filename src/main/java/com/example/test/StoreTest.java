/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.test;

import com.example.entity.Store;
import com.example.service.Stores;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Firas
 */
public class StoreTest {
    
    private static final Stores stores = new Stores();
    private static final List<Store> storelist = new ArrayList<>();
    
    static {
        storelist.add(new Store(null, 1, 3, "Csgo store by mahdi"));
        storelist.add(new Store(null, 2, 1, "fifa store by mahdi"));
        storelist.add(new Store(null, 2, 3, "Csgo store by mahdi"));
    }
    
    public static void insertAll() {
        for (Store s : storelist) {
            if (stores.insert(s)) {
                System.out.println("Store inserted");
            }
        }
    }

    public static void main() {
        for (Store s : stores.find(0, 4, null, null)) {
            System.out.println(s);
        }
        
        if (stores.deleteAllByOwnerId(1)) {
            System.out.println("Stores deleted");
        }
    }
}
