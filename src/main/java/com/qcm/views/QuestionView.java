package com.qcm.views;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qcm.model.Question;
import com.qcm.model.Response;
import com.qcm.service.GetNextQuestion;
import com.qcm.service.GetResult;

public class QuestionView extends JPanel{

   public QuestionView(ArrayList<Question> allQuestions, Question question, CardLayout cardLayout, QcmView qcmView){
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      JPanel questionWrapper = new JPanel();
      // questionWrapper.setLayout(new BoxLayout(questionWrapper, BoxLayout.Y_AXIS));
      questionWrapper.setLayout(new BorderLayout());
      JLabel questionLabel = new JLabel(question.getQuestion(), SwingConstants.CENTER);

      JPanel responsesWrapper = new JPanel();
      responsesWrapper.setLayout(new BoxLayout(responsesWrapper, BoxLayout.Y_AXIS));

      ArrayList<Response> allResponses = getResponses(question);

      for (Response response : allResponses) {
         JPanel responsePanel = new JPanel();
         responsePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
         responsePanel.setMaximumSize(new Dimension(responsePanel.getMaximumSize().width, 40));
         responsePanel.setAlignmentX(0.0f);
         // responsePanel.setBackground(new Color(255, 0 , 0));
         JCheckBox responseCheck = new JCheckBox();
         responseCheck.setName(Integer.toString(response.getId()));
         JLabel responseText = new JLabel(response.getContent());

         responsePanel.add(responseCheck);
         responsePanel.add(responseText);
         responsesWrapper.add(responsePanel);
      }
      questionWrapper.add(questionLabel, BorderLayout.NORTH);
      questionWrapper.add(responsesWrapper, BorderLayout.CENTER);
      
      add(questionWrapper);
      int index = allQuestions.indexOf(question);
      if (index == allQuestions.size() - 1) {
         JButton result = new JButton("Result");
         result.addActionListener(new GetResult(qcmView, cardLayout));
         questionWrapper.add(result, BorderLayout.SOUTH);
         // add(result);
      }else{
         Question nextQuestion = allQuestions.get(index + 1);
         JButton nextQuestionButton = new JButton("Next");
         nextQuestionButton.addActionListener(new GetNextQuestion(qcmView, nextQuestion, cardLayout, responsesWrapper));
         questionWrapper.add(nextQuestionButton, BorderLayout.SOUTH);
         // add(nextQuestionButton);

      }
   }

   public ArrayList<Response> getResponses(Question question){
      ArrayList<Response> allResponses = new ArrayList<Response>();
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
         .uri(URI.create("http://localhost:8080/api/question/" + question.getId() + "/reponses"))
         .GET()
         .build();
      try{
         HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
         JsonArray jsonArray = JsonParser.parseString(response.body().toString()).getAsJsonArray();
         
         for(int i=0; i< jsonArray.size(); i++){
               JsonObject jsonObject= jsonArray.get(i).getAsJsonObject();
               Response responseObj = new Response(
                  jsonObject.get("id").getAsInt(),
                  jsonObject.get("content").getAsString(),
                  jsonObject.get("is_correct").getAsBoolean()
               );
               allResponses.add(responseObj);
         }

         return allResponses;
      } catch (IOException exception){
         exception.printStackTrace();
      } catch (InterruptedException exception) {
         exception.printStackTrace();
      }

      return allResponses;
   }

}
