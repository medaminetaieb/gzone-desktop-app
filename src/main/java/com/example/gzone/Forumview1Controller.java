/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.Post;
import com.example.service.Posts;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author iheb
 */
public class Forumview1Controller implements Initializable {

    @FXML
    private TextField tfsearch;
    @FXML
    private TableView tbview;
    @FXML
    private TableColumn<Post, String> cltitle;
    
    @FXML
    private Button btnrefresh;
    @FXML
    public AnchorPane dashforumpane;
    @FXML
    public Button btnaddpost;
     @FXML
    private Button btnviewpost;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cltitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tbview.getColumns().add(cltitle);
        refresh(null);

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
    private void find(InputMethodEvent event) {
    }

    @FXML
    private void refresh(ActionEvent event) {
        tbview.getItems().clear();
        Posts ps = new Posts();
        List<Post> postlist = ps.findAll("`title` REGEXP '" + tfsearch.getText() + "'");
        for (Post p : postlist) {
            tbview.getItems().add(p);


        }
        tbview.refresh();

    }
    public void AddPost(ActionEvent event) throws IOException {
    
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview.fxml"));
        dashforumpane.getChildren().setAll(pane);
        
    
    }
     @FXML
    void view(ActionEvent event) throws IOException {
        
        Id.post = ((Post)tbview.getSelectionModel().getSelectedItem()).getId();
        btnviewpost.getScene().setRoot(FXMLLoader.load(getClass().getResource("Forumview3.fxml")));

    }
}
