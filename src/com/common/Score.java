package com.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/9/12.
 */
public class Score {

    public static List<ChessType> chessTypes;

    static {
        chessTypes = new ArrayList<>();
        int e = Constants.EMPTY;
        int b = Constants.BLACK;
        int w = Constants.WHITE;
        //黑五连
        chessTypes.add(new ChessType(b, b, b, b, b, 100000000));
//        chessTypes.add(new ChessType(b, b, b, b, b, e, 1000000));
//        chessTypes.add(new ChessType(b, b, b, b, b, w, 1000000));
//        chessTypes.add(new ChessType(w, b, b, b, b, b, 1000000));

        //白五连
        chessTypes.add(new ChessType(w, w, w, w, w, -100000000));
//        chessTypes.add(new ChessType(w, w, w, w, w, e, -1000000));
//        chessTypes.add(new ChessType(b, w, w, w, w, w, -1000000));
//        chessTypes.add(new ChessType(w, w, w, w, w, b, -1000000));

        //黑活4
        chessTypes.add(new ChessType(e, b, b, b, b, e, 10000000));

        //白活4
        chessTypes.add(new ChessType(e, w, w, w, w, e, -10000000));

        //黑死4
        chessTypes.add(new ChessType(e, b, b, b, b, 100000));
        chessTypes.add(new ChessType(b, e, b, b, b, 100000));
        chessTypes.add(new ChessType(b, b, e, b, b, 100000));
        chessTypes.add(new ChessType(b, b, b, e, b, 100000));
        chessTypes.add(new ChessType(b, b, b, b, e, 100000));

        //白死4
        chessTypes.add(new ChessType(e, w, w, w, w, -100000));
        chessTypes.add(new ChessType(w, e, w, w, w, -100000));
        chessTypes.add(new ChessType(w, w, e, w, w, -100000));
        chessTypes.add(new ChessType(w, w, w, e, w, -100000));
        chessTypes.add(new ChessType(w, w, w, w, e, -100000));

        //黑活3
        chessTypes.add(new ChessType(e, b, b, b, e, e, 100000));
        chessTypes.add(new ChessType(e, e, b, b, b, e, 100000));
        chessTypes.add(new ChessType(e, b, e, b, b, e, 100000));
        chessTypes.add(new ChessType(e, b, b, e, b, e, 100000));

        //白活3
        chessTypes.add(new ChessType(e, w, w, w, e, e, -100000));
        chessTypes.add(new ChessType(e, e, w, w, w, e, -100000));
        chessTypes.add(new ChessType(e, w, e, w, w, e, -100000));
        chessTypes.add(new ChessType(e, w, w, e, w, e, -100000));

        //黑活2
        chessTypes.add(new ChessType(e, b, b, e, e, e, 200));
        chessTypes.add(new ChessType(e, b, e, b, e, e, 200));
        chessTypes.add(new ChessType(e, b, e, e, b, e, 200));
        chessTypes.add(new ChessType(e, e, b, b, e, e, 200));
        chessTypes.add(new ChessType(e, e, b, e, b, e, 200));
        chessTypes.add(new ChessType(e, e, e, b, b, e, 200));

        //白活2
        chessTypes.add(new ChessType(e, w, w, e, e, e, -200));
        chessTypes.add(new ChessType(e, w, e, w, e, e, -200));
        chessTypes.add(new ChessType(e, w, e, e, w, e, -200));
        chessTypes.add(new ChessType(e, e, w, w, e, e, -200));
        chessTypes.add(new ChessType(e, e, w, e, w, e, -200));
        chessTypes.add(new ChessType(e, e, e, w, w, e, -200));

        //黑死3
        chessTypes.add(new ChessType(w, b, b, b, e, e, 200));
        chessTypes.add(new ChessType(w, b, e, b, b, e, 200));
        chessTypes.add(new ChessType(w, b, b, e, b, e, 200));
        chessTypes.add(new ChessType(w, b, e, b, e, b, 200));
        chessTypes.add(new ChessType(e, e, b, b, b, w, 200));
        chessTypes.add(new ChessType(e, b, e, b, b, w, 200));
        chessTypes.add(new ChessType(e, b, b, e, b, w, 200));
        chessTypes.add(new ChessType(b, e, b, e, b, w, 200));

        //白死3
        chessTypes.add(new ChessType(b, w, w, w, e, e, -200));
        chessTypes.add(new ChessType(b, w, e, w, w, e, -200));
        chessTypes.add(new ChessType(b, w, w, e, w, e, -200));
        chessTypes.add(new ChessType(b, w, e, w, e, w, -200));
        chessTypes.add(new ChessType(e, e, w, w, w, b, -200));
        chessTypes.add(new ChessType(e, w, e, w, w, b, -200));
        chessTypes.add(new ChessType(e, w, w, e, w, b, -200));
        chessTypes.add(new ChessType(w, e, w, e, w, b, -200));

    }



    public static class ChessType {
        private String type;

        private int score;

        public ChessType() {
        }

        public ChessType(String type, int score) {
            this.type = type;
            this.score = score;
        }

//        //7段
//        public ChessType(int i1, int i2, int i3, int i4, int i5, int i6, int i7, int score) {
//            this.type = "" + i1 + i2 + i3 + i4 + i5 + i6 + i7;
//            this.score = score;
//        }

        //6段
        public ChessType(int i1, int i2, int i3, int i4, int i5, int i6, int score) {
            this.type = "" + i1 + i2 + i3 + i4 + i5 + i6;
            this.score = score;
        }
        //5段
        public ChessType(int i1, int i2, int i3, int i4, int i5, int score) {
            this.type = "" + i1 + i2 + i3 + i4 + i5;
            this.score = score;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}
