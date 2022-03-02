
package com.example.gzone;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.layout.AnchorPane;
        import javafx.stage.Stage;

        import java.io.IOException;

public class StoreController {

    @FXML
public AnchorPane storepane;

    @FXML
    void Createstore(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("StoreForm.fxml"));
        storepane.getChildren().setAll(pane);
    }

    @FXML
    void Forum(MouseEvent event) {

    }

    @FXML
    void HomePage(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        storepane.getChildren().setAll(pane);
    }

    @FXML
    void Store(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Store.fxml"));
        storepane.getChildren().setAll(pane);
    }

    @FXML
    void Team(MouseEvent event) {

    }

    @FXML
    void Tournament(MouseEvent event) {

    }

}
