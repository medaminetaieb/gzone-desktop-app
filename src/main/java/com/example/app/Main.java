/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.app;

import com.example.test.MainTest;
import com.example.test.MatchTest;
import com.example.test.MembershipTest;
import com.example.util.TeamStat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author mat
 */
public class Main {
    public static void main(String[] args) {
       // MatchTest.insertAll();
       //MainTest.main();
        //System.out.println(TeamStat.getWinRate(2));


     /*  Image image = null;
        try {
            URL url = new URL("https://pbs.twimg.com/profile_images/1157313327867092993/a09TxL_1_400x400.jpg");
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            conn.connect();
            InputStream urlStream = conn.getInputStream();
            image = ImageIO.read(urlStream);

            JFrame frame = new JFrame();
            JLabel lblimage = new JLabel(new ImageIcon(image));
            frame.getContentPane().add(lblimage, BorderLayout.CENTER);
            frame.setSize(image.getWidth(null) + 50, image.getHeight(null) + 50);
            frame.setVisible(true);

        } catch (IOException e) {
            System.out.println("Something went wrong, sorry:" + e.toString());
            e.printStackTrace();
        }*/
    }
}


