package com.example.finalUI.util;

import java.security.MessageDigest;

/**
 * course code: cst8412
 *
 *
 * This class hashes the password of each account
 *
 *
 * @author Sebastian Sell, Luca Beumer, Bennet Ireland
 * @version 1.0
 */
public class PasswordUtil {
    /**Hashes the passwords
     * @param password
     * password of the account*/
    public static String hashPassword(String password) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder hex = new StringBuilder();

            for (byte b : hashedBytes) {
                hex.append(String.format("%02x", b));
            }

            return hex.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
