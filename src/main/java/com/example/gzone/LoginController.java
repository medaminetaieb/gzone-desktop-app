package com.example.gzone;

import com.example.entity.User;

import com.example.service.Users;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class LoginController {

    @FXML
    private Label SignIn;
    @FXML
    private Button SignIN;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    public Hyperlink toRegister;
    @FXML
    public AnchorPane signinpane;
    @FXML
    public AnchorPane homepagepane;

    @FXML
    public void ToRegister(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Register.fxml"));
        signinpane.getChildren().setAll(pane);
    }

    @FXML
    private void checkUser() throws IOException {
        if (new Users().checklogin(username.getText(), password.getText()) != null) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            signinpane.getChildren().setAll(panee);
        }
    }

    public void initialize() {

    }

}
