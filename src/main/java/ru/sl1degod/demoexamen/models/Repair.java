package ru.sl1degod.demoexamen.models;

public class Repair {
    String app_form_id;
    String user_id;
    String time;
    String price;
    String cause;
    String assistance;
    String status_repair;
    String score;

    public Repair(String app_form_id, String user_id, String time, String price, String cause, String assistance, String status_repair, String score) {
        this.app_form_id = app_form_id;
        this.user_id = user_id;
        this.time = time;
        this.price = price;
        this.cause = cause;
        this.assistance = assistance;
        this.status_repair = status_repair;
        this.score = score;
    }

    public String getApp_form_id() {
        return app_form_id;
    }

    public void setApp_form_id(String app_form_id) {
        this.app_form_id = app_form_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getAssistance() {
        return assistance;
    }

    public void setAssistance(String assistance) {
        this.assistance = assistance;
    }

    public String getStatus_repair() {
        return status_repair;
    }

    public void setStatus_repair(String status_repair) {
        this.status_repair = status_repair;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
