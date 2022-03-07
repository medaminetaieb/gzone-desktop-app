/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.JoinRequest;
import com.example.service.Teams;
import com.example.service.Tournaments;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author mat
 */
public class JoinRequestTournamentController implements Initializable {

    @FXML
    private TextArea messagetxt;
    @FXML
    private Button bSend;
    @FXML
    private TextField tfteam;
    @FXML
    private TextField tftournament;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfteam.setText(new Teams().findById(Id.team).getName());
        tfteam.setEditable(false);
        tftournament.setText(new Tournaments().findById(Id.tournament).getName());
        tftournament.setEditable(false);
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
    private void sendRequest(ActionEvent event) throws IOException {
        if (!messagetxt.getText().isBlank()) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Send Request");
            a.setContentText("Send request to join this tournament");
            Optional<ButtonType> buttonType = a.showAndWait();
            if (buttonType.get().equals(ButtonType.OK)) {
                JoinRequest jr = new JoinRequest(null, null, Id.team, Id.tournament, messagetxt.getText(), new Date(), null, null, false);
                bSend.getScene().setRoot(FXMLLoader.load(getClass().getResource("ViewTournament.fxml")));
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Fill the form");
            a.setContentText("Form has empty fields");
            a.show();
        }
    }

}
