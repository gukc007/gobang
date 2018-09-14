package com.ai;

import com.algorithm.KMPAlgorithm;
import com.common.Constants;
import com.common.Score;
import com.dto.TreeDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/9/12.
 */
public class Automatic2 {

    private int[][] chessboard;

    private int [] transverseLines; //横线
    private int [] verticalLines; //竖线
    private int [] slashs; //斜，右斜
    private int [] backSlashs;  //反斜，左斜
    private int totalLines;

    public Automatic2() {
    }

    public Automatic2(int[][] chessboard) {
        this.chessboard = chessboard;
        transverseLines = new int[chessboard.length];  // i
        verticalLines = new int[chessboard[0].length];  // j
        slashs = new int[chessboard.length + chessboard[0].length];       // i - j + (chessboard[0].length)
        backSlashs = new int[chessboard.length + chessboard[0].length];  // i + j
    }

    public TreeDto chess() {
        TreeDto dto = deep(1, Integer.MAX_VALUE);
        chessChangeLineValue(dto.getI(), dto.getJ(), Constants.BLACK);
        return dto;
    }

    //会改变线条保存的值
    public int chessChangeLineValue(int i, int j, int chessColor) {
        chessboard[i][j] = chessColor;
        return changePointLines(i, j);
    }

    //博弈树
    private TreeDto deep(int depth, int parentValue) {
        if (depth > 2) {
            //最好是考虑奇数层
//            int value = calAllWeight();
            return new TreeDto(totalLines);
        }
        if (depth % 2 == 1) {
            //奇数层为最大值
            TreeDto dto = new TreeDto(Integer.MIN_VALUE);
            for (int i = 0; i < chessboard.length; i++) {
                for (int j = 0; j < chessboard[0].length; j++) {
                    if (chessboard[i][j] == Constants.EMPTY) {
                        chessboard[i][j] = Constants.BLACK;
                        int value = handleLineBeforeDeep(i, j, depth, dto.getValue()).getValue();
                        chessboard[i][j] = Constants.EMPTY;
                        if (value > dto.getValue()) {
                            dto.setI(i);
                            dto.setJ(j);
                            dto.setValue(value);
                            if (dto.getValue() >= parentValue) {
                                //剪枝
                                return dto;
                            }
                        }
                    }
                }
            }
            return dto;
        } else {
            //偶数层为最小值
            TreeDto dto = new TreeDto(Integer.MAX_VALUE);
            for (int i = 0; i < chessboard.length; i++) {
                for (int j = 0; j < chessboard[0].length; j++) {
                    if (chessboard[i][j] == Constants.EMPTY) {
                        chessboard[i][j] = Constants.WHITE;
                        int value = handleLineBeforeDeep(i, j, depth, dto.getValue()).getValue();
                        chessboard[i][j] = Constants.EMPTY;
                        if (value < dto.getValue()) {
                            dto.setValue(value);
                            dto.setI(i);
                            dto.setJ(j);
                            if (dto.getValue() <= parentValue) {
                                //剪枝
                                return dto;
                            }
                        }
                    }
                }
            }
            return dto;
        }
    }

    // 在搜索前要进行线的值变换
    private TreeDto handleLineBeforeDeep(int i, int j, int depth, int dtoValue) {
        int transverseLine = transverseLines[i];
        int verticalLine = verticalLines[j];
        int slash = slashs[i - j + chessboard[0].length];
        int backSlash = backSlashs[i + j];

        totalLines -= transverseLine;
        totalLines -= verticalLine;
        totalLines -= slash;
        totalLines -= backSlash;

        int newLines = changePointLines(i, j);
//        totalLines += newLines;

        //dtoValue用来剪枝
        TreeDto dto = deep(depth + 1, dtoValue);

        totalLines -= newLines;

        totalLines += transverseLine;
        totalLines += verticalLine;
        totalLines += slash;
        totalLines += backSlash;

        transverseLines[i] = transverseLine;
        verticalLines[j] = verticalLine;
        slashs[i - j + chessboard[0].length] = slash;
        backSlashs[i + j] = backSlash;

        return dto;
    }

