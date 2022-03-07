/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.User;
import com.example.service.Users;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class DashboardController implements Initializable {
    ObservableList<User> data = FXCollections.observableArrayList();
    @FXML
    private TextField search;
    @FXML
    private TableColumn<?, ?> tid;
    @FXML
    private TableColumn<?, ?> tusername;
    @FXML
    private TableColumn<?, ?> tfullname;
    @FXML
    private TableColumn<?, ?> tpassword;
    @FXML
    private TableColumn<?, ?> temail;
    @FXML
    private TableColumn<?, ?> tphone;
    @FXML
    private TableColumn<?, ?> tbirthdate;
    @FXML
    private TableColumn<?, ?> tjoindate;
    @FXML
    private TableColumn<?, ?> tphoto;
    @FXML
    private TableColumn<?, ?> trole;
    @FXML
    private TableView tableviewuser;
    @FXML
    private ListView listviewusers;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data.clear();
       data = FXCollections.observableArrayList(new Users().findAll());
           listviewusers.setItems(data);
           System.out.println(data);
    }    

    @FXML
    private void search(KeyEvent event) {
    }
    
}
