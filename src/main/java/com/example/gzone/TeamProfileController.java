package com.example.gzone;

import com.example.entity.Match;

import com.example.entity.JoinRequest;
import com.example.entity.User;
import com.example.service.Matches;
import com.example.service.Users;
import com.example.util.TeamStat;
import com.example.entity.Team;

import com.example.service.JoinRequests;
import com.example.service.Teams;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TeamProfileController implements Initializable {
    private Team t;
    private User u;
    final JoinRequests members = new JoinRequests();
     Users users = new Users();
    @FXML
    private ListView<Team> topthreelist;
    @FXML
    private Button returnview;
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
    private AnchorPane allanchor;
    @FXML
    private Button deletemember;
    @FXML
    private AnchorPane smanchor;


    @FXML
    private ListView<User> lismemebers;
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
    @FXML
    void actionreturn(ActionEvent event) throws IOException {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("Team-view.fxml"));
        AnchorPane pane = loader.load();
        allanchor.getChildren().setAll(pane);

    }
    @FXML
    void actiondeletemember(ActionEvent event) {
        //u = users.findById(lismemebers.getSelectionModel().getSelectedItem().getUserId());
        User u = lismemebers.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete confirmation Alert");
        alert.setContentText("Do you really want to delete this Member: "+u.getFullName()+"?");
        Optional<ButtonType> result =alert.showAndWait();
        if(result.get() == ButtonType.OK){

            List<JoinRequest> list = members.findAll("`user_id`=" + u.getId() + " AND `team_id`=" + Id.team + " And `accepted`=true");

              for (JoinRequest m :list){

                  members.deleteById(m.getId());

                  lismemebers.getItems().remove(u);
              }


        }


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Teams teams = new Teams();
        t = teams.findById(
                Id.team);
        Image image = new Image(t.getPhotoURL());

        photo.setImage(image);

        List<User> userlist = new ArrayList<>();
        List<JoinRequest> teamlistt = members.findAll("`team_id`=" + Id.team + " And `user_id` is not null And `accepted`=true");
        for(JoinRequest m : teamlistt){
            Integer userId = m.getUserId();
            userlist.add(users.findById(userId));

        }

        lismemebers.getItems().addAll(userlist);



        final Matches matches = new Matches();
        List<Match> l = matches.findAll("`team1_id`=" + Id.team + " OR `team2_id`=" + Id.team);
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

        if(TeamStat.getWinRate(Id.team) == 100.00) {
                winrate.setText(TeamStat.getWinRate(Id.team)+"%");
                texxt.setText("You guys are awesome :)\n For all of the "+m+" matches you played,\n you won all of them !!");

        
        }
        else if ((TeamStat.getWinRate(Id.team)<=99.99)&&(TeamStat.getWinRate(Id.team)>=70.00)){
            winrate.setText(TeamStat.getWinRate(Id.team)+"%");
            texxt.setText("Well done!! Surely we will\n find you at our top 10 team's list");
        }
        else if((TeamStat.getWinRate(Id.team)<=69.99)&&(TeamStat.getWinRate(Id.team)>=40.00)){
            winrate.setText(TeamStat.getWinRate(Id.team)+"%");
            texxt.setText("We know you could do better.\n Better luck next time :)");
        }
        else{
            if(m == 0){
                winrate.setText("");
                texxt.setText("You haven't played any matches yet!");
            }
            else{
                winrate.setText(TeamStat.getWinRate(Id.team)+"%");
                texxt.setText("Hard Luck! You will do better next time :)");

            }

        }
        deletemember.disableProperty()
                .bind(lismemebers.getSelectionModel().selectedItemProperty().isNull());

        //Show top 3 teams
      /* for(Team top:TeamStat.topTenTeams()){

        }*/
topthreelist.getItems().addAll(TeamStat.topThreeTeams());

    }
}
