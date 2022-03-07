package com.example.gzone;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class JoinRequestController {

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

    @FXML
    private TextField tftournament;

    @FXML
    void Forum(MouseEvent event) {

    }

    @FXML
    void HomePage(MouseEvent event) {

    }

    @FXML
    void Store(MouseEvent event) {

    }

    @FXML
    void Team(MouseEvent event) throws IOException {
        sendinvitation.getScene().setRoot(FXMLLoader.load(getClass().getResource("team-view.fxml")));
    }

    @FXML
    void Tournament(MouseEvent event) throws IOException {
        sendinvitation.getScene().setRoot(FXMLLoader.load(getClass().getResource("ListTournaments.fxml")));
    }

    @FXML
    void sendinvitation(ActionEvent event) {

    }

    @FXML
    void sendrequest(ActionEvent event) {

    }

}
