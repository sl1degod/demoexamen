package ru.sl1degod.demoexamen.controllers;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import ru.sl1degod.demoexamen.database.DataBase;
import ru.sl1degod.demoexamen.models.App_form;
import ru.sl1degod.demoexamen.utils.State;

public class UserAppFormView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> columnCause;

    @FXML
    private TableColumn<?, ?> columnComments;

    @FXML
    private TableColumn<?, ?> columnDate;

    @FXML
    private TableColumn<?, ?> columnDesc;

    @FXML
    private TableColumn<?, ?> columnEquip;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnPriority;

    @FXML
    private TableColumn<?, ?> columnStatus;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<App_form> tableView;

    DataBase dataBase = new DataBase();

    @FXML
    void initialize() {
        String loginedUser = State.getInstance().getUser_id();
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnEquip.setCellValueFactory(new PropertyValueFactory<>("equipments"));
        columnCause.setCellValueFactory(new PropertyValueFactory<>("type_of_fault"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date_create"));
        columnDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status_app"));
        columnComments.setCellValueFactory(new PropertyValueFactory<>("comments"));
        columnPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        tableView.setItems(dataBase.getUserApp_form(State.getInstance().getUser_id()));
        dataBase.getRepairUsers(loginedUser);
        ObservableList<String> users = State.getInstance().getRepair_user_id();
        ObservableList<String> app_forms = State.getInstance().getApp_form_ids();
        for (int i = 0; i < users.size(); i++) {
            if (Objects.equals(users.get(i), loginedUser)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Уведомление");
                alert.setHeaderText("Ваш ремонт в заявке №" + Integer.parseInt(app_forms.get(i)) + " был завершен.");
                alert.showAndWait();
            }
        }

    }

}
