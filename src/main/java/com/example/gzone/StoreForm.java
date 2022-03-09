package com.example.gzone;

import com.example.entity.Game;
import com.example.entity.Store;
import static com.example.gzone.StoreNotification.notifMail;
import com.example.service.Games;
import com.example.service.Stores;
import com.example.service.UserGamePreferences;
import com.example.service.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class StoreForm {

    private Integer gameId;
    @FXML
    public Button validatebutton, cancelbutton;

    @FXML
    private SplitMenuButton smbGame;

    @FXML
    public TextField storename;
    @FXML
    public AnchorPane rootPane;

    @FXML
    void Forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("team-view.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    void Tournament(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    @FXML
    void validate(ActionEvent event) throws IOException, Exception {
        if ((storename.getText().isBlank())) {
            String title = "Failed!";
            String message = "Fill the form correctly!";
            NotificationType notification = NotificationType.WARNING;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(3));
        } else {
            new Stores().insert(new Store(null, Id.user, gameId, storename.getText()));
            String title = "Success!";
            String message = "Store Added!";
            NotificationType notification = NotificationType.SUCCESS;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(3));
            int numberrecepients = new UserGamePreferences().findAll("`game_id`=" + gameId).size();
            for (int i = 0; i < numberrecepients; i++) {
                Integer recipientId = new UserGamePreferences().findAll("`game_id`=" + gameId).get(i).getUserId();
                notifMail(new Users().findAll("`id`=" + recipientId).get(0).getEmail());
            }

            Id.store = new Stores().findByName(storename.getText()).getId();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("StoreProfile.fxml"));
            rootPane.getChildren().setAll(pane);
        }

    }

    @FXML
    void CancelCreateStore(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    public void initialize() {
        List<Game> gameList = new Games().findAll();
        MenuItem smbgmi = new MenuItem("No Game");
        smbGame.getItems().add(smbgmi);
        smbgmi.setOnAction(e -> {
            gameId = null;
            smbGame.setText("No Game");
        });
        for (Game g : gameList) {
            MenuItem mi = new MenuItem(g.getName());
            mi.setOnAction(e -> {
                gameId = g.getId();
                smbGame.setText(g.getName());
            });
            smbGame.getItems().add(mi);
        }
    }
    
    
}
