package com.example.gzone;

import com.example.entity.User;
import com.example.service.Users;
import static com.example.util.Gzon.saveSession;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class ChangePasswordController implements Initializable {

    @FXML
    private PasswordField newpassword;
    @FXML
    private PasswordField confirmpassword;
    @FXML
    private Button submit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void changepassword(ActionEvent event) {
        if (newpassword.getText().equals(confirmpassword.getText())) {
            Users u = new Users();
            User uu = u.findById(Id.user);
            u.modify(new User(
                    Id.user,
                    uu.getPhoneNumber(),
                    uu.getEmail(),
                    uu.getUsername(),
                    newpassword.getText(),
                    uu.getPhotoURL(),
                    uu.getFullName(),
                    uu.getBio(),
                    uu.getBirthDate(),
                    uu.getJoinDate(),
                    uu.isInvitable(),
                    uu.getRole()
            ));
            saveSession(uu.getUsername(),newpassword.getText());
            
            String title = "Password changed";
            String message = "Your password was updated !";
            NotificationType notification = NotificationType.SUCCESS;

            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(4));
            submit.getScene().getWindow().hide();
       
        } else {
            
            String title = "Error";
            String message = "Make sure that your passwords match !";
            NotificationType notification = NotificationType.ERROR;

            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.seconds(4));
        }
    }

}
