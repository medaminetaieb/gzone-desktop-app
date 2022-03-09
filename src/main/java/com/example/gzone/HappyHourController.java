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
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
    void Forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
        HPane.getChildren().setAll(pane);
    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        HPane.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        HPane.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("team-view.fxml"));
        HPane.getChildren().setAll(pane);
    }

    @FXML
    void Tournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        HPane.getChildren().setAll(pane);
    }

    @FXML
    private void find(ActionEvent event) {
        HappyHours h = new HappyHours();
        HappyHourList.getItems().clear();
        List<HappyHour> happyHourList = h.findAll(new Date().getTime() + " BETWEEN `start_date` AND `end_date`");
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
        String title = "Success!";
        String message = "Happy hour deleted !";
        NotificationType notification = NotificationType.SUCCESS;
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(notification);
        tray.showAndDismiss(Duration.seconds(3));
        HappyHourList.refresh();
    }

}
