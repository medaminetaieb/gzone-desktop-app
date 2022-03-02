/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.entity.Comment;
import com.example.entity.Post;
import com.example.service.Comments;
import com.example.service.Posts;
import java.util.Date;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author iheb
 */
public class Forumview3Controller implements Initializable {


    @FXML
    private CheckBox cbresolved;
    @FXML
    private Button btndelete;
    @FXML
    private Text txdate;
    @FXML
    private Button btncomment;
    @FXML
    private TableView  tbview;
    @FXML
    private TableColumn<Comment,String> clcomment;
    @FXML
    private Text txtitle;
    private int id;
    @FXML
    private TextField tfcomment;
    @FXML
    private Button btnrefresh;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    id=1;
    clcomment.setCellValueFactory(new PropertyValueFactory<>("comment_body"));
    tbview.getColumns().add(clcomment);
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
    private boolean deletepost(ActionEvent event) {
        return new Posts().deleteById(id);
    }
    

    @FXML
    private void addcomment(ActionEvent event) {
        Comments cm = new Comments();
        Comment c = new Comment(null, 6, 1, tfcomment.getText(),new Date());
        cm.insert(c);
        
    }

    @FXML
    private void refresh(ActionEvent event) {
        Comments cm = new Comments();
        tbview.getItems().clear();
        List<Comment> commentlist = cm.findAll("`post_id`=6");
        for (Comment c : commentlist) {
        tbview.getItems().add(c);
        
        }
        tbview.refresh();
    }

}
