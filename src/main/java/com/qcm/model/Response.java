package com.qcm.model;

import java.util.Objects;

public class Response {
    private int id;
    private String content;
    private Boolean is_correct;

    public Response() {
    }

    public Response(int id, String content, Boolean is_correct) {
        this.id = id;
        this.content = content;
        this.is_correct = is_correct;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean isIs_correct() {
        return this.is_correct;
    }

    public Boolean getIs_correct() {
        return this.is_correct;
    }

    public void setIs_correct(Boolean is_correct) {
        this.is_correct = is_correct;
    }

    public Response id(int id) {
        setId(id);
        return this;
    }

    public Response content(String content) {
        setContent(content);
        return this;
    }

    public Response is_correct(Boolean is_correct) {
        setIs_correct(is_correct);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Response)) {
            return false;
        }
        Response response = (Response) o;
        return id == response.id && Objects.equals(content, response.content) && Objects.equals(is_correct, response.is_correct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, is_correct);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", content='" + getContent() + "'" +
            ", is_correct='" + isIs_correct() + "'" +
            "}";
    }

}
