package ru.sl1degod.demoexamen.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import ru.sl1degod.demoexamen.database.DataBase;
import ru.sl1degod.demoexamen.utils.State;

public class RepairUpdate {

    @FXML
    private Label ErrorLabel;

    @FXML
    private TextArea assTextField;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonSave;

    @FXML
    private ComboBox<String> causeComboBox;

    @FXML
    private TextArea priceTextField;

    @FXML
    private ComboBox<String> rating;

    @FXML
    private ComboBox<String> statusRepairComboBox;

    @FXML
    private ComboBox<String> countMaterialComboBox;

    @FXML
    private ComboBox<String> materialComboBox;

    @FXML
    private TextArea timeTextField;

    String form_id = "";

    int causeSelectedId = 0;
    int statusSelectedId = 0;
    int ratingSelectedId = 0;
    int materialSelectedId = 0;
    int countSelectedId = 0;

    DataBase dataBase = new DataBase();

    @FXML
    void initialize() {
        ObservableList<String> rates = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        ObservableList<String> countMaterial = FXCollections.observableArrayList("1", "2", "3", "4", "5");

        dataBase.getCauses();
        dataBase.getStatusRepairData();
        dataBase.getMaterials();
        statusRepairComboBox.setItems(State.getInstance().getStatus_repair());
        causeComboBox.setItems(State.getInstance().getCauses());
        materialComboBox.setItems(State.getInstance().getMaterials());
        rating.setItems(rates);
        countMaterialComboBox.setItems(countMaterial);

        countMaterialComboBox.setOnAction(e -> {
            countSelectedId = countMaterialComboBox.getSelectionModel().getSelectedIndex() + 1;
        });
        materialComboBox.setOnAction(e -> {
            materialSelectedId = materialComboBox.getSelectionModel().getSelectedIndex() + 1;
        });
        causeComboBox.setOnAction(e -> {
            causeSelectedId = causeComboBox.getSelectionModel().getSelectedIndex() + 1;
        });
        statusRepairComboBox.setOnAction(e -> {
            statusSelectedId = statusRepairComboBox.getSelectionModel().getSelectedIndex() + 1;
        });
        rating.setOnAction(e -> {
            ratingSelectedId = rating.getSelectionModel().getSelectedIndex() + 1;
        });
        buttonSave.setOnAction(e -> {
            dataBase.createRepair(form_id, State.getInstance().getAdmin_id(), Integer.parseInt(timeTextField.getText()), priceTextField.getText(), String.valueOf(causeSelectedId), assTextField.getText(), String.valueOf(statusSelectedId), String.valueOf(ratingSelectedId));
            State.getInstance().setSelectedMaterial(String.valueOf(materialSelectedId));
            State.getInstance().setCountMaterial(String.valueOf(countSelectedId));
            buttonSave.getScene().getWindow().hide();
        });
    }

    public void setData(String app_form_id) {
        form_id = app_form_id;
    }
}

