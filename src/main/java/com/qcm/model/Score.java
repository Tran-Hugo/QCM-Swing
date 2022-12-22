package com.qcm.model;

import java.util.Objects;

public class Score {
    private int id;
    private int score;
    private User user;
    private Qcm qcm;

    public Score() {
    }

    public Score(int id, int score, User user, Qcm qcm) {
        this.id = id;
        this.score = score;
        this.user = user;
        this.qcm = qcm;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Qcm getQcm() {
        return this.qcm;
    }

    public void setQcm(Qcm qcm) {
        this.qcm = qcm;
    }

    public Score id(int id) {
        setId(id);
        return this;
    }

    public Score score(int score) {
        setScore(score);
        return this;
    }

    public Score user(User user) {
        setUser(user);
        return this;
    }

    public Score qcm(Qcm qcm) {
        setQcm(qcm);
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, user, qcm);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", score='" + getScore() + "'" +
            ", user='" + getUser() + "'" +
            ", qcm='" + getQcm() + "'" +
            "}";
    }


}