    //获取该点上的分数
    private int changePointLines(int i, int j) {
        int value = 0;

        //横线
        String transverseLine = "";
        for (int k = 0; k < chessboard[0].length; k++) {
            transverseLine += chessboard[i][k];
        }
        transverseLines[i] = getLineValue(transverseLine);
        value += transverseLines[i];

        //竖线
        String verticalLine = "";
        for (int k = 0; k < chessboard.length; k++) {
            verticalLine += chessboard[k][j];
        }
        verticalLines[j] = getLineValue(verticalLine);
        value += verticalLines[j];

        //斜线
        //找左下角最接近的是i 还是 j
        int is, js;
        if (i <= j) {
            is = 0;
            js = j - i;
        } else {
            is = i - j;
            js = 0;
        }
        String slash = "";
        while (is < chessboard.length && js < chessboard[0].length) {
            slash += chessboard[is][js];
            is++;
            js++;
        }
        if (slash.length() >= 5) {
            slashs[i - j + chessboard[0].length] = getLineValue(slash);
            value += slashs[i - j + chessboard[0].length];
        }

        //反斜线
        //找左上角
        int minLength = chessboard.length < chessboard[0].length ? chessboard.length : chessboard[0].length;
        if (i + j <= minLength - 1) {
            is = i + j;
            js = 0;
        } else {
            is = chessboard.length - 1;
            js = i + j - (chessboard.length - 1);
        }
        String backSlash = "";
        while (is >= 0 && js < chessboard[0].length) {
            backSlash += chessboard[is][js];
            is --;
            js ++;
        }
        if (backSlash.length() >= 5) {
            backSlashs[i + j] = getLineValue(backSlash);
            value += backSlashs[i + j];
        }

        totalLines += value;

        return value;
    }

    public int getLineValue(String chessLine) {
        int value = 0;
        for (Score.ChessType chessType : Score.chessTypes) {
            int times = KMPAlgorithm.cal(chessLine, chessType.getType());
            value += chessType.getScore() * times;
//                if (chessStr.contains(chessType.getType())) {
//                    total += chessType.getScore();
//                }
        }
        return value;
    }

    //计算整个棋盘的权值 （每行每列 每次判断六个点）ai先后手都属于黑色 电脑盘面得分减去玩家的盘面得分
    public int calAllWeight() {

        List<String> chessStrs = getAllChessStrs();

        int total = 0;
        for (String chessStr : chessStrs) {
            for (Score.ChessType chessType : Score.chessTypes) {
                int times = KMPAlgorithm.cal(chessStr, chessType.getType());
                total += chessType.getScore() * times;
//                if (chessStr.contains(chessType.getType())) {
//                    total += chessType.getScore();
//                }
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


//    public int test(int i, int j, int chessColor) {
//        chessboard[i][j] = chessColor;
//        int transverseLine = transverseLines[i];
//        int verticalLine = verticalLines[j];
//        int slash = slashs[i - j + chessboard[0].length];
//        int backSlash = backSlashs[i + j];
//
//        totalLines -= transverseLine;
//        totalLines -= verticalLine;
//        totalLines -= slash;
//        totalLines -= backSlash;
//
//        int newLines = changePointLines(i, j);
//        totalLines += newLines;
//
//        //dtoValue用来剪枝
////        TreeDto dto = deep(depth + 1, dtoValue);
//
//        return totalLines;
//
////        totalLines -= newLines;
////
////        totalLines += transverseLine;
////        totalLines += verticalLine;
////        totalLines += slash;
////        totalLines += backSlash;
////
////        transverseLines[i] = transverseLine;
////        verticalLines[j] = verticalLine;
////        slashs[i - j + chessboard[0].length] = slash;
////        backSlashs[i + j] = backSlash;
//    }

}
