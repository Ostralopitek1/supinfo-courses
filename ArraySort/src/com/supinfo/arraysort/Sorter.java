package com.supinfo.arraysort;

import java.util.Random;

public class Sorter {
    public static void main(String[] args) {
        int[] array = getRandomIntArray(10);
        displayArray(array);
        bubbleSort(array);
        // insertionSort(array);
        // selectionSort(array);
        displayArray(array);
    }

    static int[] getRandomIntArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }

    public static int[] bubbleSort(int[] array) {
        boolean sorted = false;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < array.length-1; i++) {
                int value = array[i];
                int valueUp = array[i+1];
                if (value > valueUp) {
                    array[i] = valueUp;
                    array[i+1] = value;
                    sorted = false;
                }
            }
        }
        return array;
    }

    public static int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIdx = i;
            int minValue = array[i];
            for (int j = i; j < array.length; j++) {
                if (array[j] < minValue) {
                    minIdx = j;
                    minValue = array[j];
                }
            }
            array[minIdx] = array[i];
            array[i] = minValue;
        }
        return array;
    }

    public static int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (array[i] <= array[j]) {
                    int nextValue = array[i];
                    for (int k = j; k <= i; k++) {
                        int me = array[k];
                        array[k] = nextValue;
                        nextValue = me;
                    }
                    break;
                }
            }
        }
        return array;
    }

    public static void displayArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
