package com.supinfo.courses;

public class Main {
    public static void main (String[] args) {
        // typesTest();
        // operatorsTest();
        customTests();
    }

    private static void typesTest() {
        long aLong = 0;
        System.out.println(aLong);
        aLong = 3;
        System.out.println(aLong);
        boolean aBoolean = false;
        System.out.println(aBoolean);
        double aDouble;
        // System.out.println(aDouble);
    }

    private static void operatorsTest() {
        int a = 5;
        boolean b;
        b = a < 10;
        System.out.println("b = " + b);

        int i = 4;
        int j = 5 + 9 * i;
        System.out.println("j = " + j);
        int k = 124 % 10;
        System.out.println("k = " + k);
    }

    private static void customTests() {
        int a = 10;
        a += 1;
        System.out.println(a);
    }
}
