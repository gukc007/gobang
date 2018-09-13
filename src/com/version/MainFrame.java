package com.version;

import javax.swing.*;
import java.awt.*;

/**
 * Created by admin on 2018/9/13.
 */
public class MainFrame extends JFrame{

    private MainJpanel mainJpanel;

    public MainFrame() {
        initCom();
        initFrame();
    }

    private void initCom() {
        mainJpanel = new MainJpanel();

//        jPanel.setSize(100, 100);
//        mainJpanel.setBounds(50, 50, 610, 610);
//        jPanel.setPreferredSize(new Dimension(100, 100));

//        jPanel.setLayout();
//        mainJpanel.setBackground(new Color(242, 225, 179));
//        mainJpanel.

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
