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
import java.io.IOException;
import java.util.Date;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private ListView<Comment> tbview;
    
    @FXML
    private Text txtitle;
    @FXML
    private TextField tfcomment;
    @FXML
    private Button btnrefresh;
    @FXML
    private Button btnleave;
    @FXML
    private AnchorPane postprofile;
    @FXML
    private TextArea tfcontent;
    @FXML
    private Button btndeletecm;
    Post p = new Posts().findById(Id.post);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        p = new Posts().findById(Id.post);
        txtitle.setText(p.getTitle());
        txdate.setText(p.getPostDate().toString());
        tfcontent.setText(p.getContent());
        cbresolved.setSelected(p.isResolved());
        if (p.getPosterId().equals(Id.user)) {

            cbresolved.setDisable(true);
        }

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
    private boolean deletepost(ActionEvent event) throws IOException {
        leave(event);
        return new Posts().deleteById(Id.post);

    }

    @FXML
    private void addcomment(ActionEvent event) {
        if (!p.isResolved() || !tfcomment.getText().isBlank()) {
            Comments cm = new Comments();
            Comment c = new Comment(null, Id.post, Id.user, tfcomment.getText(), new Date());
            cm.insert(c);
            refresh(event);
            tfcomment.setText("");
        }else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Either the post is redolved or Text field is empty");
            a.show();
        }
    }

    @FXML
    private void refresh(ActionEvent event) {
        Comments cm = new Comments();
        tbview.getItems().clear();
        List<Comment> commentlist = cm.findAll("`post_id`=" + Id.post);
        for (Comment c : commentlist) {
            tbview.getItems().add(c);

        }
        tbview.refresh();
    }

    @FXML
    void leave(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
        postprofile.getChildren().setAll(pane);
    }

    @FXML
    void deletcomment(ActionEvent event) {
        Comment c = (Comment) tbview.getSelectionModel().getSelectedItem();
        Id.comment = c.getId();
        if (Id.user.equals(c.getCommenterId())) {
            new Comments().deleteById(Id.comment);
            refresh(event);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.show();
        }

    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        p = new Posts().findById(Id.post);
        p.setResolved(cbresolved.isSelected());
        new Posts().modify(p);

    }

}
