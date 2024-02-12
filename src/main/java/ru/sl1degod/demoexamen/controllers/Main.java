package ru.sl1degod.demoexamen.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import ru.sl1degod.demoexamen.database.DataBase;
import ru.sl1degod.demoexamen.models.App_form;

public class Main {

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
    private AnchorPane rootPane;

    @FXML
    private TableView<App_form> tableView;

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
        tableView.setItems(dataBase.getApp_form());

//        if (Variables.ROLE_USER.equals("admin")) {
//            tableView.setOnMouseClicked(e -> {
//                if (e.getClickCount() == 2) {
//                    User user = tableView.getSelectionModel().getSelectedItem();
//
//                    try {
//                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/exam/update.fxml"));
//                        Parent parent = loader.load();
//                        Update update = loader.getController();
//                        update.setData(user.getLogin(), user.getPassword(), user.getRole(), user.getStatus(), user.getId());
//                        Stage stage = new Stage();
//                        stage.initModality(Modality.APPLICATION_MODAL);
//                        stage.setScene(new Scene(parent));
//                        stage.setTitle("Редактирование пользователя");
//                        stage.showAndWait();
//                    } catch (IOException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                }
//            });
//        }
    }

}

