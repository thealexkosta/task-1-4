package org.example;

import java.util.Arrays;

public class task1 {
    public task1() {
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        int[] arr = new int[n];
        Arrays.setAll(arr, (i) -> {
            ++i;
            return i;
        });
        int current = 0;

        do {
            System.out.print(arr[current]);
            current = (current + m - 1) % n;
        } while(current != 0);

    }
}