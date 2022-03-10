/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

/**
 *
 * @author mat
 */
public class Gzon {

    private static String path = "C:/Users/" + System.getProperty("user.name") + "/gzone.json";

    public static boolean checkFile() {
        File f = new File(path);
        if (f.exists() && f.isFile()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(path));
                StringBuilder sb = new StringBuilder();
                for (String line = br.readLine(); line != null; line = br.readLine()) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                }
                br.close();
                JSONObject gzon = new JSONObject(sb.toString());

                return gzon.has("username") && gzon.has("password");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public static String getUsername() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            StringBuilder sb = new StringBuilder();
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            br.close();
            JSONObject gzon = new JSONObject(sb.toString());

            return gzon.get("username").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getPassword() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            StringBuilder sb = new StringBuilder();
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            br.close();
            JSONObject gzon = new JSONObject(sb.toString());

            return gzon.get("password").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void saveSession(String username, String password) {
        String gzon = new JSONObject()
                .put("username", username)
                .put("password", password)
                .toString();
        try {
            FileWriter fw = new FileWriter(path, false);
            fw.write(gzon);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveSession(String username) {
        String gzon = new JSONObject()
                .put("username", username)
                .put("password", getPassword())
                .toString();
        try {
            FileWriter fw = new FileWriter(path, false);
            fw.write(gzon);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
