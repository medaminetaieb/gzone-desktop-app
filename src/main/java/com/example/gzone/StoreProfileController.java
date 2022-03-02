package com.example.gzone;

import com.example.entity.MarketItem;
import com.example.service.MarketItems;
import com.example.service.Stores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;



import java.io.IOException;
import java.util.List;

public class StoreProfileController {

        @FXML
        public  TableView tbview;
        @FXML
        public Button viewbutton;
        @FXML
        public TableColumn<MarketItem, String> cltitle;

        @FXML
        public Button addbutton;

        @FXML
        public  AnchorPane profilepane;
        @FXML
        public Button deletebutton;

        @FXML
        void AddToStore(ActionEvent event) throws IOException {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AddItemForm.fxml"));
            profilepane.getChildren().setAll(pane);
        }
        @FXML
        void DeleteYourItem(ActionEvent event) throws IOException {
                new MarketItems().deleteById(
                        ((MarketItem) tbview.getSelectionModel().getSelectedItem()).getId()
                );
                ViewItem();
        }

        @FXML
        void Forum(ActionEvent event) {

        }

        @FXML
        void HomePage(ActionEvent event) throws IOException {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                profilepane.getChildren().setAll(pane);
        }

        @FXML
        void Store(ActionEvent event) throws IOException{
                AnchorPane pane = FXMLLoader.load(getClass().getResource("Store2.fxml"));
                profilepane.getChildren().setAll(pane);
        }

        @FXML
        void Team(ActionEvent event) {

        }

        @FXML
        void Tournament(ActionEvent event) {

        }

        @FXML
        void initialize(){
                cltitle.setCellValueFactory(new PropertyValueFactory<>("title"));
                tbview.getColumns().add(cltitle);

                List<MarketItem> l = new MarketItems().findAll("`store_id`=" + 63);
                tbview.getItems().clear();
                for (MarketItem m : l) {
                        tbview.getItems().add(m);

                }
                tbview.refresh();
        }

        @FXML
        public void ViewItem() {

                List<MarketItem> l = new MarketItems().findAll("`store_id`=" + 63);
                tbview.getItems().clear();
                for (MarketItem m : l) {
                        tbview.getItems().add(m);

                }
                tbview.refresh();
        }
        @FXML
        void DeleteYourStore(ActionEvent event) {
             //   new Stores().deleteById(id);
        }


}




