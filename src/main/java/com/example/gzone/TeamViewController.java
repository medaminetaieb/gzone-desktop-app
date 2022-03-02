package com.example.gzone;

import com.example.entity.Game;
import com.example.entity.Team;
import com.example.service.Games;
import com.example.service.Teams;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TeamViewController implements Initializable {



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
    private Button vbtnshowprofile;
    @FXML
    private Button vbtnupdateteam;
    @FXML
    private Button vbtnselectteam;
    @FXML
    private SplitMenuButton yourteams;
    @FXML
    private ListView<Team> listview;
    @FXML
    private Label Label;


    final Teams teams = new Teams();
    private Integer gameId;
    String namee;

    //int i=1;
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

        Date date = new Date(System.currentTimeMillis());


        Teams team = new Teams();
        Team t = new Team();

        t.setId(null);
        t.setAdminId(2);
        t.setName(vtfteamname.getText());
        t.setPhotoURL(vtfphotourl.getText());
        t.setTeamSize(vsteamsize.getValue());
        //t.setGameId(Integer.valueOf(vmbgame.getId()));
        t.setGameId(gameId);
        t.setDescription(vtadescription.getText());
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

        teams.insert(t);
        vtfteamname.setText("");
        vtfphotourl.setText("");
        vtadescription.setText("");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Create Team");

        alert.setContentText("Team Added!");

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Game> gameList = new Games().findAll();

       vmbgame.getItems();
       vmbgame.setOnAction(e -> {
            gameId = null;

        });
        for (Game g : gameList) {
        MenuItem mi = new MenuItem(g.getName());
        mi.setOnAction(e -> {
            gameId = g.getId();
            vmbgame.setText(g.getName());
        });
        vmbgame.getItems().add(mi);
    }
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8, 1);
        valueFactory.setValue(1);
        vsteamsize.setValueFactory(valueFactory);

        final Team[] clabel = {new Team()};
        List <Team >teamlist =teams.findAll();
        listview.getItems().addAll(teamlist);
        listview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Team>() {
            @Override
            public void changed(ObservableValue<? extends Team> observable, Team oldValue, Team newValue) {
            clabel[0] = listview.getSelectionModel().getSelectedItem();
            Label.setText(clabel[0].getName());
            }
        });
    }
}









