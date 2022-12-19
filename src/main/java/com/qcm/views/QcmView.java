package com.qcm.views;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class QcmView extends JPanel{
    private JPanel qcmBody = new JPanel();
    public QcmView(Header header){
        CardLayout cardLayout = new CardLayout();
        setLayout(cardLayout);

        JLabel qcmNotLogged = new JLabel("Merci de effectuer le Login");
        qcmBody.add(qcmNotLogged);
        add(qcmBody, "0");

        JPanel qcms = new JPanel();
        JLabel qcmtest = new JLabel("Vous ete connecter");
        qcms.add(qcmtest);
        add(qcms, "1");
        System.out.println(header.getUserId());
        if (header.getUserId() == "1") {
            cardLayout.show(this, "1");
        }
        cardLayout.show(this, "0");
    }
}
