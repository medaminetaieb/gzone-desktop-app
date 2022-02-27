/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author samib
 */
public class PhotoUrlCheck {

    public static boolean testImage(String url) {
        try {
            BufferedImage image = ImageIO.read(new URL(url));

            if (image != null) {
                return true;
            } else {
                return false;
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block  
            System.err.println("URL error with image");

            return false;
        } catch (IOException e) {
            System.err.println("IO error with image");
            // TODO Auto-generated catch block  

            return false;
        }

    }

}
