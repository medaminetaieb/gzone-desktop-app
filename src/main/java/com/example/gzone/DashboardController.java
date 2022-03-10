/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.Report;
import com.example.entity.User;
import com.example.service.Reports;
import com.example.service.Users;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class DashboardController implements Initializable {

    ArrayList datareports = new ArrayList();
    ObservableList<User> datauser = FXCollections.observableArrayList();
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
    private ListView<?> reportslistview;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();
    @FXML
    private BarChart<String, Integer> barChart ;

    @FXML
    private CategoryAxis xAxis;
    @FXML
    private Button managegames;
    @FXML
    private Button managehappyhours;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        monthNames.addAll(Arrays.asList(months));
        xAxis.setCategories(monthNames);
        Calendar cal = Calendar.getInstance();
        List<User> usersdata = new Users().findAll();
        int[] monthCounter = new int[12];
        for (User u : usersdata) {
            cal.setTime(u.getBirthDate());
            int month = cal.get(Calendar.MONTH);
            monthCounter[month]++;
        }
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }
        barChart.getData().add(series);


        datauser.clear();
        datareports.clear();
        Users us = new Users();
        datauser = FXCollections.observableArrayList(us.findAll());
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
        tableviewuser.setItems(datauser);

        Reports rs = new Reports();
        for (Report r : rs.findAll()) {
            datareports.add(r.getHead());
        }

        ObservableList reports = FXCollections.observableArrayList(datareports);
        reportslistview.setItems(reports);
        reportslistview.setItems(reports);

        reportslistview.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    Id.report = new Reports().findAll("`head` REGEXP '" + reportslistview.getSelectionModel().getSelectedItem().toString() + "'").get(0).getId();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminReport.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load());
                    } catch (IOException ex) {
                        Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Stage newWindow = new Stage();
                    newWindow.setTitle("Manage Report");
                    newWindow.setScene(scene);
                    newWindow.show();
                }
            }
        });
    }

    @FXML
    private void search(KeyEvent event) {
        tableviewuser.getItems().clear();
        new Users().findAll().stream().filter(u -> u.getFullName().indexOf(search.getText()) != -1)
                .forEach(u -> tableviewuser.getItems().add(u));
        tableviewuser.refresh();

    }

    @FXML
    private void openManageGames(ActionEvent event) {
        
        
    }

    @FXML
    private void openHappHours(ActionEvent event) {
    }


}
