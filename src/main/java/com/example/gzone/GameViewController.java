/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.Game;
import com.example.service.Games;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
/**
 * FXML GameViewController class
 *
 * @author chayma
 */
public class GameViewController implements Initializable {

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfPhotoUrl;
    @FXML
    private TextArea tfDescription;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField tfNameUpdate;
    @FXML
    private TextField tfPhotoUrlUpdate;
    @FXML
    private TextArea tfDescriptionUpdate;
private Game g ;
    
    @FXML
    private TableView tbView;
    @FXML
    private TableColumn<Game, String> clPhoto;
    @FXML
    private TableColumn<Game, String> clName;
    @FXML
    private TableColumn<?, ?> clDescription;
    @FXML
    private TableColumn<?, ?> clAction;
    @FXML
    private TextField tfSearch;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        g = new Games().findById(9);
        tfNameUpdate.setText(g.getName());
        tfPhotoUrlUpdate.setText(g.getPhotoUrl());
        tfDescriptionUpdate.setText(g.getDescription());
        
          
       
        

    }   
    
    @FXML
    private void HomePage(MouseEvent event) {
    }

    @FXML
    private void Team(MouseEvent event) {
    }

    @FXML
    private void Tournament(MouseEvent event) {
    }

    @FXML
    private void Store(MouseEvent event) {
    }

    @FXML
    private void Forum(MouseEvent event) {
    }

    @FXML
    private void Add(ActionEvent event) {
        String name = tfName.getText();
        String Description = tfDescription.getText();
        String PhotoUrl = tfPhotoUrl.getText();
        Game G = new Game(null , name, PhotoUrl, Description);
        Games GS = new Games();
     GS.insert(G);
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("success");
     alert.setHeaderText("Success");
     alert.setContentText("Game is added successefully");
     alert.show();
      }

    
    @FXML
    private void modify(ActionEvent event) {
        g.setName(tfNameUpdate.getText());
        g.setPhotoUrl(tfPhotoUrlUpdate.getText());
        g.setDescription(tfDescriptionUpdate.getText());
        Games games = new Games();
     games.modify(g);
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("success");
     alert.setHeaderText("Success");
     alert.setContentText("Game is update successefully");
     alert.show();
    }

        
    @FXML
 private void find(ActionEvent event) {
  
 }
 
 
    }

//private void refresh(ActionEvent event) {
        //tbview.getItems().clear();
        //Posts ps = new Posts();
       // List<Post> postlist = ps.findAll("`title` REGEXP '" + tfsearch.getText() + "'");
       // for (Post p : postlist) {
           // tbview.getItems().add(p);

       // }
       // tbview.refresh();

   // }
