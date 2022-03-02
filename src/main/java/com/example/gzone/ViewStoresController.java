package com.example.gzone;

import com.example.entity.Store;
import com.example.service.Stores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewStoresController {
    @FXML
    private Button searchbutton;
    @FXML
    private TextField tfsearch;

    @FXML
    private AnchorPane viewstorespane;
    @FXML
    private TableView tbview;
    @FXML
    private TableColumn<Store,String> cltitle;

    @FXML
    void Forum(ActionEvent event) {

    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        viewstorespane.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) {

    }

    @FXML
    void Team(ActionEvent event) {

    }

    @FXML
    void Tournament(ActionEvent event) {

    }
    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        cltitle.setCellValueFactory(new PropertyValueFactory("title"));
        tbview.getColumns().add(cltitle);

    }
    @FXML
    private void SearchStore(ActionEvent event) {
        tbview.getItems().clear();
        Stores ps = new Stores();
        List<Store> storelist = ps.findAll("`name` REGEXP '" + tfsearch.getText() + "'");
        for (Store p : storelist) {
            tbview.getItems().add(p);


        }
        tbview.refresh();

    }


}
