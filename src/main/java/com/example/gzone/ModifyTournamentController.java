package com.example.gzone;

import com.example.entity.Tournament;
import com.example.service.Games;
import com.example.service.Tournaments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class ModifyTournamentController implements Initializable {

    Tournament t;

    @FXML
    private AnchorPane apModifyTournament;

    @FXML
    private Button bCancel;

    @FXML
    private Button bModify;

    @FXML
    private CheckBox cbOpenForRequests;

    @FXML
    private DatePicker dpCloseRequestDate;

    @FXML
    private SplitMenuButton smbParticipatingTeams;

    @FXML
    private Text tCreationDate;

    @FXML
    private Text tGameName;

    @FXML
    private Text tNumberOfTeams;

    @FXML
    private Text tTeamSize;

    @FXML
    private TextArea taTournamentDescription;

    @FXML
    private TextField tfTournamentName;

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

    @FXML
    void cancelModifyTournament(ActionEvent event) {

    }

    @FXML
    void modifyTournament(ActionEvent event) throws IOException {
        Alert a = new Alert(Alert.AlertType.NONE);
        if (
                !tfTournamentName.getText().isBlank()
                        && !taTournamentDescription.getText().isBlank()
                        && dpCloseRequestDate.getValue() != null
        ) {
            Tournaments tournaments = new Tournaments();
            t.setName(tfTournamentName.getText());
            t.setDescription(taTournamentDescription.getText());
            t.setCloseRequestsDate(new java.util.Date(java.sql.Date.valueOf(dpCloseRequestDate.getValue()).getTime()));
            t.setApproved(cbOpenForRequests.isSelected());
            tournaments.modify(t);
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setTitle("Success");
            a.setContentText("Tournament modified");
            a.show();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
            apModifyTournament.getChildren().setAll(pane);
        } else {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText("Please Fill the form");
            a.show();
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        t = new Tournaments().findById(Id.tournament);
        tGameName.setText(new Games().findById(t.getGameId()).getName());
        tfTournamentName.setText(t.getName());
        taTournamentDescription.setText((t.getDescription()));
        tNumberOfTeams.setText(t.getRequiredTeams().toString());
        tTeamSize.setText(t.getTeamSize().toString());
        dpCloseRequestDate.setValue(t.getCloseRequestsDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        cbOpenForRequests.setSelected(t.isApproved());
        tCreationDate.setText(t.getCreateDate().toString());
    }
}
