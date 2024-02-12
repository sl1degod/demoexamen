package ru.sl1degod.demoexamen.models;

public class App_form {
    private String id;
    private String equipments;
    private String type_of_fault;
    private String date_create;
    private String description;
    private String status_app;
    private String comments;
    private String priority;

    private String user_id;

    public App_form(String id, String equipments, String type_of_fault, String date_create, String description, String status_app, String comments, String priority, String user_id) {
        this.id = id;
        this.equipments = equipments;
        this.type_of_fault = type_of_fault;
        this.date_create = date_create;
        this.description = description;
        this.status_app = status_app;
        this.comments = comments;
        this.priority = priority;
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEquipments() {
        return equipments;
    }

    public void setEquipments(String equipments) {
        this.equipments = equipments;
    }

    public String getType_of_fault() {
        return type_of_fault;
    }

    public void setType_of_fault(String type_of_fault) {
        this.type_of_fault = type_of_fault;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus_app() {
        return status_app;
    }

    public void setStatus_app(String status_app) {
        this.status_app = status_app;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
