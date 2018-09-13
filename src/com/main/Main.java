package com.main;

import com.ai.Automatic;
import com.ai.Automatic2;
import com.common.Constants;
import com.version.MainFrame;

import javax.swing.*;
import java.util.Scanner;

/**
 * Created by admin on 2018/9/10.
 */
public class Main {

    private static int flag = 0;

    public static void main(String[] args) {

        int a = 0;
        if (a == 0) {
            MainFrame mainFrame = new MainFrame();
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("是否先手，先为1否为0");
        flag = sc.nextInt();
        int[][] chessboard = new int[Constants.HEIGHT][Constants.WIDTH];
//        if (flag == 0) {
            chessboard[chessboard.length / 2][chessboard[0].length / 2] = Constants.BLACK;
        if (flag == 1) {
            chessboard[chessboard.length / 2 - 1][chessboard[0].length / 2 - 1] = Constants.WHITE;
        }

//        chessboard[5][4] = chessboard[4][5] = chessboard[3][5] = Constants.BLACK;
//        chessboard[6][5] = chessboard[6][3] = Constants.WHITE;

//        chessboard[6][4] = chessboard[6][6] = chessboard[5][6] = chessboard[4][5] = chessboard[2][4] = chessboard[3][3] = Constants.WHITE;
//        chessboard[5][4] = chessboard[4][4] = chessboard[3][4] = chessboard[1][1] = chessboard[1][4] = chessboard[2][2] = Constants.BLACK;
        print(chessboard);

        Automatic2 automatic2 = new Automatic2(chessboard);

        Automatic automatic = new Automatic(chessboard);
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
            long time1 = System.currentTimeMillis();
            automatic2.chess();
//            automatic.chess(Constants.BLACK);
            long time2 = System.currentTimeMillis();
            print(chessboard);
            System.out.println("消耗时间:" + (time2 - time1));
        }

    }

    private static void print(int[][] chessboard) {
        for (int i = 0; i < chessboard.length; i++) {
            if (i < 10) {
                System.out.print(" " + i + " ");
            } else {
                System.out.print(" " + i);
            }
        }
//        ⚫⚪
        System.out.println();
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard.length; j++) {
                if (chessboard[i][j] == Constants.BLACK) {
                    if (flag == 0) {
                        System.out.print("⬜ ");
                    } else {
                        System.out.print("⬛ ");
                    }
                } else if (chessboard[i][j] == Constants.WHITE) {
                    if (flag == 0) {
                        System.out.print("⬛ ");
                    } else {
                        System.out.print("⬜ ");
                    }
                } else if (chessboard[i][j] == Constants.EMPTY) {
//                    System.out.print("" + i + j + " ");
                    System.out.print(" . ");
                }
            }
            System.out.print(" " + i);
            System.out.println();
        }
        System.out.println();
    }
}
