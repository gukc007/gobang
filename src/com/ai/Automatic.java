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
    public int calWeight(int i, int j, int chessColor) {
        int weight = 0;
        List<String> directions = directionCal(i, j);
        //⚫⚫⚫⚫⚫ 最高
        String target = "" + chessColor + chessColor + chessColor + chessColor + chessColor;
        for (int i1 = 0; i1 < directions.size(); i1++) {
            String direction = directions.get(i1);
            if (direction.contains(target)) {
                return 1000;
            }
        }

        //_⚫⚫⚫⚫_ 活四
        target = "" + Constants.EMPTY + chessColor + chessColor + chessColor + chessColor + Constants.EMPTY;
        for (int i1 = 0; i1 < directions.size(); i1++) {
            String direction = directions.get(i1);
            if (direction.contains(target)) {
                return 900;
            }
        }

        //死四 ⚫⚫⚫⚫_ _⚫⚫⚫⚫ ⚫⚫_⚫⚫ ⚫⚫⚫_⚫ ⚫_⚫⚫⚫
        String target1 = "" + Constants.EMPTY + chessColor + chessColor + chessColor + chessColor;
        String target2 = "" + chessColor + chessColor + chessColor + chessColor + Constants.EMPTY;
        String target3 = "" + chessColor + chessColor + Constants.EMPTY + chessColor + chessColor;
        String target4 = "" + chessColor + chessColor + chessColor + Constants.EMPTY + chessColor;
        String target5 = "" + chessColor + Constants.EMPTY + chessColor + chessColor + chessColor;
        int countDeadFour = 0;
        for (int i1 = 0; i1 < directions.size(); i1++) {
            String direction = directions.get(i1);
            if (direction.contains(target1) || direction.contains(target2) || direction.contains(target3)
                    || direction.contains(target4) || direction.contains(target5)) {
                //记数
                countDeadFour++;
                //移除不需要再计算的方向
                directions.remove(direction);
                i1--;
            }
        }
        if (countDeadFour >= 2) {
            //和活4一样效果
            return 900;
        }

        //_⚫⚫⚫_  _⚫_⚫⚫_  _⚫⚫_⚫_  活三
//        List<String> targets = new ArrayList<>();
        String target6 = "" + Constants.EMPTY + chessColor + chessColor + chessColor + Constants.EMPTY;
        String target7 = "" + Constants.EMPTY + chessColor + Constants.EMPTY + chessColor + chessColor + Constants.EMPTY;
        String target8 = "" + Constants.EMPTY + chessColor + chessColor + Constants.EMPTY + chessColor + Constants.EMPTY;
        int countLifeThree = 0;
        for (int i1 = 0; i1 < directions.size(); i1++) {
            String direction = directions.get(i1);
            if (direction.contains(target6) || direction.contains(target7) || direction.contains(target8)) {
                //记数
                countLifeThree++;
                //移除不需要再计算的方向
                directions.remove(direction);
                i1--;
            }
        }
        if (countDeadFour > 0 || countLifeThree > 0) {
            if (countDeadFour > 0 && countLifeThree > 0) {
                return 800;
            } else if (countLifeThree >= 2) {
                return 700;
            }
            weight  = 200;
        }

        //_⚫_⚫_ _⚫⚫__ __⚫⚫_ 活二
        String target9 = "" + Constants.EMPTY + chessColor + Constants.EMPTY + chessColor + Constants.EMPTY;
        String target10 = "" + Constants.EMPTY + chessColor + chessColor + Constants.EMPTY + Constants.EMPTY;
        String target11 = "" + Constants.EMPTY + Constants.EMPTY + chessColor + chessColor + Constants.EMPTY;
        int countLifeTwo = 0;
        for (int i1 = 0; i1 < directions.size(); i1++) {
            String direction = directions.get(i1);
            if (direction.contains(target9) || direction.contains(target10) || direction.contains(target11)) {
                //记数
                countLifeTwo++;
                //移除不需要再计算的方向
                directions.remove(direction);
                i1--;
            }
        }

        //⚫⚫⚫__  __⚫⚫⚫ ⚫⚫_⚫_ _⚫_⚫⚫ ⚫_⚫_⚫ 死三
        String target12 = "" + chessColor + chessColor + chessColor + Constants.EMPTY + Constants.EMPTY;
        String target13 = "" + Constants.EMPTY + Constants.EMPTY + chessColor + chessColor + chessColor;
        String target14 = "" + chessColor + chessColor + Constants.EMPTY + chessColor + Constants.EMPTY;
        String target15 = "" + Constants.EMPTY + chessColor + Constants.EMPTY + chessColor + chessColor;
        String target16 = "" + chessColor + Constants.EMPTY + chessColor + Constants.EMPTY + chessColor;
        int countDeadThree = 0;
        for (int i1 = 0; i1 < directions.size(); i1++) {
            String direction = directions.get(i1);
            if (direction.contains(target12) || direction.contains(target13) || direction.contains(target14)
                    || direction.contains(target15) || direction.contains(target16)) {
                //记数
                countDeadThree++;
                //移除不需要再计算的方向
                directions.remove(direction);
                i1--;
            }
        }

        weight = weight + (countLifeTwo + countDeadThree) * 10;

        return weight;
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
        for (int jc = leftDownMax; jc <= RightUpMax; jc++) {
            str += chessboard[ic][jc];
            ic--;
        }
        str = "";
        // Constants.BACK_SLASH:反斜，左斜
        ic = i - (j - leftUpMax);
        for (int jc = leftUpMax; jc <= RightDownMax; jc++) {
            str += chessboard[ic][jc];
            ic++;
        }
        directions.add(str);

        return directions;
    }
}
