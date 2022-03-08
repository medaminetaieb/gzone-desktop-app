package com.example.gzone;

import com.example.entity.Role;
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
    private Button SignIN;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    public Hyperlink toRegister;
    @FXML
    public AnchorPane signinpane;
    public AnchorPane homepagepane;
    @FXML
    private Label reglb1;
    @FXML
    private Hyperlink forgot;

    
    
    @FXML
    public void ToRegister(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Register.fxml"));
        signinpane.getChildren().setAll(pane);
    }

  

    public void initialize() {

    }

    @FXML
    private void checkUser(ActionEvent event) throws IOException {
         if ((Id.user=new Users().checklogin(username.getText(), password.getText())) != null){
            User u = new Users().findById(Id.user);
            if (u.getRole().equals(Role.admin)) {
                AnchorPane panee = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                signinpane.getChildren().setAll(panee);
                
            } else {
                AnchorPane panee = FXMLLoader.load(getClass().getResource("Profile.fxml"));
                signinpane.getChildren().setAll(panee);
            }

        }
    }

    @FXML
    private void ForgotPassword(ActionEvent event) {
    }

}
