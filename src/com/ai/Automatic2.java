package com.ai;

import com.algorithm.KMPAlgorithm;
import com.common.Constants;
import com.common.Score;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/9/12.
 */
public class Automatic2 {

    private int[][] chessboard;

    public Automatic2() {
    }

    public Automatic2(int[][] chessboard) {
        this.chessboard = chessboard;
    }

    public void chess() {
        int maxValue = Integer.MIN_VALUE;
        int maxi = 0;
        int maxj = 0;
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[0].length; j++) {
                if (chessboard[i][j] == Constants.EMPTY) {
                    chessboard[i][j] = Constants.BLACK;
                    int value = calAllWeight();
                    if (maxValue < value) {
                        maxValue = value;
                        maxi = i;
                        maxj = j;
                    }
                    chessboard[i][j] = Constants.EMPTY;
                }
            }
        }
        chessboard[maxi][maxj] = Constants.BLACK;
    }

    //计算整个棋盘的权值 （每行每列 每次判断六个点）ai先后手都属于黑色 电脑盘面得分减去玩家的盘面得分
    public int calAllWeight() {

        List<String> chessStrs = getAllChessStrs();
        int total = 0;
        for (String chessStr : chessStrs) {
            for (Score.ChessType chessType : Score.chessTypes) {
                int times = KMPAlgorithm.cal(chessStr, chessType.getType());
                    total += chessType.getScore() * times;
            }
        }
        return total;
    }

    //获取盘面所有线条的字符串
    private List<String> getAllChessStrs() {
        List<String> chessStrs = new ArrayList<>();

        //每行
        for (int i = 0; i < chessboard.length; i++) {
            String chessStr = "";
            for (int j = 0; j < chessboard[i].length; j++) {
                chessStr += chessboard[i][j];
            }
            chessStrs.add(chessStr);
        }
        //每列
        for (int j = 0; j < chessboard[0].length; j++) {
            String chessStr = "";
            for (int i = 0; i < chessboard.length; i++) {
                chessStr += chessboard[i][j];
            }
            chessStrs.add(chessStr);
        }
        //每条斜线
        for (int j = 0; j < chessboard[0].length; j++) {
            String chessStr = "";
            int i2 = 0;
            for (int j2 = j; i2 < chessboard.length && j2 < chessboard[0].length; i2++, j2++) {
                chessStr += chessboard[i2][j2];
            }
            if (chessStr.length() >= 5) {
                chessStrs.add(chessStr);
            }
        }
        //斜线边角  j=0时的起点
        for (int i = 1; i < chessboard.length; i++) {
            String chessStr = "";
            int j2 = 0;
            for (int i2 = i; i2 < chessboard.length && j2 < chessboard[0].length; i2++, j2++) {
                chessStr += chessboard[i2][j2];
            }
            if (chessStr.length() >= 5) {
                chessStrs.add(chessStr);
            }
        }

        //每条反斜线
        for (int j = 0; j < chessboard[0].length; j++) {
            String chessStr = "";
            int i2 = 0;
            for (int j2 = j; i2 <chessboard.length && j2 >= 0; i2++, j2--) {
                chessStr += chessboard[i2][j2];
            }
            if (chessStr.length() >= 5) {
                chessStrs.add(chessStr);
            }
        }
        //反斜线边角  j=chessboard.length-1时的起点
        for (int i = 1; i < chessboard.length; i++) {
            String chessStr = "";
            int j2 = chessboard.length - 1;
            for (int i2 = i; i2 < chessboard.length && j2 >= 0; i2++, j2--) {
                chessStr += chessboard[i2][j2];
            }
            if (chessStr.length() >= 5) {
                chessStrs.add(chessStr);
            }
        }

        return chessStrs;
    }

}
