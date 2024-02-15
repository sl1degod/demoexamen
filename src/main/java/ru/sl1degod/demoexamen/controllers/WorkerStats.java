package ru.sl1degod.demoexamen.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import ru.sl1degod.demoexamen.database.DataBase;
import ru.sl1degod.demoexamen.models.Stats;
import ru.sl1degod.demoexamen.models.User;
import ru.sl1degod.demoexamen.utils.State;

public class WorkerStats {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label avgTime;

    @FXML
    private Button buttonBack;

    @FXML
    private Label causes;

    @FXML
    private Label countApp_form;

    @FXML
    private AnchorPane rootPane;

    DataBase dataBase = new DataBase();

    @FXML
    void initialize() {
        ObservableList<Stats> stats = dataBase.getStats();

        countApp_form.setText(stats.get(0).getCountApp_form());
        avgTime.setText(stats.get(0).getAvgTime());
        causes.setText(stats.get(0).getCauses());
    }

}
