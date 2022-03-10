/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.Game;
import com.example.entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

/**
 * FXML Controller class
 *
 * @author chayma
 */
public class StatisticsGameController implements Initializable {
    ObservableList<Game> datagame = FXCollections.observableArrayList();
    ObservableList<User> datauser = FXCollections.observableArrayList();


    @FXML
    private BarChart<String, Integer> BarChart;
    @FXML
    private NumberAxis Y;
    @FXML
    private CategoryAxis X;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
