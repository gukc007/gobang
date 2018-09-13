package com.version;

import javax.swing.*;
import java.awt.*;

/**
 * Created by admin on 2018/9/13.
 */
public class MainJpanel extends JPanel {
    private int index = 37;

    public MainJpanel() {
        int width = index * 16;
        int point = (700 - (index * 16)) / 2;
        this.setBounds(point, point, width, width);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(242, 225, 179));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(new Color(184, 162, 160));
        //竖线
        for (int x = 0; x < 15; x++) {
            g.drawLine(index + x * index, index, index + x * index, index * 15);
//            g.drawLine(100, 100, 200, 200);
        }
        //横线
        for (int y = 0; y < 15; y++) {
            g.drawLine(index, index + y * index, index * 15, index + y * index);
//            g.drawLine(100, 100, 200, 200);
        }
        int pointSize = index / 5;
        //圆点
        g.fillOval(index + 3 * index - pointSize / 2, index + 3 * index - pointSize / 2, pointSize, pointSize);
        g.fillOval(index + 11 * index - pointSize / 2, index + 3 * index - pointSize / 2, pointSize, pointSize);
        g.fillOval(index + 7 * index - pointSize / 2, index + 7 * index - pointSize / 2, pointSize, pointSize);
        g.fillOval(index + 3 * index - pointSize / 2, index + 11 * index - pointSize / 2, pointSize, pointSize);
        g.fillOval(index + 11 * index - pointSize / 2, index + 11 * index - pointSize / 2, pointSize, pointSize);
    }
}
