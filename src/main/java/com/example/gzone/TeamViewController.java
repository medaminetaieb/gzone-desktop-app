package com.example.gzone;

import com.example.entity.Game;
import com.example.entity.Team;
import com.example.service.Games;
import com.example.service.JoinRequests;
import com.example.service.Teams;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.example.util.Badwords.filter;
import static com.example.util.PhotoUrlCheck.testImage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class TeamViewController implements Initializable {

    final Teams teams = new Teams();
    final Games games = new Games();

    int info;
    @FXML
    private Button invitebtn;
    @FXML
    private Button find;
    @FXML
    private Button joinreq;
    @FXML
    private TextField tfsearch;
    @FXML
    private AnchorPane anchoryourteams;
    @FXML
    private VBox vbox;
    @FXML
    private Tab vtabcreatetam;
    @FXML
    private TextField vtfteamname;
    @FXML
    private TextField vtfphotourl;
    @FXML
    private Spinner<Integer> vsteamsize;
    @FXML
    private SplitMenuButton vmbgame;
    @FXML
    private TextArea vtadescription;
    @FXML
    private RadioButton vrbopenforrequests;
    @FXML
    private RadioButton vrbopenforinvitation;
    @FXML
    private TextField vtfaddmembers;
    @FXML
    private ScrollBar vsb;
    @FXML
    private ListView<Team> listofteams;

    @FXML
    private Label nameoftheteam;

    @FXML
    private Button vtbncreateteam;
    @FXML
    private Tab vtabyourteams;
    @FXML
    private Button vbtnshowprofile;
    @FXML
    private Button vbtnupdateteam;
    @FXML
    private Button vbtnselectteam;
    @FXML
    private ListView<Team> listview;
    @FXML
    private Label Label;
    @FXML
    private Label selected;
    @FXML
    private Button delete;
    @FXML
    private AnchorPane teamviewanchor;

    private Integer gameId;

    @FXML
    void Forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
        teamviewanchor.getChildren().setAll(pane);
    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        teamviewanchor.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        teamviewanchor.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("team-view.fxml"));
        teamviewanchor.getChildren().setAll(pane);
    }

    @FXML
    void Tournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        teamviewanchor.getChildren().setAll(pane);
    }

    @FXML
    void ActionCreateTeam(ActionEvent event) {

        Date date = new Date(System.currentTimeMillis());

        Teams team = new Teams();
        Team t = new Team();

        if (vtfteamname.getText().isBlank()) {
            vtfteamname.setPromptText("field cannot be empty");

        }
        if (vtadescription.getText().isBlank()) {
            vtadescription.setPromptText("field cannot be empty");

        }
        if (testImage(vtfphotourl.getText()) == false) {
            vtfphotourl.setPromptText("Check the URL of the image");
        }

        if (vtfteamname.getText().isBlank() || vtadescription.getText().isBlank() || (testImage(vtfphotourl.getText()) == false)) {
            String title = "Failed!";
            String message = "Fill the form correctly!";
            NotificationType notification = NotificationType.WARNING;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(3));;
            return;

        } else {
            //t.setId(null);
            t.setAdminId(Id.user);
            t.setName(vtfteamname.getText());
            t.setTeamSize(vsteamsize.getValue());
            t.setGameId(gameId);
            t.setDescription(filter(vtadescription.getText()));
            t.setPhotoURL(vtfphotourl.getText());
            t.setCreateDate(date);
            if (vrbopenforrequests.isSelected()) {
                t.setRequestable(true);
            } else {
                t.setRequestable(false);

            }
            if (vrbopenforinvitation.isSelected()) {
                t.setInvitable(true);
            } else {
                t.setInvitable(false);

            }

            Alert conf = new Alert(Alert.AlertType.CONFIRMATION);
            conf.setTitle("Team creation confirmation Alert");
            conf.setContentText("Do you really want to create this Team: " + t.getName() + "?");
            Optional<ButtonType> result = conf.showAndWait();
            if (result.get() == ButtonType.OK) {
                teams.insert(t);
                listview.getItems().add(t);
                listview.refresh();
                vtfteamname.setText("");
                vtfphotourl.setText("");
                vtadescription.setText("");
                vrbopenforrequests.setSelected(false);
                vrbopenforinvitation.setSelected(false);

                String title = "Success!";
                String message = "Team created!";
                NotificationType notification = NotificationType.SUCCESS;
                TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(notification);
                tray.showAndDismiss(Duration.seconds(3));;
            }

        }
    }

    @FXML
    void actiondelete(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete confirmation Alert");
        alert.setContentText("Do you really want to delete this Team: " + listview.getSelectionModel().getSelectedItem().getName() + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Team dt = listview.getSelectionModel().getSelectedItem();
            teams.deleteById(dt.getId());

            listview.getItems().remove(dt);
        }

    }

    @FXML
    void ActionShowProfile(ActionEvent event) throws IOException {
        Id.team = ((Team) listview.getSelectionModel().getSelectedItem()).getId();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Team-profile.fxml"));
        AnchorPane pane = loader.load();
        teamviewanchor.getChildren().setAll(pane);

        TeamProfileController teamProfileController = loader.getController();
        String tname = ((Team) listview.getSelectionModel().getSelectedItem()).getName();

        String desc = ((Team) listview.getSelectionModel().getSelectedItem()).getDescription();
        teamProfileController.getnamee(tname);
        teamProfileController.teammm(tname);
        teamProfileController.getdescription(desc);
        teamProfileController.descriptionn(desc);
        // TeamProfileController.getnamee(tname);
        //TeamProfileController.teammm(tname);
    }

    @FXML
    void ActionUpdateTeam(ActionEvent event) throws IOException {
        /* FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Store2.fxml"));
        Parent root =(Parent) fxmlLoader.load();
        Stage stage = (Stage) validatebutton.getScene().getWindow();
        stage.setTitle("fill in");
        stage.setScene(new Scene(root));
        stage.show();
        stage.close();*/
        Id.team = ((Team) listview.getSelectionModel().getSelectedItem()).getId();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Team-update.fxml"));
        AnchorPane pane = loader.load();
        teamviewanchor.getChildren().setAll(pane);
        TeamUpdateController teamUpdateController = loader.getController();

        String tname = (listview.getSelectionModel().getSelectedItem()).getName();
        String desc = ((Team) listview.getSelectionModel().getSelectedItem()).getDescription();
        teamUpdateController.getnamee(tname);
        teamUpdateController.teammm(tname);

    }

    @FXML
    void actioninvite(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListUsers.fxml"));
        AnchorPane pane = loader.load();
        teamviewanchor.getChildren().setAll(pane);
    }

    @FXML
    void Team(TouchEvent event) {

    }

    @FXML
    private void actionfind(ActionEvent event) {
        listview.getItems().clear();
        Teams t = new Teams();
        List<Team> teamlist = t.findAll("name REGEXP '" + tfsearch.getText() + "' And `admin_id`="+Id.user);
        for (Team t1 : teamlist) {
            listview.getItems().add(t1);
        }
        listview.refresh();
    }

    @FXML
    void actionjoinrequest(ActionEvent event) throws IOException {
        Id.team = (listofteams.getSelectionModel().getSelectedItem()).getId();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("joinRequest-view.fxml"));
        AnchorPane pane = loader.load();
        teamviewanchor.getChildren().setAll(pane);

    }

    public void showTeams() {
        final Team[] clabel = {new Team()};
        List<Team> teamlist = teams.findAll("admin_id=" + Id.user);
        listview.getItems().addAll(teamlist);

        listview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Team>() {
            @Override
            public void changed(ObservableValue<? extends Team> observable, Team oldValue, Team newValue) {
                clabel[0] = listview.getSelectionModel().getSelectedItem();
                Label.setText(clabel[0].getName());
                info = listview.getSelectionModel().getSelectedItem().getId();
                listview.getSelectionModel().selectedItemProperty();
                selected.setText("Team " + clabel[0].getName() + " is selected");
                //Integer id = ((Team) listview.getSelectionModel().getSelectedItem()).getId();
                //System.out.println(id);
            }
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Game> gameList = new Games().findAll();
        MenuItem smbgmi = new MenuItem("No Game");
        vmbgame.getItems().add(smbgmi);
        smbgmi.setOnAction(e -> {
            gameId = null;
            vmbgame.setText("No Game");
        });
        for (Game g : gameList) {
            MenuItem mi = new MenuItem(g.getName());
            mi.setOnAction(e -> {
                gameId = g.getId();
                vmbgame.setText(g.getName());
            });
            vmbgame.getItems().add(mi);
        }
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        valueFactory.setValue(1);
        vsteamsize.setValueFactory(valueFactory);

        delete.disableProperty()
                .bind(listview.getSelectionModel().selectedItemProperty().isNull());
        vbtnupdateteam.disableProperty()
                .bind(listview.getSelectionModel().selectedItemProperty().isNull());
        vbtnshowprofile.disableProperty()
                .bind(listview.getSelectionModel().selectedItemProperty().isNull());

        showTeams();

        //JoinRequest
        List<Team> listrequest = teams.findAll("`requestable`= true And `admin_id`!=" + Id.user);
        listofteams.getItems().addAll(listrequest);

        joinreq.disableProperty()
                .bind(listofteams.getSelectionModel().selectedItemProperty().isNull());

    }

}
