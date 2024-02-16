package ru.sl1degod.demoexamen.utils;

import javafx.collections.ObservableList;
import ru.sl1degod.demoexamen.models.User;

import java.util.List;

public class State {
    public String role;

    public String user_id;

    public String admin_id;
    public String app_form_id;

    public String selectedMaterial;
    public String countMaterial;
    public ObservableList<String> repair_user_id;
    public ObservableList<String> app_form_ids;
    public ObservableList<String> admins;
    public ObservableList<String> status_app_form;
    public ObservableList<String> status_repair;
    public ObservableList<String> materials;

    public ObservableList<String> equipNames;
    public ObservableList<String> faultNames;
    public ObservableList<String> users;
    public ObservableList<String> causes;

    public ObservableList<String> getApp_form_ids() {
        return app_form_ids;
    }

    public void setApp_form_ids(ObservableList<String> app_form_ids) {
        this.app_form_ids = app_form_ids;
    }

    public ObservableList<String> getRepair_user_id() {
        return repair_user_id;
    }

    public void setRepair_user_id(ObservableList<String> repair_user_id) {
        this.repair_user_id = repair_user_id;
    }

    public String getApp_form_id() {
        return app_form_id;
    }

    public void setApp_form_id(String app_form_id) {
        this.app_form_id = app_form_id;
    }

    public String getSelectedMaterial() {
        return selectedMaterial;
    }

    public void setSelectedMaterial(String selectedMaterial) {
        this.selectedMaterial = selectedMaterial;
    }

    public String getCountMaterial() {
        return countMaterial;
    }

    public void setCountMaterial(String countMaterial) {
        this.countMaterial = countMaterial;
    }

    public ObservableList<String> getMaterials() {
        return materials;
    }

    public void setMaterials(ObservableList<String> materials) {
        this.materials = materials;
    }

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
