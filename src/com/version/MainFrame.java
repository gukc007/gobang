package com.version;

import com.main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by admin on 2018/9/13.
 */
public class MainFrame extends JFrame{

    private MainJpanel mainJpanel;

    private MainJpanel.MainMouseListener mainMouseListener;

    private Main main;

    private JPanel tipJpanel;

    private JLabel tipJLabel;

    public MainFrame() {
    }

    public MainFrame(Main main) {
        this.main = main;
        initCom();
        initFrame();
    }

    private void initCom() {
        mainJpanel = new MainJpanel(main);
        mainMouseListener = new MainJpanel.MainMouseListener(mainJpanel);

        mainJpanel.addMouseListener(mainMouseListener);

//        tipJpanel = new JPanel();
//        tipJpanel.setBounds(100, 10, 300, 30);
//        tipJpanel.setBackground(Color.BLUE);

        tipJLabel = new JLabel();
        tipJLabel.setFont(new Font("Dialog", 0, 20));
//“dialog”代表字体，1代表样式(1是粗体，0是平常的）15是字号
////设置字体
//                jlabel.setForeground(Color.red);
////设置颜色
        tipJLabel.setBounds(100, 10, 300, 20);

        this.add(tipJLabel);
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

    public MainJpanel getMainJpanel() {
        return mainJpanel;
    }

    public void setMainJpanel(MainJpanel mainJpanel) {
        this.mainJpanel = mainJpanel;
    }

    public MainJpanel.MainMouseListener getMainMouseListener() {
        return mainMouseListener;
    }

    public void setMainMouseListener(MainJpanel.MainMouseListener mainMouseListener) {
        this.mainMouseListener = mainMouseListener;
    }
}
