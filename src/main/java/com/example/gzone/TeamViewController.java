package com.example.gzone;

import com.example.entity.Game;
import com.example.entity.Membership;
import com.example.entity.Team;
import com.example.service.Games;
import com.example.service.Teams;
import javafx.beans.property.ReadOnlyObjectProperty;
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


import java.io.IOException;
import java.net.URL;
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
    private ListView<Team> listview;
    @FXML
    private Label Label;
    @FXML
    private Label selected;
    @FXML
    private AnchorPane teamviewanchor;


    final Teams teams = new Teams();
    private Integer gameId;
    String namee;





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
    void ActionShowProfile(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("Team-profile.fxml"));
        teamviewanchor.getChildren().setAll(pane);

        teamviewanchor.getChildren().setAll(pane);
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Team-update.fxml"));
        AnchorPane pane = loader.load();
        teamviewanchor.getChildren().setAll(pane);
        TeamUpdateController teamUpdateController = loader.getController();
        Integer id = ((Team) listview.getSelectionModel().getSelectedItem()).getId();

        teamUpdateController.getidd(id);
        teamUpdateController.iddd(id);
        String tname =((Team) listview.getSelectionModel().getSelectedItem()).getName();
        teamUpdateController.getnamee(tname);
        teamUpdateController.teammm(tname);
        }



    @FXML
    void Team(TouchEvent event) {

    }
    @FXML
    public void initialize() {

        }
        int info;

        public void showTeams(){
            final Team[] clabel = {new Team()};
            List <Team >teamlist =teams.findAll("admin_id=2");
            listview.getItems().addAll(teamlist);

            listview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Team>() {
                @Override
                public void changed(ObservableValue<? extends Team> observable, Team oldValue, Team newValue) {
                    clabel[0] = listview.getSelectionModel().getSelectedItem();
                    Label.setText(clabel[0].getName());
                    info = listview.getSelectionModel().getSelectedItem().getId();
                    listview.getSelectionModel().selectedItemProperty();
                    selected.setText("Team "+clabel[0].getName() +" is selected" );
                    //Integer id = ((Team) listview.getSelectionModel().getSelectedItem()).getId();
                    //System.out.println(id);
                }
            });

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



        vbtnupdateteam.disableProperty()
                .bind(listview.getSelectionModel().selectedItemProperty().isNull());
        vbtnshowprofile.disableProperty()
                .bind(listview.getSelectionModel().selectedItemProperty().isNull());

        showTeams();

    }
}









