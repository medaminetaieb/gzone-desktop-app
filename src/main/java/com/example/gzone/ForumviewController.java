/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.Post;
import com.example.service.Posts;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author iheb
 */
public class ForumviewController implements Initializable {


    @FXML
    private TextArea tfcontent;
    @FXML
    public Button btninsert;
    
    @FXML
    public Button btncancel;
    @FXML
    private TextField tftitle;
    @FXML
    public AnchorPane dashforumback;
    
    /*ValidationSupport validationsupport = new ValidationSupport();*/
    
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
        dashforumback.getChildren().setAll(pane);
    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        dashforumback.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        dashforumback.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event)throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("team-view.fxml"));
        dashforumback.getChildren().setAll(pane);
    }

    @FXML
    void Tournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        dashforumback.getChildren().setAll(pane);
    }
    @FXML
    private void addpost(ActionEvent event) throws IOException {
        if(!tftitle.getText().isBlank() || (!tfcontent.getText().isBlank())){
        Posts ps = new Posts();
        Post p = new Post(null, 3, false, tftitle.getText(), tfcontent.getText(), "", new Date());
        ps.insert(p);
        }   else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("One of your Text fields is empty");
            a.show();
        }
    }
    
    public void cancel (ActionEvent event) throws IOException{
        
    AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
    dashforumback.getChildren().setAll(pane);
    }
        


}
