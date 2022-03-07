/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author iheb
 */
public class Badwords {

    private static final ArrayList<String> badwords= new ArrayList<>();

    static {
        badwords.add("ffff");
        badwords.add("ssss");
        badwords.add("nnnn");
    }

    public static String filter(String input) {
        for(String badword:badwords){
        
            input = input.replaceAll(badword, "*".repeat(badword.length())  );
        
        }
        return input;
    }

}
