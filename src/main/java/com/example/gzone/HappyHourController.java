/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.Game;
import com.example.entity.HappyHour;
import com.example.service.Games;
import com.example.service.HappyHours;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author chayma
 */
public class HappyHourController implements Initializable {

    @FXML
    private Button btnEdit;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private AnchorPane HPane;
    @FXML
    private Button btnDelete;
    @FXML
    private ListView<HappyHour> HappyHourList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        find(null);
    }

    @FXML
    private void HomePage(MouseEvent event) {
    }

    @FXML
    private void Team(MouseEvent event) {
    }

    @FXML
    private void Tournament(MouseEvent event) {
    }

    @FXML
    private void Store(MouseEvent event) {
    }

    @FXML
    private void Forum(MouseEvent event) {
    }

    @FXML
    private void find(ActionEvent event) {
        HappyHours h = new HappyHours();
        HappyHourList.getItems().clear();
        List<HappyHour> happyHourList = h.findAll(new Date().getTime() + " BETWEEN `start_date` AND `end_date`" );
        for (HappyHour h1 : happyHourList) {
            HappyHourList.getItems().add(h1);
        }
        HappyHourList.refresh();
    }

    @FXML
    private void EditHappyHour(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("UpdateHappyHour.fxml"));
        HPane.getChildren().setAll(pane);
    }

    @FXML
    private void addHappyHour(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("CreateHappyHour.fxml"));
        HPane.getChildren().setAll(pane);
    }

    @FXML
    private void DeleteHappyHour(ActionEvent event) {
        int id = ((HappyHour) HappyHourList.getSelectionModel().getSelectedItem()).getId();
        new HappyHours().deleteById(id);
        find(event);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setHeaderText("Success");
        alert.setContentText("HappyHour is delete successefully");
        alert.show();
        HappyHourList.refresh();
    }

}
