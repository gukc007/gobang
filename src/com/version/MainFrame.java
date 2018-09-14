package com.version;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by admin on 2018/9/13.
 */
public class MainFrame extends JFrame{

    private MainJpanel mainJpanel;

    private MainMouseListener mainMouseListener;

    public MainFrame() {
        initCom();
        initFrame();
    }

    private void initCom() {
        mainJpanel = new MainJpanel();
        mainMouseListener = new MainMouseListener(mainJpanel);

        mainJpanel.addMouseListener(mainMouseListener);

        this.add(mainJpanel);
    }

    private void initFrame() {
        this.setLayout(null);
        this.setSize(700, 700);  //长 高
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //关闭则退出
        this.setLocationRelativeTo(null);  //居中
        this.setResizable(false); //不可伸缩
        this.setVisible(true);
    }
}
