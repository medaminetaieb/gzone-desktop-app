/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.JoinRequest;
import com.example.service.JoinRequests;
import com.example.service.Teams;
import com.example.service.Users;
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
public class InviteUserController implements Initializable {

    @FXML
    private TextArea messagetxt;
    @FXML
    private Button sendrequest;
    @FXML
    private Button sendinvitation;
    @FXML
    private TextField tfuser;
    @FXML
    private TextField tfteam;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfuser.setText(new Users().findById(Id.temp).getFullName());
        tfuser.setEditable(false);
        tfteam.setText(new Teams().findById(Id.team).getName());
        tfteam.setEditable(false);
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
    private void sendrequest(ActionEvent event) {
    }

    @FXML
    private void sendinvitation(ActionEvent event) throws IOException{
        if (!messagetxt.getText().isBlank()) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Send Invitation");
            a.setContentText("Invite user to this teams?");
            Optional<ButtonType> buttonType = a.showAndWait();
            if (buttonType.get().equals(ButtonType.OK)) {
                JoinRequest jr = new JoinRequest(null, Id.temp, Id.team, null, messagetxt.getText(), new Date(), null, null, true);
                new JoinRequests().insert(jr);
                sendrequest.getScene().setRoot(FXMLLoader.load(getClass().getResource("ListUsers.fxml")));
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Fill the form");
            a.setContentText("Form has empty fields");
            a.show();
        }
    }

}
