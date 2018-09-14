package com.version;

import com.common.Constants;
import com.main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * Created by admin on 2018/9/13.
 */
public class MainJpanel extends JPanel {
    private int index = Constants.INDEX;

    private volatile BufferedImage bufferedImage;

    private static Main main;

    private volatile static boolean lock;

    private static int color = Constants.BLACK;

    private static int chessSize = (int) (Constants.INDEX * 0.85);

    public MainJpanel() {
//        initBufferImage();
    }

    public MainJpanel(Main main) {
        MainJpanel.main = main;
        int width = index * 16;
        int point = (700 - (index * 16)) / 2;
        this.setBounds(point, point, width, width);
    }

    private void initBufferImage() {
        bufferedImage = (BufferedImage) this.createImage(this.getWidth(), this.getHeight());
        Graphics g = bufferedImage.getGraphics();
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

    @Override
    public void paint(Graphics g) {
        if (bufferedImage == null) {
            initBufferImage();
        }
        g.drawImage(bufferedImage, 0, 0, null);
    }

    //输入棋子
    public void printChess(int i, int j) {
        Graphics g = bufferedImage.getGraphics();

        //电脑的颜色要相反，因为电脑记录的时候是黑色，但是输出的时候是反色
        if (color == Constants.BLACK) {
            g.setColor(Color.BLACK);
            color = Constants.WHITE;
        } else if (color == Constants.WHITE) {
            g.setColor(Color.WHITE);
            color = Constants.BLACK;
        }

        int x = (j + 1) * Constants.INDEX;
        int y = (i + 1) * Constants.INDEX;

        g.fillOval(x  - chessSize / 2, y - chessSize / 2, chessSize, chessSize);

        //用户可以输入
        lock = false;

        this.repaint();
    }

    public static class MainMouseListener implements MouseListener {

        private MainJpanel jPanel;

        public MainMouseListener() {
        }

        public MainMouseListener(MainJpanel jPanel) {
            this.jPanel = jPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (lock) {
                return;
            }
            int xClick = e.getX();
            int yClick = e.getY();

            BufferedImage bufferedImage = jPanel.getBufferedImage();

            Graphics g = bufferedImage.getGraphics();

            //查找最接近的点

            int i = (int) Math.round(1.0 * yClick / Constants.INDEX) - 1;
            int j = (int) Math.round(1.0 * xClick / Constants.INDEX) - 1;

            int x = (j + 1) * Constants.INDEX;
            int y = (i + 1) * Constants.INDEX;

            if (!main.judgeChess(i, j)
                    || Math.pow(xClick - x, 2) + Math.pow(yClick - y, 2) > Math.pow(chessSize / 2, 2)) {
                return;
            }
            if (color == Constants.BLACK) {
                g.setColor(Color.BLACK);
                color = Constants.WHITE;
            } else if (color == Constants.WHITE) {
                g.setColor(Color.WHITE);
                color = Constants.BLACK;
            }
            //锁住让机器下
            lock = true;

            g.fillOval(x  - chessSize / 2, y  - chessSize / 2, chessSize, chessSize);

            jPanel.repaint();

            main.changeChessboard(i, j);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public static boolean isLock() {
        return lock;
    }

    public static void setLock(boolean lock) {
        MainJpanel.lock = lock;
    }

    public static int getColor() {
        return color;
    }

    public static void setColor(int color) {
        MainJpanel.color = color;
    }
}
