package com.qcm.model;

import java.util.List;
import java.util.Objects;

public class Qcm {
    private int id;
    private String theme;
    private List<Score> scores;

    public Qcm() {
    }

    public Qcm(int id, String theme) {
        this.id = id;
        this.theme = theme;
    }

    public Qcm(int id, String theme, List<Score> scores) {
        this.id = id;
        this.theme = theme;
        this.scores = scores;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return this.theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public List<Score> getScores() {
        return this.scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public Qcm id(int id) {
        setId(id);
        return this;
    }

    public Qcm theme(String theme) {
        setTheme(theme);
        return this;
    }

    public Qcm scores(List<Score> scores) {
        setScores(scores);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Qcm)) {
            return false;
        }
        Qcm qcm = (Qcm) o;
        return id == qcm.id && Objects.equals(theme, qcm.theme) && Objects.equals(scores, qcm.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, theme, scores);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", theme='" + getTheme() + "'" +
            ", scores='" + getScores() + "'" +
            "}";
    }

}
