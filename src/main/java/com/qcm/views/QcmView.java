package com.qcm.views;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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
    private int userId;
    private int selectedQcmId;
    private ArrayList<Qcm> allQcm;
    private ArrayList<Question> allQuestions;
    private int finalScore;

    public QcmView(int userId){
        this.userId = userId;

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
            // qcmCard.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 0));
            qcmCard.setLayout(new BorderLayout());
            qcmCard.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            JLabel qcmTheme = new JLabel(qcm.getTheme());
            JButton qcmButton = new JButton("Select");

            qcmButton.addActionListener(new GetQcm(qcm.getId(), this, cardLayout));

            qcmCard.add(qcmTheme, BorderLayout.WEST);
            qcmCard.add(qcmButton, BorderLayout.EAST);
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

    public void qcmFinalScore(CardLayout cardLayout){
        FinalScoreView finalScore = new FinalScoreView(getfinalScore(), getAllQuestions(), getQcmId(), getUserId());
        add(finalScore, "finalscore");

        cardLayout.show(this, "finalscore");
    }

    public void setFinalScore(int score){
        this.finalScore = score;
    }

    public int getfinalScore(){
        return this.finalScore;
    }

    public int getUserId(){
        return this.userId;
    }

    public void setQcmId(int qcmId){
        this.selectedQcmId = qcmId;
    }

    public int getQcmId(){
        return this.selectedQcmId;
    }
}
