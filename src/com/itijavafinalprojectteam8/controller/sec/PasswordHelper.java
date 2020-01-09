package com.itijavafinalprojectteam8.controller.sec;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ahares
 */
public class PasswordHelper {

    public static String getEncryptedPassword(final String p) throws NoSuchAlgorithmException {
        return toHexString(getSHA(p));
    }
    private static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-512");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
    private static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    /*
    // Driver code
    public static void main(String args[]) {
        try {
            System.out.println("HashCode Generated by SHA-512 for:");

            String s1 = "GeeksForGeeks";
            System.out.println("\n" + s1 + " : " + getEncryptedPassword(s1));

            String s2 = "hello world";
            System.out.println("\n" + s2 + " : " + getEncryptedPassword(s2));
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
    }*/
}