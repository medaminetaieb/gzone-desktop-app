package com.example.gzone;

import com.example.entity.MarketItem;
import com.example.entity.Store;
import com.example.entity.User;
import com.example.entity.UserLikesDislike;
import com.example.service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    public ListView<MarketItem> tbview;
    @FXML
    public Button viewbutton;

    @FXML
    public Button addbutton;

    @FXML
    public AnchorPane profilepane;
    @FXML
    public Button deletebutton;
    @FXML
    private Text storenametext;
    @FXML
    private Text usernametext;
    @FXML
    private Text dislikeCount;

    @FXML
    private Text likeCount;
    @FXML
    private Button btndislike;

    @FXML
    private Button btnlike;
    @FXML
    private Button report;

    @FXML
    void AddToStore(ActionEvent event) throws IOException {
        if (new Stores().findById(Id.store).getOwnerId().equals(Id.user)) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AddItemForm.fxml"));
            profilepane.getChildren().setAll(pane);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("different user");
            alert.showAndWait();
        }
    }

    @FXML
    void DeleteYourItem(ActionEvent event) throws IOException {
        if (new Stores().findById(Id.store).getOwnerId().equals(Id.user)) {
            new MarketItems().deleteById(
                    ((MarketItem) tbview.getSelectionModel().getSelectedItem()).getId()
            );
            ViewItem();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("different user");
            alert.showAndWait();
        }
    }

    @FXML
    void Forum(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Forumview1.fxml"));
        profilepane.getChildren().setAll(pane);
    }

    @FXML
    void HomePage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        profilepane.getChildren().setAll(pane);
    }

    @FXML
    void Store(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ViewStores.fxml"));
        profilepane.getChildren().setAll(pane);
    }

    @FXML
    void Team(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("team-view.fxml"));
        profilepane.getChildren().setAll(pane);
    }

    @FXML
    void Tournament(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListTournaments.fxml"));
        profilepane.getChildren().setAll(pane);
    }

    @FXML
    void initialize() {
        if (Id.user == new Stores().findById(Id.store).getOwnerId()) {
            report.setVisible(false);
        }
        //  cltitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        // tbview.getColumns().add(cltitle);
        Store s = new Stores().findById(Id.store);
        User u = new Users().findById(s.getOwnerId());
        storenametext.setText(s.getName());
        usernametext.setText(u.getUsername());
        ViewItem();

        btnlike.setText((new UserLikesDislikes().findAll("store_id=" + Id.store + " and user_id=" + Id.user + " and `like`=true").isEmpty()) ? "Like" : "UnLike");
        btndislike.setText((new UserLikesDislikes().findAll("store_id=" + Id.store + " and user_id=" + Id.user + " and `like`=false").isEmpty()) ? "Dislike" : "UnDislike");
        refreshCounts();
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
    void DeleteYourStore(ActionEvent event) throws IOException {
        if (new Stores().findById(Id.store).getOwnerId().equals(Id.user)) {
            new MarketItems().deleteByStoreId(Id.store);
            new Stores().deleteById(Id.store);
            Store(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("different user");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleLike(ActionEvent event) {
        if (new UserLikesDislikes().findAll("store_id=" + Id.store + " and user_id=" + Id.user + " and `like`=true").isEmpty()) {
            new UserLikesDislikes().insert(new UserLikesDislike(null, Id.user, Id.store, null, null, true));
            btnlike.setText("Unlike");
            if (!new UserLikesDislikes().findAll("store_id=" + Id.store + " and user_id=" + Id.user + " and `like`=false").isEmpty()) {
                new UserLikesDislikes().delete("store_id=" + Id.store + " and user_id=" + Id.user + " and `like`=false");
                btndislike.setText("Dislike");
            }
        } else {
            new UserLikesDislikes().delete("store_id=" + Id.store + " and user_id=" + Id.user + " and `like`=true");
            btnlike.setText("Like");
        }
        refreshCounts();
    }

    @FXML
    private void handleDislike(ActionEvent event) {
        if (new UserLikesDislikes().findAll("store_id=" + Id.store + " and user_id=" + Id.user + " and `like`=false").isEmpty()) {
            new UserLikesDislikes().insert(new UserLikesDislike(null, Id.user, Id.store, null, null, false));
            btndislike.setText("UnDislike");
            if (!new UserLikesDislikes().findAll("store_id=" + Id.store + " and user_id=" + Id.user + " and `like`=true").isEmpty()) {
                new UserLikesDislikes().delete("store_id=" + Id.store + " and user_id=" + Id.user + " and `like`=true");
                btnlike.setText("Like");
            }
        } else {
            new UserLikesDislikes().delete("store_id=" + Id.store + " and user_id=" + Id.user + " and `like`=false");
            btndislike.setText("Dislike");
        }
        refreshCounts();
    }

    private void refreshCounts() {
        likeCount.setText("" + new UserLikesDislikes().findAll("store_id=" + Id.store + " and `like`=true").size());
        dislikeCount.setText("" + new UserLikesDislikes().findAll("store_id=" + Id.store + " and `like`=false").size());
    }

    @FXML
    void report(ActionEvent event) throws IOException {
        Id.type = 1;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Report.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newWindow = new Stage();
        newWindow.setTitle("Report User");
        newWindow.setScene(scene);
        newWindow.show();

    }

}
