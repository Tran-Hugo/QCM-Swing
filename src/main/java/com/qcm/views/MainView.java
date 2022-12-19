package com.qcm.views;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame {

    public MainView(){
        setSize(800, 500);
        setLocationRelativeTo(null);
        // setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Welcome!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        JPanel mainWrapper = new JPanel();
        mainWrapper.setLayout(new BoxLayout(mainWrapper, BoxLayout.Y_AXIS));

        Header header = new Header();
        header.getUserId();
        mainWrapper.add(header);
        // assertEquals();
        QcmView qcmView = new QcmView(header);
        mainWrapper.add(qcmView);
        getContentPane().add(mainWrapper);

        setVisible(true);
    }
}
