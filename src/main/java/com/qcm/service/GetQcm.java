package com.qcm.service;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qcm.model.Question;
import com.qcm.views.QcmView;


public class GetQcm implements ActionListener {
    private int id;
    private QcmView qcm;
    private CardLayout cardLayout;
    private ArrayList<Question> allQuestion;
    public GetQcm(int id, QcmView qcm, CardLayout cardLayout){
        this.id = id;
        this.qcm = qcm;
        this.cardLayout = cardLayout;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/api/qcm/" + this.id + "/questions"))
            .GET()
            .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonArray jsonArray = JsonParser.parseString(response.body().toString()).getAsJsonArray();
            ArrayList<Question> questions = new ArrayList<Question>();
            for(int i=0; i< jsonArray.size(); i++){
                JsonObject jsonObject= jsonArray.get(i).getAsJsonObject();
                Question question = new Question(
                    jsonObject.get("id").getAsInt(),
                    jsonObject.get("type").getAsString(),
                    jsonObject.get("question").getAsString()

                );
                questions.add(question);
            }
            System.out.println(questions);
            this.qcm.setAllQuestions(questions);

            this.qcm.qcmQuestion(questions.get(0), this.cardLayout);
        } catch (IOException exception){
            exception.printStackTrace();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
