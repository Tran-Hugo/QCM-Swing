package com.qcm.views;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.qcm.service.Login;
import com.qcm.service.LoginSubmit;

public class Header extends JPanel {
    private String userId;
    public Header(JPanel mainWrapper){
        // String user = "anis";

        CardLayout cardLayout = new CardLayout();
        setLayout(cardLayout);
        setMaximumSize(new Dimension(this.getMaximumSize().width, 50));
        JPanel loginWrap = new JPanel();
        JButton login = new JButton("login");
        loginWrap.add(login);
        add(loginWrap, "0");

        JPanel loginForm = new JPanel();
        JLabel loginLabel = new JLabel("Your ID");
        JTextField loginInput = new JTextField();
        loginInput.setColumns(15);
        JButton loginSubmit =new JButton("Submit");
        loginForm.add(loginLabel);
        loginForm.add(loginInput);
        loginForm.add(loginSubmit);

        add(loginForm, "1");

        login.addActionListener(new Login(cardLayout, this, "1"));

        JPanel connecterPanel = new JPanel();
        JLabel connectedUser = new JLabel();
        connecterPanel.add(connectedUser);
        add(connecterPanel, "2");

        loginSubmit.addActionListener(new LoginSubmit(loginForm, cardLayout, this, "2", connectedUser, mainWrapper));

        cardLayout.show(this, "0");


        // add(login);

    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return this.userId;
    }
}
