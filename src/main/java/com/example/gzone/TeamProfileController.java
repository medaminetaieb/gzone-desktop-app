package com.example.gzone;

import com.example.entity.Membership;
import com.example.util.TeamStat;
import com.example.entity.Team;
import com.example.service.Memberships;
import com.example.service.Teams;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TeamProfileController implements Initializable {
    private Team t;
    final Memberships members = new Memberships();
    @FXML
    private Text teamname;

    @FXML
    private Text description;

    @FXML
    private CheckBox chekreq;

    @FXML
    private CheckBox checkin;
    @FXML
    private Text winrate;

    @FXML
    private ListView<Membership> lismemebers;
    private String textnamee;
    private String desc;

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
    public void getnamee(String textname) {

        this.textnamee = textname;

    }
    public  void teammm(String name){
        teamname.setText(textnamee);

    }

    public void getdescription(String textname) {

        this.desc = textname;

    }
    public  void descriptionn(String name){
        description.setText(desc);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Membership> teamlistt = members.findAll("`team_id`="+id.eam+" And `user_id` is not null");
        lismemebers.getItems().addAll(teamlistt);

        Teams teams = new Teams();
        t = teams.findById(id.eam);
        if (t.isRequestable() == true) {
            chekreq.fire();
            chekreq.setDisable(true);
        }
else{chekreq.setDisable(false);}
        if (t.isInvitable() == true) {
            checkin.fire();
            checkin.setDisable(true);
        }
        else {
            checkin.setDisable(false);
        }
        winrate.setText(String.valueOf(TeamStat.getWinRate(id.eam)));
    }
}
