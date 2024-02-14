package ru.sl1degod.demoexamen.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import ru.sl1degod.demoexamen.database.DataBase;
import ru.sl1degod.demoexamen.models.Repair;
import ru.sl1degod.demoexamen.utils.State;

public class UserRepairView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> columnApp_id;

    @FXML
    private TableColumn<?, ?> columnAssistance;

    @FXML
    private TableColumn<?, ?> columnCause_id;

    @FXML
    private TableColumn<?, ?> columnPrice;

    @FXML
    private TableColumn<?, ?> columnRating;

    @FXML
    private TableColumn<?, ?> columnStatus_repair_id;

    @FXML
    private TableColumn<?, ?> columnTime_repair;

    @FXML
    private TableColumn<?, ?> columnUser_id;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<Repair> tableView;

    DataBase dataBase = new DataBase();

    @FXML
    void initialize() {
        columnApp_id.setCellValueFactory(new PropertyValueFactory<>("app_form_id"));
        columnUser_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        columnTime_repair.setCellValueFactory(new PropertyValueFactory<>("time"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnCause_id.setCellValueFactory(new PropertyValueFactory<>("cause"));
        columnAssistance.setCellValueFactory(new PropertyValueFactory<>("assistance"));
        columnStatus_repair_id.setCellValueFactory(new PropertyValueFactory<>("status_repair"));
        columnRating.setCellValueFactory(new PropertyValueFactory<>("score"));
        tableView.setItems(dataBase.getRepairs(State.getInstance().getUser_id()));
    }
}
