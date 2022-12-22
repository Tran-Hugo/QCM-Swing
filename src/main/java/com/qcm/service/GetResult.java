package com.qcm.service;

import java.awt.*;
import java.awt.event.*;

import com.qcm.views.QcmView;

public class GetResult implements ActionListener {

    private QcmView qcmView;
    private CardLayout cardLayout;

    public GetResult(QcmView qcmView, CardLayout cardLayout){
        this.qcmView = qcmView;
        this.cardLayout = cardLayout;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.qcmView.qcmFinalScore(cardLayout);
    }
    

}
