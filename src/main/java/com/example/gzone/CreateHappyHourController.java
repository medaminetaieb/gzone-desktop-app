/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.Badge;
import com.example.entity.Game;
import com.example.entity.HappyHour;
import com.example.service.Badges;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author chayma
 */
public class CreateHappyHourController implements Initializable {

    private Integer gameId;
    private Integer badgeId;

    @FXML
    private DatePicker dpStartDate;
    @FXML
    private DatePicker dpEndDate;
    @FXML
    private SplitMenuButton smbGame;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btCancel;
    @FXML
    private AnchorPane CreatePane;
    @FXML
    private SplitMenuButton smbBadge;
    @FXML
    private TextField TfBadge;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Game> gamelist = new Games().findAll();
        MenuItem smbgmi = new MenuItem("No Game");
        smbGame.getItems().add(smbgmi);
        smbgmi.setOnAction(e -> {
            gameId = null;
            smbGame.setText("No Game");
        });
        for (Game g : gamelist) {
            MenuItem mi = new MenuItem(g.getName());
            mi.setOnAction(e -> {
                gameId = g.getId();
                smbGame.setText(g.getName());
            });
            smbGame.getItems().add(mi);
        }

        MenuItem smbB = new MenuItem("New Badge");
        smbB.setOnAction(e -> {
            Id.badge = null;
            smbBadge.setText("New Badge");
            TfBadge.setEditable(true);

        });
        smbBadge.getItems().add(smbB);

        for (Badge b : new Badges().findAll()) {
            MenuItem mi1 = new MenuItem(b.getTitle());
            mi1.setOnAction(e -> {
                Id.badge = b.getId();
                smbBadge.setText(b.getTitle());
                TfBadge.setEditable(false);
                TfBadge.setText(b.getTitle());
            });

            smbBadge.getItems().add(mi1);
        }

    }

    @FXML
    void Forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
        CreatePane.getChildren().setAll(pane);
    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        CreatePane.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        CreatePane.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("team-view.fxml"));
        CreatePane.getChildren().setAll(pane);
    }

    @FXML
    void Tournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        CreatePane.getChildren().setAll(pane);
    }

    @FXML
    private void CreateHappyHour(ActionEvent event) {

        Alert a = new Alert(Alert.AlertType.NONE);

        if (dpStartDate.getValue() != null && dpEndDate.getValue() != null) {
            Date crd = new Date(java.sql.Date.valueOf(dpStartDate.getValue()).getTime());
            Date crd1 = new Date(java.sql.Date.valueOf(dpEndDate.getValue()).getTime());
            if (crd.before(crd1)) {
                if (badgeId == null) {
                    new Badges().insert(new Badge(null, gameId, TfBadge.getText()));
                    new HappyHours().insert(new HappyHour(null, new Badges().findAll("`title` REGEXP '" + TfBadge.getText() + "'").get(0).getId(), crd, crd1));
                } else {
                    new HappyHours().insert(new HappyHour(null, badgeId, crd, crd1));
                }
                String title = "Success!";
                String message = "Happy hour added !";
                NotificationType notification = NotificationType.SUCCESS;
                TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(notification);
                tray.showAndDismiss(Duration.seconds(3));
            } else {
                String title = "Failed!";
                String message = "Start date must be before the end date !";
                NotificationType notification = NotificationType.WARNING;
                TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(notification);
                tray.showAndDismiss(Duration.seconds(3));
            }
        } else {
            String title = "Failed!";
            String message = "Fill the form correctly !";
            NotificationType notification = NotificationType.ERROR;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(3));
        }
    }

    @FXML
    private void CancelHappyHour(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HappyHour.fxml"));
        CreatePane.getChildren().setAll(pane);
    }

}
