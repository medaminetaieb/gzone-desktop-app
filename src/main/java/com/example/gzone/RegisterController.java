package com.example.gzone;

import com.example.entity.Game;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.entity.UserGamePreference;
import static com.example.gzone.UploadDropBox.uploadPhoto;
import com.example.service.Games;
import com.example.service.UserGamePreferences;
import com.example.service.Users;
import com.example.util.Gzon;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class RegisterController {

    private Integer userId;
    private List<Integer> selectedgames = new ArrayList<>();
    @FXML
    private TextField fullName;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField photoURL;
    @FXML
    private PasswordField password;
    @FXML
    private DatePicker birthDate;
    private RadioButton leagueOfLegends;
    @FXML
    private TextArea bio;
    @FXML
    private Button register;
    @FXML
    public Hyperlink toLogin;
    @FXML
    public AnchorPane registerpane;
    @FXML
    private Label reglb;
    @FXML
    private Label birthdatecontrol;
    @FXML
    private Label control;
    @FXML
    private MenuButton mbgames;
    @FXML
    private Button importphoto;

    public void initialize() {

        birthdatecontrol.setVisible(false);
        control.setVisible(false);
        Games gs = new Games();
        for (Game g : gs.findAll()) {
            RadioMenuItem i = new RadioMenuItem(g.getName());
            i.setOnAction(e -> {
                if (i.isSelected()) {
                    selectedgames.add(g.getId());
                } else {
                    selectedgames.remove(g.getId());
                }
            });
            mbgames.getItems().add(i);
        }

    }

    @FXML
    private void importPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your profile pic");
        Window stage = null;
        String path = fileChooser.showOpenDialog(stage).getAbsolutePath();
        String name = "/" + fileChooser.showOpenDialog(stage).getName();
        uploadPhoto(path, name);

    }

    @FXML
    void createUser(ActionEvent event) throws ParseException, IOException {
        Users user = new Users();

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
                    || (fullName.getText().isBlank())
                    || (password.getText().isBlank())) {
                control.setVisible(true);
            } else if (user.insert(new User(
                    null,
                    phoneNumber.getText(),
                    email.getText(),
                    username.getText(),
                    password.getText(),
                    photoURL.getText(),
                    fullName.getText(),
                    bio.getText(),
                    bdate,
                    new java.util.Date(),
                    true,
                    Role.user
            ))) {

                String title = "Success!";
                String message = "Welcome to G-ZONE!";
                NotificationType notification = NotificationType.SUCCESS;
                TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(notification);
                tray.showAndDismiss(Duration.seconds(3));

                Id.user = user.findAll("`email` REGEXP '" + email.getText() + "'").get(0).getId();

                UserGamePreferences ugps = new UserGamePreferences();

                for (Integer i : selectedgames) {
                    ugps.insert(new UserGamePreference(null, Id.user, i));
                    AnchorPane panee = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                    registerpane.getChildren().setAll(panee);

                }
                Gzon.saveSession(username.getText(), password.getText());
            } else {
                birthdatecontrol.setVisible(true);
                String title = "Failed!";
                String message = "Verify your information!";
                NotificationType notification = NotificationType.ERROR;
                TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(notification);
                tray.showAndDismiss(Duration.seconds(3));
            }
        } catch (NullPointerException ex) {
            birthdatecontrol.setVisible(true);
            String title = "Failed!";
            String message = "Verify your information!";
            NotificationType notification = NotificationType.ERROR;
            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(3));
        }

    }

    @FXML
    public void ToLogin(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        registerpane.getChildren().setAll(pane);
    }

}
