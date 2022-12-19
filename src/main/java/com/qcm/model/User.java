package com.qcm.model;

import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String name;
    private List<Score> scores;

    public User() {
    }

    public User(int id, String name, List<Score> scores) {
        this.id = id;
        this.name = name;
        this.scores = scores;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Score> getScores() {
        return this.scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public User id(int id) {
        setId(id);
        return this;
    }

    public User name(String name) {
        setName(name);
        return this;
    }

    public User scores(List<Score> scores) {
        setScores(scores);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(scores, user.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, scores);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", scores='" + getScores() + "'" +
            "}";
    }


}
