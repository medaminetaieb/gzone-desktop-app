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
    private void edit(ActionEvent event) throws IOException {
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
    
    private void delete(ActionEvent event) {
        int id = ((Game) GameList.getSelectionModel().getSelectedItem()).getId();
        new Games().deleteById(id);
        find(event);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setHeaderText("Success");
        alert.setContentText("Game is delete successefully");
        alert.show();
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
    
}
