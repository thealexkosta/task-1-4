package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class task4 {
    public task4() {
    }

    public static void main(String[] args) {
        String fileName = args[0];
        List<Integer> nums = readListFromFile(fileName);
        int minMoves = findMinMoves(nums);
        System.out.println(minMoves);
    }

    private static List<Integer> readListFromFile(String fileName) {
        List<Integer> nums = new ArrayList();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line;
            try {
                while((line = reader.readLine()) != null) {
                    String[] values = line.split(" ");
                    String[] var5 = values;
                    int var6 = values.length;

                    for(int var7 = 0; var7 < var6; ++var7) {
                        String value = var5[var7];
                        nums.add(Integer.parseInt(value));
                    }
                }
            } catch (Throwable var10) {
                try {
                    reader.close();
                } catch (Throwable var9) {
                    var10.addSuppressed(var9);
                }

                throw var10;
            }

            reader.close();
        } catch (IOException var11) {
            IOException e = var11;
            e.printStackTrace();
        }

        return nums;
    }

    private static int findMinMoves(List<Integer> nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int target;
        for(Iterator var3 = nums.iterator(); var3.hasNext(); max = Math.max(max, target)) {
            target = (Integer)var3.next();
            min = Math.min(min, target);
        }

        int minMoves = Integer.MAX_VALUE;

        for(target = min; target <= max; ++target) {
            int moves = 0;

            int num;
            for(Iterator var6 = nums.iterator(); var6.hasNext(); moves += Math.abs(num - target)) {
                num = (Integer)var6.next();
            }

            minMoves = Math.min(minMoves, moves);
        }

        return minMoves;
    }
}