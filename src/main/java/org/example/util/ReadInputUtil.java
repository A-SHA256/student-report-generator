package org.example.util;

import java.util.Scanner;

public class ReadInputUtil {
    private static final Scanner sc = new Scanner(System.in);

    public static int readIntInput(String message) {
        System.out.print(message);
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }
    public static String readStringInput(String message) {
        System.out.print(message);
        return sc.nextLine().trim();
    }
}
