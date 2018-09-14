package com.version;

import com.common.Constants;
import com.main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * Created by admin on 2018/9/14.
 */
public class MainMouseListener implements MouseListener {

    private MainJpanel jPanel;

    private boolean lock;

    private int color = Constants.BLACK;

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

        int chessSize = (int) (Constants.INDEX * 0.85);

        //查找最接近的点
        int x = (int) (Math.round(1.0 * xClick / Constants.INDEX) * Constants.INDEX);
        int y = (int) (Math.round(1.0 * yClick / Constants.INDEX) * Constants.INDEX);

        if ( x <= 0 || x >= 16 * Constants.INDEX || y <= 0 || y >= 16 * Constants.INDEX
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

        g.fillOval(x  - chessSize / 2, y  - chessSize / 2, chessSize, chessSize);

        jPanel.repaint();
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
