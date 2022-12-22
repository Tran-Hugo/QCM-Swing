package com.qcm.service;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.qcm.model.Question;
import com.qcm.model.Response;
import com.qcm.views.QcmView; 

public class GetNextQuestion implements ActionListener {

    private QcmView qcmView;
    private Question question;
    private CardLayout cardLayout;
    private JPanel responsesWrapper;
    
    public GetNextQuestion(QcmView qcmView, Question question, CardLayout cardLayout, JPanel responsesWrapper){
        this.qcmView = qcmView;
        this.question = question;
        this.cardLayout = cardLayout;
        this.responsesWrapper = responsesWrapper;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Component parentComp : this.responsesWrapper.getComponents()) {
            if (parentComp.getClass().equals(JPanel.class)) {
                JPanel wrapPanel = (JPanel) parentComp;
                for (Component childComp : wrapPanel.getComponents()) {
                    if (childComp.getClass().equals(JCheckBox.class)) {
                        JCheckBox checkBox = (JCheckBox) childComp;
                        if (checkBox.isSelected()) {
                            Integer responseId = Integer.parseInt(checkBox.getName());
                            Boolean isCorrect =  getResponse(responseId);

                            if (isCorrect) {
                                this.qcmView.setFinalScore(this.qcmView.getfinalScore() + 1);
                            }
                        }
                    }
                }
            }
        }
        this.qcmView.qcmQuestion(this.question, this.cardLayout);
    }

    public Boolean getResponse(int id){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/api/response/" + id))
            .GET()
            .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonObject = JsonParser.parseString(response.body().toString()).getAsJsonObject();
            Boolean isCorrect = jsonObject.get("is_correct").getAsBoolean();

            return isCorrect;
        } catch (IOException exception){
            exception.printStackTrace();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        return false;
    }
    
}
