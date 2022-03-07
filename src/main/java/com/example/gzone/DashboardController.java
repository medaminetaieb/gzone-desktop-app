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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /*data = FXCollections.observableArrayList(new Users().findAll());
        listviewusers.setItems(data);
        System.out.println(data);*/
         data.clear();
         Users us = new Users();
      
        data = FXCollections.observableArrayList(us.findAll());
        tfullname.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tusername.setCellValueFactory(new PropertyValueFactory<>("username"));
        temail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tpassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        tphone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tbirthdate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        tjoindate.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
        tphoto.setCellValueFactory(new PropertyValueFactory<>("photoURL"));
         trole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableviewuser.setItems(data);
       
    }
   @FXML
    public void refreshlist(ActionEvent event) {
       
    }
    
  


    @FXML
    private void search(KeyEvent event) {
        tableviewuser.getItems().clear();
        new Users().findAll().stream().filter(u -> u.getFullName().indexOf(search.getText())!=-1).peek(System.out::println).forEach(u->tableviewuser.getItems().add(u));
        tableviewuser.refresh();
        /*FilteredList<User> filtereddata = new FilteredList<>(data, b -> true);
   
        search.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return false;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (user.getFullName().toLowerCase().contains(lowercasefilter) == true) {
                    return true;             
                } else {
                    return false;
                }

            });

        });
        System.out.println(filtereddata);
        SortedList<User> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(tableviewuser.comparatorProperty());
        tableviewuser.setItems(sorteddata);*/
        
    }

}
