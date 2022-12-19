package com.qcm.service;

import java.awt.*;
import java.awt.event.*;

import com.qcm.model.Question;
import com.qcm.views.QcmView; 

public class GetNextQuestion implements ActionListener {

    private QcmView qcmView;
    private Question question;
    private CardLayout cardLayout;
    
    public GetNextQuestion(QcmView qcmView, Question question, CardLayout cardLayout){
        this.qcmView = qcmView;
        this.question = question;
        this.cardLayout = cardLayout;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.qcmView.qcmQuestion(this.question, this.cardLayout);
    }
    
}
