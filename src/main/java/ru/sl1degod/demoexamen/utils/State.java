package ru.sl1degod.demoexamen.utils;

import javafx.collections.ObservableList;
import ru.sl1degod.demoexamen.models.User;

import java.util.List;

public class State {
    public String role;

    public String user_id;

    public ObservableList<String> equipNames;
    public ObservableList<String> faultNames;
    public ObservableList<String> users;

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
