package ru.sl1degod.demoexamen.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import ru.sl1degod.demoexamen.App;
import ru.sl1degod.demoexamen.database.DataBase;
import ru.sl1degod.demoexamen.models.User;
import ru.sl1degod.demoexamen.utils.State;

public class MenuClient {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button createAppFormButton;

    @FXML
    private Label labelFio;

    @FXML
    private AnchorPane root;

    @FXML
    private Button seeMyAppFormButton;

    @FXML
    private Button seeMyRepair;

    DataBase dataBase = new DataBase();

    @FXML
    void initialize() {
        ObservableList<User> users = dataBase.getUser(State.getInstance().getUser_id());
        labelFio.setText("Здравствуйте, " + users.get(0).getFirstName() + " " + users.get(0).getSecondName() + " " + users.get(0).getLastName());
        createAppFormButton.setOnAction(e -> {
            new App().openNewScene(root, "/ru/sl1degod/demoexamen/create-app-form.fxml", "Создание заявки");
        });
        seeMyAppFormButton.setOnAction(e -> {
            new App().openNewScene(root, "/ru/sl1degod/demoexamen/user-app-form-view.fxml", "Просмотр заявок");
        });
        seeMyRepair.setOnAction(e -> {
            new App().openNewScene(root, "/ru/sl1degod/demoexamen/user-repair-view.fxml", "Просмотр заявок на ремонт");
        });
    }

}
