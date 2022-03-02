package com.example.gzone;

import com.example.entity.Team;
import com.example.service.Teams;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TeamUpdateController implements Initializable {

    private Team t;

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
    void Update(ActionEvent event){
        Teams teams = new Teams();

        t.setAdminId(2);
        t.setName(teamname.getText());
        t.setPhotoURL(photourl.getText());

        t.setTeamSize(scroller.getValue());
        t.setDescription(descritpion.getText());

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
    }
    public void setatt(){



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8, 1);
        valueFactory.setValue(1);
        scroller.setValueFactory(valueFactory);

    }
}
