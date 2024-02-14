package ru.sl1degod.demoexamen.utils;

import javafx.collections.ObservableList;
import ru.sl1degod.demoexamen.models.User;

import java.util.List;

public class State {
    public String role;

    public String user_id;

    public String admin_id;

    public ObservableList<String> admins;
    public ObservableList<String> status_app_form;
    public ObservableList<String> status_repair;

    public ObservableList<String> equipNames;
    public ObservableList<String> faultNames;
    public ObservableList<String> users;
    public ObservableList<String> causes;

    public ObservableList<String> getCauses() {
        return causes;
    }

    public void setCauses(ObservableList<String> causes) {
        this.causes = causes;
    }

    public ObservableList<String> getStatus_repair() {
        return status_repair;
    }

    public void setStatus_repair(ObservableList<String> status_repair) {
        this.status_repair = status_repair;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public ObservableList<String> getStatus_app_form() {
        return status_app_form;
    }

    public void setStatus_app_form(ObservableList<String> status_app_form) {
        this.status_app_form = status_app_form;
    }

    public ObservableList<String> getAdmins() {
        return admins;
    }

    public void setAdmins(ObservableList<String> admins) {
        this.admins = admins;
    }

    public ObservableList<String> getUsers() {
        return users;
    }

    public void setUsers(ObservableList<String> users) {
        this.users = users;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public ObservableList<String> getEquipNames() {
        return equipNames;
    }

    public void setEquipNames(ObservableList<String> equipNames) {
        this.equipNames = equipNames;
    }

    public ObservableList<String> getFaultNames() {
        return faultNames;
    }

    public void setFaultNames(ObservableList<String> faultNames) {
        this.faultNames = faultNames;
    }

    private static State instance;
    private State(){}
    public static State getInstance(){
        if(instance == null){
            instance = new State();
        }
        return instance;
    }
}
