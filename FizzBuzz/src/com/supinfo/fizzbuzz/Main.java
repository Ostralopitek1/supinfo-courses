package com.supinfo.fizzbuzz;

public class Main {
    public static void main(String[] args) {
        fizzBuzz(100);
    }

    private static void fizzBuzz(int until) {
        for (int i = 0; i < until; i++) {
            if (i % 10 == 0) {
                System.out.println();
            }
            String value = i + "";  // implicit cast to string
            if (i % 3 == 0 && i % 5 == 0) {
                value = "fizzbuzz";
            } else if (i % 3 == 0) {
                value = "fizz";
            } else if (i % 5 == 0) {
                value = "buzz";
            }
            System.out.print(value + " ");
        }
    }
}
