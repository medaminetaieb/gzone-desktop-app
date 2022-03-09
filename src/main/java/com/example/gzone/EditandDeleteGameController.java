/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.Game;
import com.example.service.Games;
import com.example.util.TournamentStat;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author chayma
 */
public class EditandDeleteGameController implements Initializable {

    private Integer gameId;
    private Game g;
    @FXML
    private TextField tfNameUpdate;
    @FXML
    private TextField tfPhotoUrlUpdate;
    @FXML
    private TextArea tfDescriptionUpdate;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnCancel;
    @FXML
    private AnchorPane APEdit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Games games = new Games();
        g = games.findById(Id.game);
        tfNameUpdate.setText(g.getName());
        tfDescriptionUpdate.setText(g.getDescription());
        tfPhotoUrlUpdate.setText(g.getPhotoUrl());
    }

    @FXML
    void Forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
        APEdit.getChildren().setAll(pane);
    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        APEdit.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        APEdit.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("team-view.fxml"));
        APEdit.getChildren().setAll(pane);
    }

    @FXML
    void Tournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        APEdit.getChildren().setAll(pane);
    }

    @FXML
    private void modify(ActionEvent event) {
        Game g = new Games().findById(Id.game);
        g.setName(tfNameUpdate.getText());
        g.setDescription(tfDescriptionUpdate.getText());
        g.setPhotoUrl(tfPhotoUrlUpdate.getText());
        Games games = new Games();
        games.modify(g);
        String title = "Success!";
        String message = "Game modified !";
        NotificationType notification = NotificationType.SUCCESS;
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(notification);
        tray.showAndDismiss(Duration.seconds(3));
    }

    // @FXML
    //private void modify(ActionEvent event) {
    // Game g = new Games().findById(Id.game);
    // g.setName(tfNameUpdate.getText());
    // g.setDescription(tfDescriptionUpdate.getText());
    // g.setPhotoUrl(tfPhotoUrlUpdate.getText());
    // Games games = new Games();
    // games.modify(g);
    // Alert alert = new Alert(Alert.AlertType.INFORMATION);
    //alert.setTitle("success");
    //alert.setHeaderText("Success");
    //alert.setContentText("Game is update successefully");
    //alert.show();
    //find(event);
    //tabOurGame.getTabPane().selectionModelProperty().set(tabEdit.getTabPane().selectionModelProperty().getValue());
    //  }
    @FXML
    private void Cancel(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("GameView.fxml"));
        //APEdit.getChildren().setAll(pane);
        btnCancel.getScene().setRoot(pane);

    }
}
