package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class task2 {
    public task2() {
    }

    public static void main(String[] args) {
        String centerFileName = args[0];
        String pointFileName = args[1];

        try {
            File centerFile = new File(centerFileName);
            Scanner centerScanner = new Scanner(centerFile);
            float centerX = centerScanner.nextFloat();
            float centerY = centerScanner.nextFloat();
            float radius = centerScanner.nextFloat();
            centerScanner.close();
            File pointFile = new File(pointFileName);
            Scanner pointScanner = new Scanner(pointFile);
            StringBuilder result = new StringBuilder();

            while(pointScanner.hasNextFloat()) {
                float pointX = pointScanner.nextFloat();
                float pointY = pointScanner.nextFloat();
                float distance = (float)Math.sqrt(Math.pow((double)(pointX - centerX), 2.0) + Math.pow((double)(pointY - centerY), 2.0));
                if (distance < radius) {
                    result.append("1 ");
                } else if (distance == radius) {
                    result.append("0 ");
                } else {
                    result.append("2 ");
                }
            }

            pointScanner.close();
            System.out.println(result.toString().trim());
        } catch (FileNotFoundException var14) {
            FileNotFoundException e = var14;
            System.out.println("Файл не найден: " + e.getMessage());
        }

    }
}
