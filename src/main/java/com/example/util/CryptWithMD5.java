/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Mahdi
 */
public class CryptWithMD5 {

    private static MessageDigest md;

    public static String cryptWithMD5(String pass) {

        byte[] msg = pass.getBytes();

        byte[] hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            hash = md.digest(msg);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        StringBuilder strBuilder = new StringBuilder();
        for (byte b : hash) {
            strBuilder.append(String.format("%02x", b));
        }
        String strHash = strBuilder.toString();
        System.out.println("The MD5 hash: " + strHash);
        return strHash;

    }

}
