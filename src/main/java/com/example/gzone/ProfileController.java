package com.example.gzone;

import com.example.entity.User;
import com.example.entity.UserGamePreference;
import com.example.service.UserGamePreferences;
import com.example.service.Users;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class ProfileController implements Initializable {

    @FXML
    private AnchorPane profile;
    @FXML
    public Text fullname;
    @FXML
    public ImageView profilepic;
    @FXML
    public Text username;
    @FXML
    public Text phone;
    @FXML
    public Text birthdate;
    @FXML
    public Text email;
    @FXML
    public CheckBox invitable;
    @FXML
    public TextArea bio;
    @FXML
    public TableColumn<?, ?> favorite_games;
    @FXML
    private Hyperlink reporthyperlink;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User u = new Users().findById(Id.user);
        fullname.setText(u.getFullName());
        try {
            profilepic.setImage(new Image(new FileInputStream(u.getPhotoURL())));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        username.setText(u.getUsername());
        phone.setText(u.getPhoneNumber());
        birthdate.setText(u.getBirthDate().toString());
        bio.setText(u.getBio());
        email.setText(u.getEmail());
        invitable.setSelected(u.isInvitable());
    }

    @FXML
    private void reportUser(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainFX.class.getResource("Report.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        StackPane secondaryLayout = new StackPane();
        Stage newWindow = new Stage();
        newWindow.setTitle("Report User");
        newWindow.setScene(scene);
        newWindow.show();
    }

    @FXML
    private void HomePage(ActionEvent event) {
    }

    @FXML
    private void Team(ActionEvent event) {
    }

    @FXML
    private void Tournament(ActionEvent event) {
    }

    @FXML
    private void Store(ActionEvent event) {
    }

    @FXML
    private void Forum(ActionEvent event) {

    }

}
