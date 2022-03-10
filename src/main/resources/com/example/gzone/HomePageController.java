/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author chayma
 */
public class HomePageController implements Initializable {

    @FXML
    private AnchorPane homepagepane;
    @FXML
    private ListView<?> topstore;
    @FXML
    private ListView<?> tournamentList;
    @FXML
    private Button btnTournament;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void HomePage(ActionEvent event) {
    }

    @FXML
    private void Team(ActionEvent event) {
    }

    @FXML
    private void Tournament(ActionEvent event) {
    }

    @FXML
    private void Store(ActionEvent event) {
    }

    @FXML
    private void Forum(ActionEvent event) {
    }

    @FXML
    private void ViewTournament(ActionEvent event) {
    }
    
}
