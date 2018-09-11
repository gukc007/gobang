package com.ai;

import com.common.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/9/11.
 */
public class Automatic {

    private int[][] chessboard;

    public Automatic() {
    }

    public Automatic(int[][] chessboard) {
        this.chessboard = chessboard;
        initChessboard();
    }

    //初始化
    public void initChessboard() {
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                chessboard[i][j] = Constants.EMPTY;
            }
        }
    }

    //计算点的权值
    public int calWeight(int i, int j) {
        int[] directions = {Constants.TRANSVERSE_LINE, Constants.VERTICAL_LINE, Constants.SLASH, Constants.BACK_SLASH};
        //⚫⚫⚫⚫⚫ 最高
        return 0;
    }

    //四个方向十个
    public List<String> directionCal(int i, int j) {
        int leftMax = j - 4 >= 0 ? j - 4 : 0;
        int rightMax = j + 4 < chessboard[0].length ? j + 4 : chessboard[0].length - 1;
        int upMax = i - 4 >= 0 ? i - 4 : 0;
        int downMax = i + 4 < chessboard.length ? i + 4 : chessboard.length - 1;
        int leftUpMax = j - leftMax <= i - upMax ? leftMax : j - (i - upMax);
        int RightUpMax = rightMax - j <= i - upMax ? rightMax : j + (i - upMax);
        int leftDownMax = j - leftMax <= downMax - i ? leftMax : j - (downMax - i);
        int RightDownMax = rightMax - j <= downMax - i ? rightMax : j + (downMax - i);
        List<String> directions = new ArrayList<>();
        // Constants.TRANSVERSE_LINE:横线
        String str = "";
        for (int jc = leftMax; jc <= rightMax; jc++) {
            str += chessboard[i][jc];
        }
        directions.add(str);
        str = "";
        // Constants.VERTICAL_LINE:竖线
        for (int ic = upMax; ic <= downMax; ic++) {
            str += chessboard[ic][j];
        }
        directions.add(str);
        str = "";
        // Constants.SLASH:右斜
        int ic = i + (j - leftDownMax);
        for (int jc = leftDownMax ; jc <= RightUpMax; jc++) {
            str += chessboard[ic][jc];
            ic--;
        }
        str = "";
        // Constants.BACK_SLASH:反斜，左斜
        ic = i - (j - leftUpMax);
        for (int jc = leftUpMax ; jc <= RightDownMax; jc++) {
            str += chessboard[ic][jc];
            ic++;
        }
        directions.add(str);

        return directions;
    }
}
