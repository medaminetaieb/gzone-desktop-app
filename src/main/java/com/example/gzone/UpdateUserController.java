/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.Role;
import com.example.entity.User;
import com.example.service.Users;
import static com.example.util.Gzon.saveSession;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class UpdateUserController implements Initializable {

    @FXML
    private AnchorPane updatepane;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private Label olddate;
    @FXML
    private TextField fullName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private DatePicker birthDate;
    @FXML
    private Label reglb;
    @FXML
    private TextArea bio;
    @FXML
    private TextField photoURL;
    @FXML
    private Label control;
    @FXML
    private Label birthdatecontrol;
    @FXML
    private Button update;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        birthdatecontrol.setVisible(false);
        control.setVisible(false);
        User old = new Users().findById(Id.user);
        username.setText(old.getUsername());
        email.setText(old.getEmail());
        fullName.setText(old.getFullName());
        phoneNumber.setText(old.getPhoneNumber());
        Instant instant = Instant.ofEpochMilli(old.getBirthDate().getTime());
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        birthDate.setValue(ldt.toLocalDate());
        bio.setText(old.getBio());
        photoURL.setText(old.getPhotoURL());
    }

    @FXML
    private void update(ActionEvent event) throws ParseException, IOException {
        Users old = new Users();
        User tochange = old.findById(Id.user);
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate ld = birthDate.getValue();
            Date permitted = sdf.parse("2010-01-01");
            Instant instant = Instant.from(ld.atStartOfDay(ZoneId.systemDefault()));
            Date bdate = Date.from(instant);
            if ((bdate.after(permitted))) {
                birthdatecontrol.setVisible(true);
            } else if ((email.getText().isBlank())
                    || (username.getText().isBlank())
                    || (fullName.getText().isBlank())) {
                control.setVisible(true);
            } else {

                old.modifyWithoutPassword(new User(
                        Id.user,
                        phoneNumber.getText(),
                        email.getText(),
                        username.getText(),
                        null,
                        photoURL.getText(),
                        fullName.getText(),
                        bio.getText(),
                        bdate,
                        new java.util.Date(),
                        true,
                        Role.ROLE_USER,
                        1
                        
                ));
                saveSession(username.getText());
                String title = "Success!";
                String message = "User updated!";
                NotificationType notification = NotificationType.SUCCESS;
                TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(notification);
                tray.showAndDismiss(Duration.seconds(3));;
            }
        } catch (NullPointerException ex) {
            birthdatecontrol.setVisible(true);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Profile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage newWindow = new Stage();
        newWindow.setTitle("G-Zone");
        newWindow.setScene(scene);
        newWindow.show();

    }

}
