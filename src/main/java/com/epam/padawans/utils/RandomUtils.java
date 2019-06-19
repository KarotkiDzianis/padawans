package com.epam.padawans.utils;

import java.util.Random;

public class RandomUtils {

    private static final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String number = "0123456789";
    private static final String AB_number = "ABCDEFGHIJKLMNOPQRSTUVWXYZ@#$!$%^&*()?<>=+|0123456789";
    private static Random rnd = new Random();

    public static String getRandomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    public static String getRandomInt(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(number.charAt(rnd.nextInt(number.length())));
        }
        return sb.toString();
    }

    public static String getRandomIntAndString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB_number.charAt(rnd.nextInt(number.length())));
        }
        return sb.toString();
    }
}
