package com.main;

import com.ai.Automatic;
import com.common.Constants;

import java.util.Scanner;

/**
 * Created by admin on 2018/9/10.
 */
public class Main {

    public static void main(String[] args) {
        int[][] chessboard = new int[Constants.HEIGHT][Constants.WIDTH];
        chessboard[5][5] = Constants.BLACK;
//        chessboard[4][4] = Constants.WHITE;
//        chessboard[2][5] = Constants.BLACK;
//        chessboard[1][5] = Constants.WHITE;
        print(chessboard);

        Automatic automatic = new Automatic(chessboard);
        Scanner sc = new Scanner(System.in);
//        Automatic automatic = new Automatic(chessboard);
        while(true) {

            int i = sc.nextInt();
            int j = sc.nextInt();
            if (chessboard[i][j] != Constants.EMPTY) {
                System.out.println("输入地点不为空");
                continue;
            } else {
                chessboard[i][j] = Constants.WHITE;
            }
            print(chessboard);
            automatic.chess(Constants.BLACK);
            print(chessboard);
        }

//        print(values);

//        automatic.calWeight();
    }

    private static void print(int[][] chessboard) {
        for (int i = 0; i < chessboard.length; i++) {
            System.out.print(" " + i + " ");
        }
//        ⚫⚪
        System.out.println();
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard.length; j++) {
                if (chessboard[i][j] == Constants.BLACK) {
                    System.out.print("⬜ ");
                } else if (chessboard[i][j] == Constants.WHITE) {
                    System.out.print("⬛ ");
                } else if (chessboard[i][j] == Constants.EMPTY) {
//                    System.out.print("" + i + j + " ");
                    System.out.print(" . ");
                } else {
                    if (chessboard[i][j] < 10) {
                        System.out.print(chessboard[i][j] + "  ");
                    } else {
                        System.out.print(chessboard[i][j] + " ");
                    }
                }
            }
            System.out.print(" " + i);
            System.out.println();
        }
        System.out.println();
    }
}
