package com.example.gzone;

import com.example.entity.Team;
import com.example.service.Teams;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.util.Badwords.filter;
import static com.example.util.PhotoUrlCheck.testImage;

public class TeamUpdateController implements Initializable {

    public Integer idd;
    public String textname;

    private Team t;
    private Team oldteam;
    @FXML
    private AnchorPane teamupdateanchor;
    @FXML
    private TextField teamname;
    @FXML
    private TextField photourl;
    @FXML
    private Spinner<Integer> scroller;
    @FXML
    private TextArea descritpion;
    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb2;
    @FXML
    private Button updateteam;

    @FXML
    private Label nameee;
    @FXML
    private Button returnn;

    @FXML
    void actionreturn(ActionEvent event) throws IOException {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("Team-view.fxml"));
        AnchorPane pane = loader.load();
        teamupdateanchor.getChildren().setAll(pane);


    }

    @FXML
    void Update(ActionEvent event) {
        Teams teams = new Teams();


        oldteam = teams.findById(TeamId.eam);


        if (teamname.getText().isBlank()) {

            teamname.setPromptText("field cannot be empty");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Alert Dialog");
            alert.setContentText("Please check the name");
            alert.showAndWait();


        }
        if (testImage(photourl.getText()) == false) {

            photourl.setPromptText("Check the URL of the image");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Alert Dialog");
            alert.setContentText("Check the URL of the image");
            alert.showAndWait();

        }
        if (descritpion.getText().isBlank()) {
            descritpion.setPromptText("field cannot be empty");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Alert Dialog");
            alert.setContentText("Check your description");
            alert.showAndWait();

        } else if (!teamname.getText().isBlank() && !testImage(photourl.getText()) == false && !descritpion.getText().isBlank()) {
            t.setName(teamname.getText());
            t.setPhotoURL(photourl.getText());
            t.setTeamSize(scroller.getValue());
            t.setDescription(filter(descritpion.getText()));
            if (rb1.isSelected()) {
                t.setRequestable(true);
            } else {
                t.setRequestable(false);
            }

            if (rb2.isSelected()) {
                t.setInvitable(true);
            } else {
                t.setInvitable(false);
            }

            if ((oldteam.getName()).equals(t.getName()) && (oldteam.getDescription()).equals(t.getDescription()) && (oldteam.getTeamSize() == t.getTeamSize()) && (oldteam.getPhotoURL()).equals(t.getPhotoURL()) && (oldteam.isInvitable() == t.isInvitable()) && (oldteam.isRequestable() == t.isRequestable())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Alert Dialog");
                alert.setContentText("You didn't make any changes");
                alert.showAndWait();
            } else {
                Alert conf = new Alert(Alert.AlertType.CONFIRMATION);
                conf.setTitle("Update confirmation Alert");
                conf.setContentText("Do you really want to modify this Team: " + oldteam.getName() + "?");
                Optional<ButtonType> result = conf.showAndWait();
                if (result.get() == ButtonType.OK) {
                    t.setName(teamname.getText());
                    t.setPhotoURL(photourl.getText());
                    t.setTeamSize(scroller.getValue());
                    t.setDescription(filter(descritpion.getText()));
                    if (rb1.isSelected()) {
                        t.setRequestable(true);
                    } else {
                        t.setRequestable(false);
                    }

                    if (rb2.isSelected()) {
                        t.setInvitable(true);
                    } else {
                        t.setInvitable(false);
                    }

                    teams.modify(t);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Update Team");

                    alert.setContentText("Team Updated!");
                    alert.show();
                }
            }

        }


    }


    public String setnamee() {
        return textname;
    }

    public void getnamee(String textname) {
        this.textname = textname;

    }

    public void teammm(String name) {
        nameee.setText(textname);

    }

    public void initialize() {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Teams teams = new Teams();
        t = teams.findById(TeamId.eam);
        teamname.setText(t.getName());
        photourl.setText(t.getPhotoURL());
        descritpion.setText(t.getDescription());


        if (t.isRequestable() == true) {
            rb1.fire();
        }

        if (t.isInvitable() == true) {
            rb2.fire();
        }


        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        valueFactory.setValue(t.getTeamSize());
        scroller.setValueFactory(valueFactory);

    }
}
