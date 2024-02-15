package ru.sl1degod.demoexamen.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import ru.sl1degod.demoexamen.App;
import ru.sl1degod.demoexamen.database.DataBase;
import ru.sl1degod.demoexamen.utils.State;

public class UpdateAppForm {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label ErrorLabel;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonSave;

    @FXML
    private ComboBox<String> choiceBoxFIO;

    @FXML
    private TextArea commentsTextField;

    @FXML
    private TextArea descTextField;

    @FXML
    private AnchorPane root;

    @FXML
    private ComboBox<String> statusComboBox;

    String idUser = "";

    String form_id = "";

    DataBase dataBase = new DataBase();

    int statusSelectedId = 0;
    int fioSelectedId = 0;

    @FXML
    void initialize() {
        dataBase.getAdminsData();
        dataBase.getStatusApp_form();
        choiceBoxFIO.setItems(State.getInstance().getAdmins());
        statusComboBox.setItems(State.getInstance().getStatus_app_form());

        statusComboBox.setOnAction(e -> {
            statusSelectedId = statusComboBox.getSelectionModel().getSelectedIndex() + 1;
        });
        choiceBoxFIO.setOnAction(e -> {
            fioSelectedId = choiceBoxFIO.getSelectionModel().getSelectedIndex() + 1;
        });

        buttonSave.setOnAction(e -> {
            if (choiceBoxFIO.getValue() == null) {
                System.out.println("error");
            } else {
                dataBase.updateApp_form(String.valueOf(statusSelectedId), descTextField.getText(), commentsTextField.getText(), form_id);
                State.getInstance().setAdmin_id(String.valueOf(fioSelectedId));
                buttonSave.getScene().getWindow().hide();
            }

        });
    }

    public void setData(String desc, String comment, String app_form_id, String id) {
        commentsTextField.setText(comment);
        descTextField.setText(desc);
        idUser = id;
        form_id = app_form_id;
    }
}

