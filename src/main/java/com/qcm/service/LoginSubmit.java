package com.qcm.service;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.awt.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qcm.model.User;
import com.qcm.views.Header;
import com.qcm.views.QcmView;

public class LoginSubmit implements ActionListener {
    private JPanel form;
    private CardLayout cardLayout;
    private Header header;
    private String cardPage;
    private JLabel userLabel;
    private JPanel mainWrapper;

    public LoginSubmit(JPanel form, CardLayout cardLayout, Header header, String cardPage, JLabel userLabel, JPanel mainWrapper){
        this.form = form;
        this.cardLayout = cardLayout;
        this.header = header;
        this.cardPage = cardPage;
        this.userLabel = userLabel;
        this.mainWrapper = mainWrapper;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = "";
        for (Component component : this.form.getComponents()) {
            if (component.getClass().equals(JTextField.class)) {
                JTextField loginId = (JTextField) component;
                id = loginId.getText();
            }
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/api/user/" + id))
            .GET()
            .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonObject = JsonParser.parseString(response.body().toString()).getAsJsonObject();
            User user = new User();
            user.setId(jsonObject.get("id").getAsInt());
            user.setName(jsonObject.get("name").getAsString());
            String userId = Integer.toString(user.getId());
            this.userLabel.setText(userId);
            this.cardLayout.show((JPanel) this.header, this.cardPage);
            this.header.setUserId(userId);

            // JPanel body = new JPanel();
            // body.setLayout(new CardLayout());
            // body.add(qcmView);
            QcmView qcmView = new QcmView(user.getId());
            this.mainWrapper.add(qcmView);
        } catch (IOException exception){
            exception.printStackTrace();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

    }
}
