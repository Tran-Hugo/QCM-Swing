package com.qcm.service;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;

public class Login  implements ActionListener {

    private CardLayout cardLayout;
    private JPanel header;
    private String cardPage;

    public Login(CardLayout cardLayout, JPanel header, String cardPage){
        this.cardLayout = cardLayout;
        this.header = header;
        this.cardPage = cardPage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.cardLayout.show(this.header, this.cardPage);
    }
    
}
