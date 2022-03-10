package com.example.gzone;

import com.example.entity.User;
import com.example.service.Games;
import com.example.service.Posts;
import com.example.service.UserGamePreferences;
import com.example.service.Users;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
    private ListView favorite_games;
    @FXML
    private Button deletebtn;
    @FXML
    private Button modifybtn;
    @FXML
    private Button checkBtn;
    @FXML
    private Button tournamentbtn;
    @FXML
    private Hyperlink logout;
    @FXML
    private Button openqr;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        User u = new Users().findById(Id.user);
        UserGamePreferences ugp = new UserGamePreferences();
        ArrayList favgamearray = new ArrayList();
        Games favgames = new Games();
        for (int i = 0; i < ugp.findAll("`user_id` REGEXP '" + u.getId() + "'").size(); i++) {
            Integer favgameid = ugp.findAll("`user_id` REGEXP '" + u.getId() + "'").get(i).getGameId();
            String favgame = favgames.findAll("`id` REGEXP '" + favgameid + "'").get(0).getName();
            favgamearray.add(favgame);
        }
        ObservableList favgameobservable = FXCollections.observableArrayList(favgamearray);

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
        favorite_games.setItems(favgameobservable);
    }

    private void report(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Report.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Report User");
        newWindow.setScene(scene);
        newWindow.show();
    }

    @FXML
    private void delete(ActionEvent event) throws IOException {
        Users du = new Users();
        du.deleteById(Id.user);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        profile.getChildren().setAll(pane);
        String title = "Success!";
        String message = "Account deleted!";
        NotificationType notification = NotificationType.SUCCESS;
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(notification);
        tray.showAndDismiss(Duration.seconds(3));
    }

    @FXML
    private void modify(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("updateUser.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Update your information !");
        newWindow.setScene(scene);
        newWindow.show();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        profile.getChildren().setAll(pane);
    }

    @FXML
    private void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        profile.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("team-view.fxml"));
        profile.getChildren().setAll(pane);
    }

    @FXML
    private void Store(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        profile.getChildren().setAll(pane);
    }

    @FXML
    private void Forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
        profile.getChildren().setAll(pane);
    }

    @FXML
    private void checkRequests(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("accept-decline.fxml"));
        profile.getChildren().setAll(pane);
    }

    @FXML
    private void Tournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        profile.getChildren().setAll(pane);
    }

    @FXML
    private void changeInvitable(MouseEvent event) {
        User u = new Users().findById(Id.post);
        u.setInvitable(invitable.isSelected());
        new Users().modify(u);
    }

    @FXML
    private void openQr(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QrCode.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Report User");
        newWindow.setScene(scene);
        newWindow.show();
    }

}
