package com.main;

import com.ai.Automatic;
import com.ai.Automatic2;
import com.common.Constants;
import com.dto.TreeDto;
import com.version.MainFrame;
import com.version.MainJpanel;
import sun.invoke.empty.Empty;

import javax.swing.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by admin on 2018/9/10.
 */
public class Main {

    private int flag = 0;

    private int[][] chessboard;

    private Automatic2 automatic2;

    private MainJpanel mainJpanel;

    private MainFrame mainFrame;

    public static void main(String[] args) {

        Main main = new Main();
        main.begin();
    }

    //为了避免静态方法
    public void begin() {
        mainFrame = new MainFrame(this);
        mainJpanel = mainFrame.getMainJpanel();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        chessboard = new int[Constants.HEIGHT][Constants.WIDTH];

        automatic2 = new Automatic2(chessboard);
        if (flag == 0) {
            int i = chessboard.length / 2;
            int j = chessboard[0].length / 2;
            //因为要改变线的值
            automatic2.chessChangeLineValue(i, j, Constants.BLACK);
            mainJpanel.printChess(i, j);
        } else if (flag == 1) {
            int i = chessboard.length / 2 - 1;
            int j = chessboard[0].length / 2 - 1;
            chessboard[i][j] = Constants.WHITE;
            mainJpanel.printChess(i, j);
        }

//        chessboard[5][4] = chessboard[4][5] = chessboard[3][5] = Constants.BLACK;
//        chessboard[6][5] = chessboard[6][3] = Constants.WHITE;

//        chessboard[6][4] = chessboard[6][6] = chessboard[5][6] = chessboard[4][5] = chessboard[2][4] = chessboard[3][3] = Constants.WHITE;
//        chessboard[5][4] = chessboard[4][4] = chessboard[3][4] = chessboard[1][1] = chessboard[1][4] = chessboard[2][2] = Constants.BLACK;


//        Automatic automatic = new Automatic(chessboard);
//        Automatic automatic = new Automatic(chessboard);
    }

    //电脑下
    public void aiChess() {
        long time1 = System.currentTimeMillis();
        TreeDto dto = automatic2.chess();
//            automatic.chess(Constants.BLACK);
        mainJpanel.printChess(dto.getI(), dto.getJ());
        long time2 = System.currentTimeMillis();
        mainFrame.getTipJLabel().setText("电脑决策完毕,耗时:" + (1.0 * (time2 - time1)) / 1000 + "秒");
        System.out.println("消耗时间:" + (time2 - time1));
    }

    //改变棋盘
    public void changeChessboard(int i, int j) {
        if (chessboard[i][j] == Constants.EMPTY) {
            //玩家下棋
            automatic2.chessChangeLineValue(i, j, Constants.WHITE);
            System.out.print("电脑决策中....");
            mainFrame.getTipJLabel().setText("电脑决策中....");
            //多线程
            ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
            singleThreadExecutor.execute(this::aiChess);
        }
    }

    //检查下棋是否合法
    public boolean judgeChess(int i, int j) {
        if (i < 0 || i >= 15 || j < 0 || j >= 15
                || chessboard[i][j] != Constants.EMPTY) {
            return false;
        }
        return true;
    }

    private void print(int[][] chessboard) {
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
