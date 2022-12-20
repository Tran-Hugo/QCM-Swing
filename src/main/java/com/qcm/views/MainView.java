package com.qcm.views;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame {
    private static MainView instance;

    private MainView(){
        setSize(800, 500);
        setLocationRelativeTo(null);
        // setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Welcome!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        JPanel mainWrapper = new JPanel();
        mainWrapper.setLayout(new BoxLayout(mainWrapper, BoxLayout.Y_AXIS));

        Header header = new Header(mainWrapper);
        mainWrapper.add(header);
        getContentPane().add(mainWrapper);

        setVisible(true);
    }
    public static MainView createApp(){
        if (instance == null) {
            instance = new MainView();
            return instance;
        } else {
            return instance;
        }
    }
}
