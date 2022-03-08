package com.example.gzone;

import com.example.entity.PostReport;
import com.example.entity.Report;
import com.example.entity.StoreReport;
import com.example.entity.Subject;
import com.example.entity.TournamentReport;
import com.example.service.PostReports;
import com.example.service.Reports;
import com.example.service.StoreReports;
import com.example.service.TournamentReports;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class ReportController implements Initializable {

    private Integer id;
    private Integer reporterId;
    private Date reportDate;
    private Subject subject;
    @FXML
    private TextArea content;
    @FXML
    private Label control;
    @FXML
    private TextField title;
    @FXML
    private Button submit;
    @FXML
    private RadioButton sensitivebtn;
    @FXML
    private RadioButton manipulationbtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        control.setVisible(false);
    }

    @FXML
    private void Report(ActionEvent event) {
        Reports rep = new Reports();
        if (sensitivebtn.isSelected()) {
            subject = Subject.sensitive;
        } else {
            subject = Subject.score_manipulation;
        }
        if ((title.getText() == null || title.getText().isBlank())
                || (content.getText() == null || content.getText().isBlank())) {
            control.setVisible(true);
        } else {
            rep.insert(new Report(
                    null,
                    Id.user,
                    subject,
                    title.getText(),
                    content.getText(),
                    new java.util.Date()
            ));
            
            Id.report = rep.findAll("`head` REGEXP '" + title.getText() + "'").get(0).getId();
            
            TournamentReports tr = new TournamentReports();
            PostReports pr = new PostReports();
            StoreReports sr = new StoreReports();
            
            if (Id.type==0){
            tr.insert(new TournamentReport(null,Id.tournament,Id.report));
            }
            else if (Id.type==1){
            pr.insert(new PostReport(null, Id.post, Id.report));
            }else if (Id.type==2){
            sr.insert(new StoreReport(null, Id.store, Id.report));
            }
            
        }
    }

}
