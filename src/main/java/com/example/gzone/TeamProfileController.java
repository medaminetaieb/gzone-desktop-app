package com.example.gzone;

import com.example.entity.Match;
import com.example.entity.Membership;
import com.example.service.Matches;
import com.example.util.TeamStat;
import com.example.entity.Team;
import com.example.service.Memberships;
import com.example.service.Teams;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TeamProfileController implements Initializable {
    private Team t;
    final Memberships members = new Memberships();
    @FXML
    private Text texxt;
    @FXML
    private Text teamname;
    @FXML
    private ImageView photo;

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

        Image image = new Image("https://pbs.twimg.com/profile_images/1157313327867092993/a09TxL_1_400x400.jpg");

        photo.setImage(image);

        List<Membership> teamlistt = members.findAll("`team_id`=" + TeamId.eam + " And `user_id` is not null");
        lismemebers.getItems().addAll(teamlistt);

        Teams teams = new Teams();
        t = teams.findById(TeamId.eam);

        final Matches matches = new Matches();
        List<Match> l = matches.findAll("`team1_id`=" + TeamId.eam + " OR `team2_id`=" + TeamId.eam);
        long m = l.stream().count();

        if (t.isRequestable() == true) {
            chekreq.setSelected(true);
            chekreq.setDisable(true);
        } else {
            chekreq.setSelected(false);
            chekreq.setDisable(true);
        }
        if (t.isInvitable() == true) {
            checkin.setSelected(true);
            checkin.setDisable(true);
        } else {
            checkin.setSelected(false);
            checkin.setDisable(true);
        }

        if(TeamStat.getWinRate(TeamId.eam) == 100.00) {
                winrate.setText(TeamStat.getWinRate(TeamId.eam)+"%");
                texxt.setText("You guys are awesome :)\n For all of the "+m+" matches you played,\n you won all of them !!");

        
        }
        else if ((TeamStat.getWinRate(TeamId.eam)<=99.99)&&(TeamStat.getWinRate(TeamId.eam)>=70.00)){
            winrate.setText(TeamStat.getWinRate(TeamId.eam)+"%");
            texxt.setText("Well done!! Surely we will\n find you at our top 10 team's list");
        }
        else if((TeamStat.getWinRate(TeamId.eam)<=69.99)&&(TeamStat.getWinRate(TeamId.eam)>=40.00)){
            winrate.setText(TeamStat.getWinRate(TeamId.eam)+"%");
            texxt.setText("We know you could do better.\n Better luck next time :)");
        }
        else{
            if(m == 0){
                winrate.setText("");
                texxt.setText("You haven't played any matches yet!");
            }
            else{
                winrate.setText(TeamStat.getWinRate(TeamId.eam)+"%");
                texxt.setText("Hard Luck! You will do better next time :)");

            }

        }

    }
}
