package ru.sl1degod.demoexamen.controllers;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.sl1degod.demoexamen.App;
import ru.sl1degod.demoexamen.database.DataBase;
import ru.sl1degod.demoexamen.utils.State;

public class AuthController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonSignIn;

    @FXML
    private ImageView imageClose;

    @FXML
    private Label labelError;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private AnchorPane rootPane;

    @FXML
    void onBackPressed() {
        Stage stage = (Stage) imageClose.getScene().getWindow();
        stage.close();
    }

    DataBase dataBase = new DataBase();

    @FXML
    void initialize() {
        buttonSignIn.setOnAction(e -> {
            validation();
        });
    }
    private void validation() {
        String login = textFieldLogin.getText();
        String password = textFieldPassword.getText();
        int codeError = dataBase.signIn(login, password);
        if (login.isEmpty()) {
            labelError.setText("Введите логин");
        } else if (password.isEmpty()) {
            labelError.setText("Введите пароль");
        } else if (codeError == 0) {
            labelError.setText("Такого аккаунта не существует");
        } else if (codeError == 404) {
            labelError.setText("Ошибка");
        }
        else {
            labelError.setText("");
            if (State.getInstance().getRole().equals("Сотрудник")) {
                new App().openNewScene(rootPane, "/ru/sl1degod/demoexamen/main-worker.fxml", "Главный экран");
            } else {
                new App().openNewScene(rootPane, "/ru/sl1degod/demoexamen/create-app-form.fxml", "Главный экран");
            }
            System.out.println(State.getInstance().getRole());

        }
    }

}