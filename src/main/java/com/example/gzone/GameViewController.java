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
import com.example.service.Games;
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
    private void edit(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditandDeleteGame.fxml"));
        gamepane.getChildren().setAll(pane);
    }
    @FXML
    private void AddGame(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("CreateGame.fxml"));
        gamepane.getChildren().setAll(pane);
    }
    @FXML
    private Button btnAddGame;
    @FXML
    private Button btnSearch;
    @FXML
    private TableView tbView;
    @FXML
    private TableColumn<Game, String> clName;
    @FXML
    private TableColumn<Game, String> clDescription;
    @FXML
    private TableColumn<Game, String> clPhotoUrl;
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
        Games games = new Games();
        clName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbView.getColumns().add(clName);
        clDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tbView.getColumns().add(clDescription);
        clPhotoUrl.setCellValueFactory(new PropertyValueFactory<>("photoUrl"));
        tbView.getColumns().add(clPhotoUrl);
        for (Game g : games.findAll()) {
            tbView.getItems().add(g);
        }
    tbView.refresh();
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
        int id = ((Game) tbView.getSelectionModel().getSelectedItem()).getId();
     new Games().deleteById(id);
        find(event);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setHeaderText("Success");
        alert.setContentText("Game is delete successefully");
        alert.show();
        tbView.refresh();

    }
      @FXML
    private void find(ActionEvent event) {
        tbView.getItems().clear();
        Games g = new Games();
        List<Game> gamelist = g.findAll("name REGEXP '" + tfSearch.getText() + "'");
        for (Game g1 : gamelist) {
            tbView.getItems().add(g1);
        }
        tbView.refresh();
    }
    
    

}
