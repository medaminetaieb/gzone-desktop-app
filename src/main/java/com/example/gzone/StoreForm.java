package com.example.gzone;

import com.example.entity.Game;
import com.example.entity.Store;
import com.example.service.Games;
import com.example.service.Stores;
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
    void validate(ActionEvent event) throws IOException {
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
