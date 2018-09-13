package com.dto;

/**
 * Created by admin on 2018/9/13.
 */
//用来存储 i j 和 value值
public class TreeDto {
    private int i;
    private int j;
    private int value;

    public TreeDto() {
    }

    public TreeDto(int value) {
        this.value = value;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
