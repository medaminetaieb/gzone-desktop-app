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


