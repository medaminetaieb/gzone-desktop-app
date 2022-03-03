package com.example.gzone;

import com.example.entity.MarketItem;
import com.example.entity.Store;
import com.example.entity.User;
import com.example.service.Games;
import com.example.service.MarketItems;
import com.example.service.Stores;
import com.example.service.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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
        private Text storenametext;
        @FXML
        private Text usernametext ;

        @FXML
        void AddToStore(ActionEvent event) throws IOException {
                if (new Stores().findById(Id.store).getOwnerId().equals(Id.user)) {
                        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddItemForm.fxml"));
                        profilepane.getChildren().setAll(pane);
                }
        }
        @FXML
        void DeleteYourItem(ActionEvent event) throws IOException {
                if (new Stores().findById(Id.store).getOwnerId().equals(Id.user)) {
                        new MarketItems().deleteById(
                                ((MarketItem) tbview.getSelectionModel().getSelectedItem()).getId()
                        );
                        ViewItem();
                }
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
                AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
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
                Store s = new Stores().findById(Id.store);
                User u = new Users().findById(s.getOwnerId());
                storenametext.setText(s.getName());
                usernametext.setText(u.getUsername());
                ViewItem();
        }

        @FXML
        public void ViewItem() {

                List<MarketItem> l = new MarketItems().findAll("`store_id`=" + Id.store);
                tbview.getItems().clear();
                for (MarketItem m : l) {
                        tbview.getItems().add(m);

                }
                tbview.refresh();
        }
        @FXML
        void DeleteYourStore(ActionEvent event)throws IOException {
                if (new Stores().findById(Id.store).getOwnerId().equals(Id.user)) {
                        new MarketItems().deleteByStoreId(Id.store);
                        new Stores().deleteById(Id.store);
                        Store(event);
                }
        }


}




