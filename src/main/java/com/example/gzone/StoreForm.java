package com.example.gzone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StoreForm {
    @FXML
    public Button validatebutton;
    public AnchorPane rootPane;

    @FXML
    void validate(ActionEvent event) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Store.fxml"));
        Parent root =(Parent) fxmlLoader.load();
        Stage stage = (Stage) validatebutton.getScene().getWindow();
        stage.setTitle("fill in");
        stage.setScene(new Scene(root));
        stage.show();
        stage.close();*/
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Store2.fxml"));
        rootPane.getChildren().setAll(pane);





}
}
