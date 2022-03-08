package com.example.gzone;

import com.example.entity.User;
import com.example.service.Games;
import com.example.service.UserGamePreferences;
import com.example.service.Users;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class ProfileController implements Initializable {

    @FXML
    private AnchorPane profile;
    @FXML
    public Text fullname;
    @FXML
    public ImageView profilepic;
    @FXML
    public Text username;
    @FXML
    public Text phone;
    @FXML
    public Text birthdate;
    @FXML
    public Text email;
    @FXML
    public CheckBox invitable;
    @FXML
    public TextArea bio;

    @FXML
    private Hyperlink reporthyperlink;
    @FXML
    private ListView favorite_games;
    @FXML
    private Button deletebtn;
    @FXML
    private Button modifybtn;
    @FXML
    private Button logout;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User u = new Users().findById(Id.user);
        UserGamePreferences ugp = new UserGamePreferences();
        ArrayList favgamearray = new ArrayList();
        Games favgames = new Games();
        for (int i = 0; i < ugp.findAll("`user_id` REGEXP '" + u.getId() + "'").size(); i++) {
            Integer favgameid = ugp.findAll("`user_id` REGEXP '" + u.getId() + "'").get(i).getGameId();
            String favgame = favgames.findAll("`id` REGEXP '" + favgameid + "'").get(0).getName();
            favgamearray.add(favgame);
        }
        ObservableList favgameobservable = FXCollections.observableArrayList(favgamearray);

        fullname.setText(u.getFullName());
        try {
            profilepic.setImage(new Image(new FileInputStream(u.getPhotoURL())));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        username.setText(u.getUsername());
        phone.setText(u.getPhoneNumber());
        birthdate.setText(u.getBirthDate().toString());
        bio.setText(u.getBio());
        email.setText(u.getEmail());
        invitable.setSelected(u.isInvitable());
        favorite_games.setItems(favgameobservable);
    }

    @FXML
    private void report(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Report.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Report User");
        newWindow.setScene(scene);
        newWindow.show();
    }

    @FXML
    private void delete(ActionEvent event) throws IOException {
        Users du = new Users();
        du.deleteById(Id.user);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        profile.getChildren().setAll(pane);
    }

    @FXML
    private void modify(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Update.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Update your information !");
        newWindow.setScene(scene);
        newWindow.show();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        profile.getChildren().setAll(pane);
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

}
