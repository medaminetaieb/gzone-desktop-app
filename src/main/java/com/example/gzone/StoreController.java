
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
    void HomePage(MouseEvent event) {

    }

    @FXML
    void Store(MouseEvent event) {

    }

    @FXML
    void Team(MouseEvent event) {

    }

    @FXML
    void Tournament(MouseEvent event) {

    }

}
