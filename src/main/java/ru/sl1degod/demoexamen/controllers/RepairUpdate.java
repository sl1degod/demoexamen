package ru.sl1degod.demoexamen.controllers;

import java.net.URL;
import java.util.ResourceBundle;
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
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private ComboBox<String> choiceBoxFIO;

    @FXML
    private TextArea commentsTextField;

    @FXML
    private TextArea descTextField;

    @FXML
    private TextArea priceTextField;

    @FXML
    private ComboBox<String> rating;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private TextArea timeTextField;

    String idUser = "";

    String form_id = "";

    int causeSelectedId = 0;
    int statusSelectedId = 0;
    int ratingSelectedId = 0;

    DataBase dataBase = new DataBase();

    @FXML
    void initialize() {
        dataBase.getAdminsData();
        dataBase.getStatusApp_form();
        choiceBoxFIO.setItems(State.getInstance().getAdmins());
        statusComboBox.setItems(State.getInstance().getStatus_app_form());
        causeComboBox.setOnAction(e -> {
            causeSelectedId = causeComboBox.getSelectionModel().getSelectedIndex() + 1;
        });
        statusComboBox.setOnAction(e -> {
            statusSelectedId = statusComboBox.getSelectionModel().getSelectedIndex() + 1;
        });
        rating.setOnAction(e -> {
            ratingSelectedId = rating.getSelectionModel().getSelectedIndex() + 1;
        });
        buttonSave.setOnAction(e -> {
            dataBase.createRepair(form_id, idUser, Integer.parseInt(timeTextField.getText()), priceTextField.getText(), String.valueOf(causeSelectedId), assTextField.getText(), String.valueOf(statusSelectedId), String.valueOf(ratingSelectedId));
        });
    }

    public void setData(String desc, String comment, String id, String app_form_id) {
        commentsTextField.setText(comment);
        descTextField.setText(desc);
        idUser = id;
        form_id = app_form_id;
    }
}

