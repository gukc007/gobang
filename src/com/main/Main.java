package com.main;

import com.ai.Automatic;
import com.ai.Automatic2;
import com.common.Constants;

import java.util.Scanner;

/**
 * Created by admin on 2018/9/10.
 */
public class Main {

    public static void main(String[] args) {
        int[][] chessboard = new int[Constants.HEIGHT][Constants.WIDTH];
        chessboard[5][5] = Constants.BLACK;

//        chessboard[5][4] = chessboard[4][5] = chessboard[3][5] = Constants.BLACK;
//        chessboard[6][5] = chessboard[6][3] = Constants.WHITE;

//        chessboard[6][4] = chessboard[6][6] = chessboard[5][6] = chessboard[4][5] = chessboard[2][4] = chessboard[3][3] = Constants.WHITE;
//        chessboard[5][4] = chessboard[4][4] = chessboard[3][4] = chessboard[1][1] = chessboard[1][4] = chessboard[2][2] = Constants.BLACK;
        print(chessboard);

        Automatic2 automatic2 = new Automatic2(chessboard);

//        Automatic automatic = new Automatic(chessboard);
        Scanner sc = new Scanner(System.in);
//        Automatic automatic = new Automatic(chessboard);
        while (true) {

            int i = sc.nextInt();
            int j = sc.nextInt();
            if (chessboard[i][j] != Constants.EMPTY) {
                System.out.println("输入地点不为空");
                continue;
            } else {
                chessboard[i][j] = Constants.WHITE;
            }
            print(chessboard);
            automatic2.chess();
            print(chessboard);
        }

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
