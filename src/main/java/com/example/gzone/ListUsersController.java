/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.Team;
import com.example.entity.User;
import com.example.service.JoinRequests;
import com.example.service.Teams;
import com.example.service.Users;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author mat
 */
public class ListUsersController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private ListView<Team> lvTeams;
    @FXML
    private ListView<User> lvUsers;
    @FXML
    private Button bInvite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        search(null);
    }

    @FXML
    private void search(KeyEvent event) {
        lvUsers.getItems().clear();
        lvTeams.getItems().clear();
        List<Integer> requested = new ArrayList<>();
        if (lvTeams.getSelectionModel().getSelectedItem() != null) {
            new JoinRequests().findAll("`tournament_id` IS NULL AND `team_id`=" + lvTeams.getSelectionModel().getSelectedItem().getId())
                    .stream().mapToInt(jr -> jr.getUserId()).forEach(id -> requested.add(id));
        }
        new Users().findAll("`id`<>"+Id.user+" AND `invitable`=true").stream().filter(u -> (requested.indexOf(u.getId()) == -1) && u.getFullName().indexOf(search.getText()) != -1).forEach(u -> lvUsers.getItems().add(u));
        new Teams().findAll("`admin_id`=" + Id.user).stream().forEach(t -> lvTeams.getItems().add(t));
        lvUsers.refresh();
        lvTeams.refresh();
    }

    @FXML
    private void inviteUser(ActionEvent event) throws IOException {
        Id.team = lvTeams.getSelectionModel().getSelectedItem().getId();
        Id.temp = lvUsers.getSelectionModel().getSelectedItem().getId();
        bInvite.getScene().setRoot(FXMLLoader.load(getClass().getResource("joinRequest-InviteUser.fxml")));

    }

}
