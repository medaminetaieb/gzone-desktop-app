/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author chayma
 */
public class UpdateHappyHourController implements Initializable {

    @FXML
    private DatePicker dpStartDate1;
    @FXML
    private DatePicker EndDate1;
    @FXML
    private TextField tfBadge;
    @FXML
    private SplitMenuButton smbGame1;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnCancel;
    @FXML
    private AnchorPane UpdatePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void Forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
        UpdatePane.getChildren().setAll(pane);
    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        UpdatePane.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        UpdatePane.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event)throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("team-view.fxml"));
        UpdatePane.getChildren().setAll(pane);
    }

    @FXML
    void Tournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        UpdatePane.getChildren().setAll(pane);
    }

    @FXML
    private void UpdateHappyHour(ActionEvent event) {
       
    }

    @FXML
    private void Cancel(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("HappyHour.fxml"));
        UpdatePane.getChildren().setAll(pane);
    }
    
}
