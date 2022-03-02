package com.example.gzone;

import com.example.entity.Game;
import com.example.entity.Store;
import com.example.service.Games;
import com.example.service.Stores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class StoreForm {

    private Integer gameId;
    @FXML
    public Button validatebutton,cancelbutton;


    @FXML
    private SplitMenuButton smbGame;

    @FXML
    public TextField storename;
    @FXML
    public AnchorPane rootPane;

    @FXML
    void validate(ActionEvent event) throws IOException {
        if((!storename.getText().isBlank()) && new Stores().insert(new Store(null, 1, gameId, storename.getText())))
        {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Store2.fxml"));
            rootPane.getChildren().setAll(pane);
        }



    }

    @FXML
    void CancelCreateStore(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Store.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    @FXML
    public void initialize(){
        List<Game> gameList = new Games().findAll();
        MenuItem smbgmi = new MenuItem("No Game");
        smbGame.getItems().add(smbgmi);
        smbgmi.setOnAction(e -> {
            gameId = null;
            smbGame.setText("No Game");
        });
        for (Game g : gameList) {
            MenuItem mi = new MenuItem(g.getName());
            mi.setOnAction(e -> {
                gameId = g.getId();
                smbGame.setText(g.getName());
            });
            smbGame.getItems().add(mi);
        }
    }
}
