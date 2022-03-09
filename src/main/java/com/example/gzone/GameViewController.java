/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import com.example.entity.Game;
import com.example.entity.User;
import com.example.service.Games;
import com.example.util.TournamentStat;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML GameViewController class
 *
 * @author chayma
 */
public class GameViewController implements Initializable {

    private Integer gameId;
    public AnchorPane gamepane;
    @FXML
    private ListView<Game> GameList;
    @FXML
    private Label MyLabel;
    @FXML
    private ListView<User> Top5;
    @FXML
    private Text Tourid;
    @FXML
    private Text tnot;

    @FXML
    private void edit(ActionEvent event) throws IOException {
        Id.game = GameList.getSelectionModel().getSelectedItem().getId();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditandDeleteGame.fxml"));
        // gamepane.getChildren().setAll(pane);
        BTNEdit.getScene().setRoot(pane);
    }

    @FXML
    private void AddGame(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("CreateGame.fxml"));
        //gamepane.getChildren().setAll(pane);
        btnDelete.getScene().setRoot(pane);

    }
    @FXML
    private Button btnAddGame;
    @FXML
    private Button btnSearch;
    private TableView tbView;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button BTNEdit;
    @FXML
    private Button btnDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        find(null);
        Top5.getItems().addAll(TournamentStat.TopFive());
        tnot.setVisible(false);
        Tourid.setVisible(false);
    }

    @FXML
    void Forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
        gamepane.getChildren().setAll(pane);
    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        gamepane.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        gamepane.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("team-view.fxml"));
        gamepane.getChildren().setAll(pane);
    }

    @FXML
    void Tournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        gamepane.getChildren().setAll(pane);
    }

    @FXML

    private void delete(ActionEvent event) {
        int id = ((Game) GameList.getSelectionModel().getSelectedItem()).getId();
        new Games().deleteById(id);
        find(event);
        String title = "Success!";
        String message = "Game was deleted!";
        NotificationType notification = NotificationType.SUCCESS;
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(notification);
        tray.showAndDismiss(Duration.seconds(3));
        GameList.refresh();

    }

    @FXML
    private void find(ActionEvent event) {
        Games games = new Games();
        GameList.getItems().clear();
        List<Game> list = games.findAll("name REGEXP '" + tfSearch.getText() + "'");
        for (Game g1 : list) {
            GameList.getItems().add(g1);
        }
        GameList.refresh();
    }

    @FXML
    private void UpdateNumberOfTournament() {
        tnot.setVisible(true);
        Tourid.setVisible(true);
        Tourid.setText("" + TournamentStat.CountTournaments(GameList.getSelectionModel().getSelectedItem().getId()));

    }

}
