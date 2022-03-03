package com.example.gzone;

import com.example.entity.Team;
import com.example.service.Teams;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TeamUpdateController implements Initializable {

    private Team t;

    @FXML
    private TextField teamname;

    @FXML
    private TextField photourl;

    @FXML
    private Spinner<Integer> scroller;

    @FXML
    private TextArea descritpion;

    @FXML
    private RadioButton rb1;

    @FXML
    private RadioButton rb2;

    @FXML
    private Button updateteam;

    @FXML
    private Label nameid;
    @FXML
    private Label nameee;

    @FXML
    void Update(ActionEvent event){
        Teams teams = new Teams();

        t.setAdminId(2);
        t.setName(teamname.getText());
        t.setPhotoURL(photourl.getText());

        t.setTeamSize(scroller.getValue());
        t.setDescription(descritpion.getText());

        if (rb1.isSelected()) {
            t.setRequestable(true);
        } else {
            t.setRequestable(false);

        }

        if (rb2.isSelected()) {
            t.setInvitable(true);
        } else {
            t.setInvitable(false);

        }

        teams.modify(t);
    }


  public Integer idd ;
        public Integer setidd(){
            return idd ;
    }
    public void getidd(Integer id){
            this.idd=id;

    }



    public void iddd(Integer iddd) {
        nameid.setText(String.valueOf(idd));
    }
    public String textname;

    public String setnamee(){
        return textname ;
    }
    public void getnamee(String textname){
        this.textname= textname;

    }
    public void teammm(String name){
        nameee.setText(textname);

    }


    public void initialize(){




    }
    Integer i = setidd();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Teams teams = new Teams();
        t = teams.findById(4);
        teamname.setText(t.getName());
        photourl.setText(t.getPhotoURL());
        descritpion.setText(t.getDescription());


System.out.println(i);


        if(t.isRequestable() == true){
            rb1.fire();
        }

        if(t.isInvitable() == true){
            rb2.fire();
        }


        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8, 1);
        valueFactory.setValue(t.getTeamSize());
        scroller.setValueFactory(valueFactory);

    }
}
