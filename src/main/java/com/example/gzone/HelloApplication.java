package com.example.gzone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.Parent;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
      //  Parent root = FXMLLoader.load(getClass().getResource("Store.fxml"));
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
       Scene scene = new Scene(fxmlLoader.load());
       //Scene scene = new Scene(root);
        stage.setTitle("Gzone-Project");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {

        launch();
    }
}