/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.gzone;

import com.example.entity.Report;
import com.example.service.PostReports;
import com.example.service.Posts;
import com.example.service.Reports;
import com.example.service.StoreReports;
import com.example.service.Stores;
import com.example.service.TournamentReports;
import com.example.service.Tournaments;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class AdminReportController implements Initializable {

    @FXML
    private TextField title;
    @FXML
    private TextField subject;
    @FXML
    private TextArea content;
    @FXML
    private Button deletereported;
    @FXML
    private Button deletereport;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Report rs = new Reports().findById(Id.report);
        title.setText(rs.getHead());
        subject.setText(rs.getSubject().toString());
        content.setText(rs.getBody());
        System.out.println(Id.report);
    }

    @FXML
    private void deletereported(ActionEvent event) {
        if (Id.report == new TournamentReports().findAll("`report_id`=" + Id.report).get(0).getReportId()) {
            Integer tempId = new TournamentReports().findAll("`report_id`=" + Id.report).get(0).getTournamentId();
            new TournamentReports().delete("`report_id`=" + Id.report);
            new Reports().deleteById(Id.report);
            new Tournaments().deleteById(tempId);
        } else if (Id.report == new StoreReports().findAll("`report_id`=" + Id.report).get(0).getReportId()) {
            Integer tempId = new StoreReports().findAll("`report_id`=" + Id.report).get(0).getStoreId();
            new StoreReports().delete("`report_id`=" + Id.report);
            new Reports().deleteById(Id.report);
            new Stores().deleteById(tempId);
        } else {
            Integer tempId = new PostReports().findAll("`report_is`=" + Id.report).get(0).getPostId();
            new PostReports().delete("`report_id`=" + Id.report);
            new Reports().deleteById(Id.report);
            new Posts().deleteById(tempId);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Report Validated");
            alert.setContentText("Actions were taken !");
            alert.showAndWait();
        }

    }

    @FXML
    private void deletereport(ActionEvent event) {
        new Reports().deleteById(Id.report);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Report removed");
        alert.setContentText("Report was discarded");
        alert.showAndWait();
    }

}