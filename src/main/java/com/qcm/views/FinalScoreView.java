package com.qcm.views;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qcm.model.Question;
import com.qcm.model.Score;

public class FinalScoreView extends JPanel {
    
    public FinalScoreView(int finalScore, ArrayList<Question> allQuestions, int qcmId , int userId){
        HashMap<String, Integer> data = new HashMap<>();
        data.put("score", finalScore);
        data.put("id_qcm", qcmId);
        data.put("id_user", userId);
        postFinalScore(data);
        JLabel user = new JLabel("User: " + Integer.toString(userId));
        JLabel qcm = new JLabel("Qcm: " + Integer.toString(qcmId));
        JLabel title = new JLabel("Your final score is: ");
        JLabel finalscore = new JLabel(Integer.toString(finalScore) + "/" + Integer.toString(allQuestions.size()));

        add(qcm);
        add(user);
        add(title);
        add(finalscore);
    }


    public void postFinalScore(HashMap<String, Integer> data){
        Score score = scoreExist(data);

        if (score.getId() == 0) {
            Gson gson = new Gson(); 
            String json = gson.toJson(data);
            // System.out.println(json);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/score"))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(json))
                .build();
            try{
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                // System.out.println(response);
            } catch (IOException e){
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {

            if (data.get("score") > score.getScore()) {
                data.put("id", score.getId());

                updateScore(data);
            }

        }
    }

    public Score scoreExist(HashMap<String, Integer> data){
        Score score = new Score();
        Gson gson = new Gson(); 
        String json = gson.toJson(data);
        // System.out.println(json);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/api/getScore"))
            .header("Content-Type", "application/json")
            .POST(BodyPublishers.ofString(json))
            .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonObject = JsonParser.parseString(response.body().toString()).getAsJsonObject();
            score.setId(jsonObject.get("id").getAsInt());
            score.setScore(jsonObject.get("score").getAsInt());
            return score;
        } catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return score;
    }

    public void updateScore(HashMap<String, Integer> data){
        Gson gson = new Gson(); 
        String json = gson.toJson(data);
        // System.out.println(json);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/api/score"))
            .header("Content-Type", "application/json")
            .PUT(BodyPublishers.ofString(json))
            .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
