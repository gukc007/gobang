package com.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by admin on 2018/1/16.
 */
public class KMPAlgorithm {

    private static final String RED = "red";
    private static final String BLUE = "blue";
    private static final String YELLOW = "yellow";
    private static final String WHITE = "white";

    public static int cal(String str, String subString) {

        int[] next = getNextValue(subString);

        List<Integer> allIndexs = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            while (true) {
                if (index < 0) {
                    index = 0;
                    break;
                }
                if (subString.charAt(index) == str.charAt(i)) {
                    index++;
                    if (index >= subString.length()) {
//                        System.out.println("找到一处位置:" + i);
                        allIndexs.add(i - subString.length() + 1);
                        index = next[index] - 1;
                    }
                    break;
                } else {
                    index = next[index] - 1;
                }
            }
        }
        return allIndexs.size();
    }


    private static int[] getNextValue(String subString) {
        int[] next = new int[subString.length() + 1];

        next[0] = 0;
        for (int i = 1; i <= subString.length(); i++) {
            char c = subString.charAt(i - 1);
            int index = next[i - 1] - 1;
            while (true) {
                if (index < 0) {
                    next[i] = 1;
                    break;
                }
                if (c == subString.charAt(index)) {
                    next[i] = index + 1 + 1;
                    break;
                } else {
                    index = next[index] - 1;
                }
            }
        }
//        System.out.println("生成的kmp码值:");
//        System.out.println(Arrays.toString(next));
        return next;
    }
}
