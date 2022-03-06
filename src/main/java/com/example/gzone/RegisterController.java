package com.example.gzone;

import com.example.entity.Game;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.entity.UserGamePreference;
import com.example.service.Games;
import com.example.service.UserGamePreferences;
import com.example.service.Users;
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
import java.util.concurrent.TimeUnit;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class RegisterController {

    private Integer userId;

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
    public Hyperlink toLogin;
    @FXML
    public AnchorPane registerpane;
    @FXML
    private Label reglb;
    @FXML
    private Label birthdatecontrol;
    @FXML
    private Label control;

    ;

    public void initialize() {
        birthdatecontrol.setVisible(false);
        control.setVisible(false);
        FileChooser fileChooser = new FileChooser();
    }

    @FXML
    void createUser(ActionEvent event) throws ParseException {
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
            } else {
                user.insert(new User(
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
                ));
            }
        } catch (NullPointerException ex) {
            birthdatecontrol.setVisible(true);
        }

    }

    @FXML
    void CreateGamesFav(MouseEvent mouseevent) throws InterruptedException {

        System.out.println("rftgvrfc");
        Users gu = new Users();
        Games gg = new Games();
        Integer game_id = gg.findAll("`name` REGEXP '" + leagueOfLegends.getText() + "'").get(0).getId();
        Integer user_id = gu.findAll("`id`= (SELECT MAX(id) FROM users)").get(0).getId();

        System.out.println(game_id);
        UserGamePreferences ugp = new UserGamePreferences();
        ugp.insert(new UserGamePreference(
                null,
                user_id,
                game_id
        ));
    }

    @FXML
    public void ToLogin(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        registerpane.getChildren().setAll(pane);
    }

}
