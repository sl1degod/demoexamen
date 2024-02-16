package ru.sl1degod.demoexamen.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.sl1degod.demoexamen.App;
import ru.sl1degod.demoexamen.database.DataBase;
import ru.sl1degod.demoexamen.models.App_form;
import ru.sl1degod.demoexamen.models.User;
import ru.sl1degod.demoexamen.utils.State;

public class MainWorker {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> columnCause;

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
    private TableColumn<?, ?> columnComments;

    @FXML
    private TableColumn<?, ?> columnStatus;

    @FXML
    private TableColumn<?, ?> columnUser_id;

    @FXML
    private Label startRepairLabel;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label updateLabel;

    @FXML
    private Label statsLabel;

    @FXML
    private TableView<App_form> tableView;

    @FXML
    private Label searchLabel;

    @FXML
    private TextField searchTextField;


    DataBase dataBase = new DataBase();

    @FXML
    void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnEquip.setCellValueFactory(new PropertyValueFactory<>("equipments"));
        columnCause.setCellValueFactory(new PropertyValueFactory<>("type_of_fault"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date_create"));
        columnDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status_app"));
        columnComments.setCellValueFactory(new PropertyValueFactory<>("comments"));
        columnPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        columnUser_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        tableView.setItems(dataBase.getApp_form());
        FilteredList<App_form> filteredData = new FilteredList<>(dataBase.getApp_form(), p -> true);
        tableView.setItems(filteredData);

        setData();
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(appForm -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (appForm.getEquipments().toLowerCase().contains(lowerCaseFilter) ||
                        appForm.getType_of_fault().toLowerCase().contains(lowerCaseFilter) ||
                        appForm.getDate_create().toLowerCase().contains(lowerCaseFilter) ||
                        appForm.getDescription().toLowerCase().contains(lowerCaseFilter) ||
                        appForm.getStatus_app().toLowerCase().contains(lowerCaseFilter) ||
                        appForm.getComments().toLowerCase().contains(lowerCaseFilter) ||
                        appForm.getPriority().toLowerCase().contains(lowerCaseFilter) ||
                        appForm.getId().toLowerCase().contains(lowerCaseFilter) ||
                        appForm.getUser_id().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
        });

        updateLabel.setOnMouseClicked(e -> {
            tableView.setItems(dataBase.getApp_form());
        });
        statsLabel.setOnMouseClicked(e -> {
            new App().openNewScene(rootPane, "/ru/sl1degod/demoexamen/worker-stats.fxml", "Статистика");
        });
    }
    private void setData() {


        if (State.getInstance().getRole().equals("Сотрудник")) {
            tableView.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2) {
                    App_form appForm = tableView.getSelectionModel().getSelectedItem();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/sl1degod/demoexamen/app-form-update.fxml"));
                        Parent parent = loader.load();
                        UpdateAppForm update = loader.getController();
                        update.setData(appForm.getDescription(), appForm.getComments(), appForm.getId(), appForm.getUser_id());
                        System.out.println(appForm.getId());
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(new Scene(parent));
                        stage.setTitle("Редактирование заявки");
                        stage.showAndWait();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }

        startRepairLabel.setOnMouseClicked(e -> {
            if (State.getInstance().getRole().equals("Сотрудник")) {
//                    if (e.getClickCount() == 2) {
                        App_form appForm = tableView.getSelectionModel().getSelectedItem();
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/sl1degod/demoexamen/repair-update.fxml"));
                            Parent parent = loader.load();
                            RepairUpdate repairUpdate = loader.getController();
                            repairUpdate.setData(appForm.getId(), appForm.getUser_id());
                            System.out.println(appForm.getId());
                            Stage stage = new Stage();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.setScene(new Scene(parent));
                            stage.setTitle("Редактирование заявки");
                            stage.showAndWait();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
        };
    }


