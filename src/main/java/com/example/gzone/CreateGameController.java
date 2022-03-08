/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.Game;
import com.example.service.Games;
import com.example.util.PhotoUrlCheck;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author chayma
 */
public class CreateGameController implements Initializable {
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfPhotoUrl;
    @FXML
    private TextArea tfDescription;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnCancel;
    @FXML
    private AnchorPane APCreate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void Forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
        APCreate.getChildren().setAll(pane);
    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        APCreate.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        APCreate.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event)throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("team-view.fxml"));
        APCreate.getChildren().setAll(pane);
    }

    @FXML
    void Tournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        APCreate.getChildren().setAll(pane);
    }

    @FXML
    private void Add(ActionEvent event) {
        Games GS = new Games();
        Alert alert = new Alert(Alert.AlertType.NONE);
        if (
             !tfName.getText().isBlank()
             && PhotoUrlCheck.testImage(tfPhotoUrl.getText())
             && !tfDescription.getText().isBlank()) {
            GS.insert(new Game(null, tfName.getText(), tfPhotoUrl.getText(), tfDescription.getText()));
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setHeaderText("Success");
            alert.setContentText("Game is added successefully");
            alert.show();
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("faild");
            alert.setHeaderText("faild");
            alert.setContentText("check form");
            alert.show();
        }
    }
 

    @FXML
    private void Cancel(ActionEvent event) throws IOException {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("GameView.fxml"));
            //APCreate.getChildren().setAll(pane);
               btnCancel.getScene().setRoot(pane);

        }
    }


