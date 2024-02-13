package ru.sl1degod.demoexamen.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import ru.sl1degod.demoexamen.App;
import ru.sl1degod.demoexamen.database.DataBase;
import ru.sl1degod.demoexamen.models.Equipments;
import ru.sl1degod.demoexamen.models.TypeOfFault;
import ru.sl1degod.demoexamen.utils.State;

public class CreateAppForm {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button createApp_formButton;

    @FXML
    private TextField descTextField;

    @FXML
    private ComboBox<String> equipComboBox;

    @FXML
    private ComboBox<String> typeofcauseComboBox;

    @FXML
    private AnchorPane root;

    DataBase dataBase = new DataBase();
    int equipSelectedId = 0;
    int faultSelectedId = 0;

    @FXML
    void initialize() {
        dataBase.getEquipments();
        dataBase.getFaults();
        equipComboBox.setItems(State.getInstance().equipNames);
        typeofcauseComboBox.setItems(State.getInstance().faultNames);

        equipComboBox.setOnAction(e -> {
            equipSelectedId = equipComboBox.getSelectionModel().getSelectedIndex() + 1;
            System.out.println(equipSelectedId);
        });

        typeofcauseComboBox.setOnAction(e -> {
            faultSelectedId = typeofcauseComboBox.getSelectionModel().getSelectedIndex() + 1;
            System.out.println(faultSelectedId);
        });

        createApp_formButton.setOnAction(e -> {
            String equip = String.valueOf(equipSelectedId);
            String fault = String.valueOf(faultSelectedId);
            String desc = descTextField.getText();
            String user_id = State.getInstance().getUser_id();
            dataBase.createApp_form(equip, fault, desc, user_id);
            new App().openNewScene(root, "/ru/sl1degod/demoexamen/user-app-form-view.fxml", "Главный экран");
        });
    }

}
