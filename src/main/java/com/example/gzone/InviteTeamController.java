package com.example.gzone;

import com.example.entity.JoinRequest;
import com.example.service.JoinRequests;
import com.example.service.Teams;
import com.example.service.Tournaments;
import com.example.service.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class InviteTeamController implements Initializable {

    @FXML
    private TextArea messagetxt;

    @FXML
    private Button sendinvitation;

    @FXML
    private TextField tfteam;

    @FXML
    private TextField tftournament;

    @FXML
    void Actionsendinvitation(ActionEvent event) throws IOException {
        if (!messagetxt.getText().isBlank()) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Send Invitation");
            a.setContentText("Invite team to this tournament?");
            Optional<ButtonType> buttonType = a.showAndWait();
            if (buttonType.get().equals(ButtonType.OK)) {
                JoinRequest jr = new JoinRequest(null,null, Id.temp, Id.tournament, messagetxt.getText(), new Date(), null, null, true);
                new JoinRequests().insert(jr);
                sendinvitation.getScene().setRoot(FXMLLoader.load(getClass().getResource("ListUsers.fxml")));
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Fill the form");
            a.setContentText("Form has empty fields");
            a.show();
        }
    }

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
    void Team(MouseEvent event) {

    }

    @FXML
    void Tournament(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tftournament.setText(new Tournaments().findById(Id.tournament).getName());
        tftournament.setEditable(false);
        tfteam.setText(new Teams().findById(Id.temp).getName());
        tfteam.setEditable(false);
    }
}
