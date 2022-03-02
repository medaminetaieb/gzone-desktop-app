package com.example.gzone;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TeamUpdateController implements Initializable {

    @FXML
    private TextField teamname;

    @FXML
    private TextField photourl;

    @FXML
    private Spinner<?> scroller;

    @FXML
    private TextArea descritpion;

    @FXML
    private RadioButton rb1;

    @FXML
    private RadioButton rb2;

    @FXML
    private Button updateteam;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
