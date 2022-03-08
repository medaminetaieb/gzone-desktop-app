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
import com.example.entity.UserLikesDislike;
import com.example.service.Comments;
import com.example.service.Posts;
import com.example.service.UserLikesDislikes;
import com.google.protobuf.ByteString;
import static java.awt.SystemColor.text;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.Locale;
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

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
    @FXML
    private Button btnlike;
    @FXML
    private Button btnDislike;
    @FXML
    private Text likeCount;
    @FXML
    private Text dislikeCount;
    @FXML
    private Button btnlisten;

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
        if (!p.getPosterId().equals(Id.user)) {
            cbresolved.setDisable(true);
        }

        refresh(null);

        btnlike.setText((new UserLikesDislikes().findAll("`post_id`=" + Id.post + " and `user_id`=" + Id.user + " and `like`=true").isEmpty()) ? "Like" : "UnLike");
        btnDislike.setText((new UserLikesDislikes().findAll("`post_id`=" + Id.post + " and `user_id`=" + Id.user + " and `like`=false").isEmpty()) ? "Dislike" : "UnDislike");
        refreshCounts();
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
        if (!p.isResolved() && !tfcomment.getText().isBlank()) {
            Comments cm = new Comments();
            Comment c = new Comment(null, Id.post, Id.user, tfcomment.getText(), new Date());
            cm.insert(c);
            refresh(event);
            tfcomment.setText("");
        } else {
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

    @FXML
    private void handleLike(ActionEvent event) {
        if (new UserLikesDislikes().findAll("`post_id`=" + Id.post + " and `user_id`=" + Id.user + " and `like`=true").isEmpty()) {
            new UserLikesDislikes().insert(new UserLikesDislike(null, Id.user, null, Id.post, null, true));
            btnlike.setText("Unlike");
            if (!new UserLikesDislikes().findAll("`post_id`=" + Id.post + " and `user_id`=" + Id.user + " and `like`=false").isEmpty()) {
                new UserLikesDislikes().delete("`post_id`=" + Id.post + " and `user_id`=" + Id.user + " and `like`=false");
                btnDislike.setText("Dislike");
            }
        } else {
            new UserLikesDislikes().delete("`post_id`=" + Id.post + " and `user_id`=" + Id.user + " and `like`=true");
            btnlike.setText("Like");
        }
        refreshCounts();
    }

    @FXML
    private void handleDisLike(ActionEvent event) {
        if (new UserLikesDislikes().findAll("`post_id`=" + Id.post + " and `user_id`=" + Id.user + " and `like`=false").isEmpty()) {
            new UserLikesDislikes().insert(new UserLikesDislike(null, Id.user, null, Id.post, null, false));
            btnDislike.setText("UnDislike");
            if (!new UserLikesDislikes().findAll("`post_id`=" + Id.post + " and `user_id`=" + Id.user + " and `like`=true").isEmpty()) {
                new UserLikesDislikes().delete("`post_id`=" + Id.post + " and `user_id`=" + Id.user + " and `like`=true");
                btnlike.setText("Like");
            }
        } else {
            new UserLikesDislikes().delete("`post_id`=" + Id.post + " and `user_id`=" + Id.user + " and `like`=false");
            btnDislike.setText("Dislike");
        }
        refreshCounts();
    }

    private void refreshCounts() {
        likeCount.setText("" + new UserLikesDislikes().findAll("`post_id`=" + Id.post + " and `like`=true").size());
        dislikeCount.setText("" + new UserLikesDislikes().findAll("`post_id`=" + Id.post + " and `like`=false").size());
    }

    @FXML
    public void listen(ActionEvent event) {

        try {
            // Set property as Kevin Dictionary
            System.setProperty(
                    "freetts.voices",
                    "com.sun.speech.freetts.en.us"
                    + ".cmu_us_kal.KevinVoiceDirectory");

            // Register Engine
            Central.registerEngineCentral(
                    "com.sun.speech.freetts"
                    + ".jsapi.FreeTTSEngineCentral");

            // Create a Synthesizer
            Synthesizer synthesizer
                    = Central.createSynthesizer(
                            new SynthesizerModeDesc(Locale.US));

            // Allocate synthesizer
            synthesizer.allocate();

            // Resume Synthesizer
            synthesizer.resume();

            // Speaks the given text
            // until the queue is empty.
            synthesizer.speakPlainText(
                    tfcontent.getText(), null);
            synthesizer.waitEngineState(
                    Synthesizer.QUEUE_EMPTY);

            // Deallocate the Synthesizer.
            synthesizer.deallocate();
        } catch (IllegalArgumentException | InterruptedException | AudioException | EngineException e) {
            e.printStackTrace();
        }
    }

}
