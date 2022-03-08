package com.example.gzone;

import com.example.entity.Store;
import com.example.entity.UserLikesDislike;
import com.example.service.Stores;
import com.example.service.UserLikesDislikes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
    private AnchorPane viewstorepane;
    @FXML
    private ListView<Store> tbview;

    @FXML
    private Button createstorebutton;
    @FXML
    private Button viewstorebutton;



    @FXML
    void Forum(ActionEvent event) {

    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        viewstorepane.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        viewstorepane.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event) {

    }

    @FXML
    void Tournament(ActionEvent event) {

    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {


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

    @FXML
    void ViewStore(ActionEvent event) throws IOException {
        if ((Id.store = ((Store) tbview.getSelectionModel().getSelectedItem()).getId()) != null) {

            AnchorPane pane = FXMLLoader.load(getClass().getResource("StoreProfile.fxml"));
            viewstorepane.getChildren().setAll(pane);
        }
    }

    @FXML
    void CreateStore(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("StoreForm.fxml"));
        viewstorepane.getChildren().setAll(pane);
    }
}