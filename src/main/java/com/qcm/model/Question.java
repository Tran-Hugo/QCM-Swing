package com.qcm.model;

import java.util.Objects;

public class Question {
    private int id;
    private String type;
    private String question;

    public Question() {
    }

    public Question(int id, String type, String question) {
        this.id = id;
        this.type = type;
        this.question = question;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Question id(int id) {
        setId(id);
        return this;
    }

    public Question type(String type) {
        setType(type);
        return this;
    }

    public Question question(String question) {
        setQuestion(question);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Question)) {
            return false;
        }
        Question question = (Question) o;
        return id == question.id && Objects.equals(type, question.type) && Objects.equals(question, question.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, question);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", type='" + getType() + "'" +
            ", question='" + getQuestion() + "'" +
            "}";
    }
    
}
