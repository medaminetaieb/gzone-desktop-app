/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.Badge;
import com.example.entity.Game;
import com.example.entity.HappyHour;
import com.example.service.Badges;
import com.example.service.Games;
import com.example.service.HappyHours;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.text.html.HTMLEditorKit;

/**
 * FXML Controller class
 *
 * @author chayma
 */
public class CreateHappyHourController implements Initializable {

    private Integer gameId;
    private Integer badgeId;

    @FXML
    private DatePicker dpStartDate;
    @FXML
    private DatePicker dpEndDate;
    @FXML
    private SplitMenuButton smbGame;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btCancel;
    private SplitMenuButton spMBadge;
    @FXML
    private TextField tfBadge;
    @FXML
    private AnchorPane CreatePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         List<Game> gamelist = new Games().findAll();
        MenuItem smbgmi = new MenuItem("No Game");
        smbGame.getItems().add(smbgmi);
        smbgmi.setOnAction(e -> {
            gameId = null;
            smbGame.setText("No Game");
        });
        for (Game g : gamelist) {
            MenuItem mi = new MenuItem(g.getName());
            mi.setOnAction(e -> {
                gameId = g.getId();
                smbGame.setText(g.getName());
            });
            smbGame.getItems().add(mi);
        }
                
        
        
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
    private void createHappyHour(ActionEvent event) {
        Badges badges = new Badges();
        HappyHours happyHours = new HappyHours();
        LocalDate ld = dpStartDate.getValue();
        Instant instant = Instant.from(ld.atStartOfDay(ZoneId.systemDefault()));
        Date crd = new Date(Date.from(instant).getTime());
        LocalDate ld1 = dpEndDate.getValue();
        Instant instant1 = Instant.from(ld.atStartOfDay(ZoneId.systemDefault()));
        Date crd1 = new Date(Date.from(instant).getTime());
        Alert a = new Alert(Alert.AlertType.NONE);
        if (ld.isBefore(ld1) == true
                && dpStartDate.getValue() != null
                && dpEndDate.getValue() != null
                && !tfBadge.getText().isBlank()) {
            badges.insert(new Badge(null, gameId, tfBadge.getText()));
            happyHours.insert(new HappyHour(null, badges.findAll("`title` REGEXP '" + tfBadge.getText() + "'").get(0).getId(), crd, crd1));
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setTitle("success");
            a.setHeaderText("Success");
            a.setContentText("HappyHour is added successefully");
            a.show();
        } else {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setTitle("faild");
            a.setHeaderText("faild");
            a.setContentText("please check the form");
            a.show();
        }
    }

    @FXML
    private void CancelHappyHour(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("HappyHour.fxml"));
        CreatePane.getChildren().setAll(pane);
    }
}
