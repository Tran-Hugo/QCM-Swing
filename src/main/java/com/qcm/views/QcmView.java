package com.qcm.views;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qcm.model.Qcm;
import com.qcm.model.Question;
import com.qcm.service.GetQcm;

public class QcmView extends JPanel{
    private ArrayList<Qcm> allQcm;
    private ArrayList<Question> allQuestions;

    public QcmView(){
        CardLayout cardLayout = new CardLayout();
        setLayout(cardLayout);

        JPanel qcmList = new JPanel();
        qcmList.setLayout(new BoxLayout(qcmList, BoxLayout.Y_AXIS));

        add(qcmList, "0");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/api/qcm"))
            .GET()
            .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonArray jsonArray = JsonParser.parseString(response.body().toString()).getAsJsonArray();

            ArrayList<Qcm> qcms= new ArrayList<Qcm>();
            for(int i=0; i< jsonArray.size(); i++){
                JsonObject jsonObject= jsonArray.get(i).getAsJsonObject();
                Qcm qcm = new Qcm(
                    jsonObject.get("id").getAsInt(),
                    jsonObject.get("theme").getAsString()
                );
                qcms.add(qcm);
            }

            this.allQcm = qcms;

        } catch (IOException exception){
            exception.printStackTrace();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        for (Qcm qcm : this.allQcm) {
            JPanel qcmCard = new JPanel();
            JLabel qcmTheme = new JLabel(qcm.getTheme());
            JButton qcmButton = new JButton("Select");

            qcmButton.addActionListener(new GetQcm(qcm.getId(), this, cardLayout));

            qcmCard.add(qcmTheme);
            qcmCard.add(qcmButton);
            qcmList.add(qcmCard);
        }
        cardLayout.show(this, "0");
    }

    public void setAllQuestions(ArrayList<Question> allQuestions){
        this.allQuestions = allQuestions;
    }

    public ArrayList<Question> getAllQuestions(){
        return this.allQuestions;
    }

    public void qcmQuestion(Question question, CardLayout cardLayout){
        QuestionView questionView = new QuestionView( this.allQuestions, question, cardLayout , this);
        add(questionView, "question " + question.getId());

        cardLayout.show(this, "question " + question.getId());
    }
}
