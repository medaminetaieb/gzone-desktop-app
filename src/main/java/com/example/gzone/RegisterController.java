package com.example.gzone;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class RegisterController {

    ObservableList<String> GamePreferencesList = FXCollections.observableArrayList("League of Legends", "FIFA", "Minecraft", "Battlefield");
    @FXML
    private TextField fullName;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField photo;
    @FXML
    private DatePicker birthDate;
    @FXML
    private RadioButton leagueOfLegends;
    @FXML
    private RadioButton fifa;
    @FXML
    private RadioButton minecraft;
    @FXML
    private RadioButton battlefield;
    @FXML
    private TextArea bio;
    @FXML
    private Button register;
    @FXML
    private Hyperlink toLogin;

    private void initialize() {

    }

}
