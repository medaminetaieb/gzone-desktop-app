package com.example.gzone;

import com.example.entity.Tournament;
import com.example.service.Games;
import com.example.service.JoinRequests;
import com.example.service.Matches;
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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class ModifyTournamentController implements Initializable {

    Tournament t;

    @FXML
    private AnchorPane apModifyTournament;

    @FXML
    private Button bCancel;

    @FXML
    private Button bModify;

    @FXML
    private Button bDeleteTournament;

    @FXML
    private CheckBox cbRequestable;

    private DatePicker dpCloseRequestDate;

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
    void Forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
        apModifyTournament.getChildren().setAll(pane);
    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        apModifyTournament.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        apModifyTournament.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("team-view.fxml"));
        apModifyTournament.getChildren().setAll(pane);
    }

    @FXML
    void Tournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        apModifyTournament.getChildren().setAll(pane);
    }

    @FXML
    void cancelModifyTournament(ActionEvent event) throws IOException {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Cancel Modifications");
        a.setContentText("Your modifications will be lost."
                + "Continue ?");
        Optional<ButtonType> buttonType = a.showAndWait();
        if (buttonType.get().equals(ButtonType.OK)) {
            bCancel.getScene().setRoot(FXMLLoader.load(getClass().getResource("ViewTournament.fxml")));
            a.close();
        } else {
            a.close();
        }
    }

    @FXML
    void modifyTournament(ActionEvent event) throws IOException {
        Alert a = new Alert(Alert.AlertType.NONE);
        if (!tfTournamentName.getText().isBlank() && !taTournamentDescription.getText().isBlank()) {
            a.setAlertType(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirm Modifications");
            a.setContentText("Your tournament will be modified.\nConfirm ?");
            Optional<ButtonType> buttonType = a.showAndWait();
            if (buttonType.get().equals(ButtonType.OK)) {
                Tournaments tournaments = new Tournaments();
                t.setName(tfTournamentName.getText());
                t.setDescription(taTournamentDescription.getText());
                t.setRequestable(cbRequestable.isSelected());
                tournaments.modify(t);
                bModify.getScene().setRoot(FXMLLoader.load(getClass().getResource("ViewTournament.fxml")));
                a.close();
                String title = "Success!";
                String message = "Tournament modified !";
                NotificationType notification = NotificationType.SUCCESS;
                TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(notification);
                tray.showAndDismiss(Duration.seconds(3));
            } else {
                a.close();
            }
        } else {
            String title = "Something went wrong !";
            String message = "Verify your information !";
            NotificationType notification = NotificationType.ERROR;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(3));
        }
    }

    @FXML
    void deleteTournament(ActionEvent event) throws IOException {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Delete Tournament");
        a.setContentText("Are you sure you want to delete " + t.getName() + " ?");
        Optional<ButtonType> buttonType = a.showAndWait();
        if (buttonType.get().equals(ButtonType.OK)) {
            new Matches().deleteByTournamentId(Id.tournament);
            new JoinRequests().deleteByTournamentId(Id.tournament);
            new Tournaments().deleteById(Id.tournament);
            bDeleteTournament.getScene().setRoot(FXMLLoader.load(getClass().getResource("ListTournaments.fxml")));
            a.close();
            String title = "Success!";
            String message = "Tournament deleted!";
            NotificationType notification = NotificationType.SUCCESS;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(3));
        } else {
            a.close();
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        t = new Tournaments().findById(Id.tournament);
        tGameName.setText((Id.game == null) ? "No Game" : new Games().findById(t.getGameId()).getName());
        tfTournamentName.setText(t.getName());
        taTournamentDescription.setText((t.getDescription()));
        tNumberOfTeams.setText(t.getRequiredTeams().toString());
        tTeamSize.setText(t.getTeamSize().toString());
        cbRequestable.setSelected(t.isRequestable());
        tCreationDate.setText(t.getCreateDate().toString());
    }
}
