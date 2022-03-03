/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.User;
import com.example.entity.UserGamePreference;
import com.example.service.UserGamePreferences;
import com.example.service.Users;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;

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
    public TableColumn<?, ?> favorite_games;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User u = new Users().findById(Id.user);
        UserGamePreference gup = new UserGamePreferences().findById(Id.usergamepreferences);
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
