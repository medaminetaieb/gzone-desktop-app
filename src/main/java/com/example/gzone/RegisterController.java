package com.example.gzone;

import com.example.entity.Game;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.entity.UserGamePreference;
import com.example.service.Games;
import com.example.service.UserGamePreferences;
import com.example.service.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class RegisterController {

    private Integer userId;
    private Integer gameId;
    private Boolean invitable;
    private Enum role;
    private Date joinDate;

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
    private Hyperlink toLogin;

    @FXML
    void createUser(ActionEvent event) {
        Users user = new Users();
        UserGamePreferences ugp = new UserGamePreferences();
        LocalDate ld = birthDate.getValue();
        Instant instant = Instant.from(ld.atStartOfDay(ZoneId.systemDefault()));
        Date crd = Date.from(instant);
        user.insert(new User(
                null,
                phoneNumber.getText(),
                email.getText(),
                username.getText(),
                password.getText(),
                photoURL.getText(),
                fullName.getText(),
                bio.getText(),
                crd,
                new java.util.Date(),
                true,
                Role.user
        ));
        ugp.insert(new UserGamePreference(
               null,
                null,
                null
        ));

    }

    @FXML
    public void initialize() {

    }
}
