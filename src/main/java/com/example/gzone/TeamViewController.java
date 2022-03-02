package com.example.gzone;

import com.example.entity.Game;
import com.example.entity.Team;
import com.example.service.Games;
import com.example.service.Teams;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.VBox;


import javax.swing.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TeamViewController implements Initializable {

    final Teams teams = new Teams();
    private Integer gameId;

    ObservableList<Team> list = FXCollections.observableArrayList(teams.findAll());
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
    private MenuButton vmbgame;
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
    private Button vbtnaddmembers;
    @FXML
    private Button vtbncreateteam;
    @FXML
    private Tab vtabyourteams;
    @FXML
    private TableView<Team> vtvteamname;
    @FXML
    private TableColumn<Team, String> vtv;
    @FXML
    private Button vbtnshowprofile;
    @FXML
    private Button vbtnupdateteam;
    @FXML
    private Button vbtnselectteam;

    int i=1;
    @FXML
    void ActionAdd(ActionEvent event) {
        /*vbtnaddmembers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vbox.getChildren().add(new TextField());
                i++;
            }
        });*/
    }



    @FXML
    void ActionCreateTeam(ActionEvent event) {

        Teams team = new Teams();
        Team t = new Team();

        t.setId(null);
        t.setAdminId(2);
        t.setName(vtfteamname.getText());
        t.setPhotoURL(vtfphotourl.getText());
        t.setTeamSize(vsteamsize.getValue());
        //t.setGameId(Integer.valueOf(vmbgame.getId()));
        t.setGameId(2);
        t.setDescription(vtadescription.getText());
        t.setCreateDate(new java.util.Date());
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

        teams.insert(t);
        vtfteamname.setText("");
        vtfphotourl.setText("");
        vtadescription.setText("");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Create Team");

        alert.setContentText("Team Added!");

    }

    @FXML
    void ActionSelectTeam(ActionEvent event) {

        Team selectedteam = vtvteamname.getSelectionModel().getSelectedItem();

    }

    @FXML
    void ActionShowProfile(ActionEvent event) {

    }

    @FXML
    void ActionUpdateTeam(ActionEvent event) {

    }

    @FXML
    void Team(TouchEvent event) {

    }
    @FXML
    public void initialize() {

        }
    public void showTeams(){
List<Team> list = teams.findAll();
vtv.setCellValueFactory(new PropertyValueFactory<Team,String>("name"));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Game> gameList = new Games().findAll();
        for (Game g : gameList) {
            vmbgame.getItems().add(new MenuItem(g.getName()));
            vtv.setCellValueFactory(new PropertyValueFactory<Team, String>("name"));
            vtvteamname.setItems(list);

            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8, 1);
            valueFactory.setValue(1);
            vsteamsize.setValueFactory(valueFactory);

            showTeams();

        }
    }
}








