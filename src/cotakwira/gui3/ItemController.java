/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui3;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Pane;
//import javax.mail.FetchProfile.Item;
import cotakwira.services.MatchCrud;
import cotakwira.entities.Match;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import takiwra.pidev.services.Item;
import cotakwira.services.VoteCrud;
import cotakwira.services.mail;
import cotakwira.entities.MatchTermine;
import cotakwira.entities.Vote;
import cotakwira.gui.LoginController;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class ItemController implements Initializable {

    @FXML
    private Label nomEq1;
    @FXML
    private Label nomEq2;
    @FXML
    private Label spect;
    @FXML
    private Label stade;
    @FXML
    private Label Hdeb;
    @FXML
    private Label desc;
    @FXML
    private Label labJ;
    @FXML
    private Label labM;
    @FXML
    private Label labA;
    @FXML
    private Label Hfin;
    int id_m;
    @FXML
    private JFXButton actualiser;
    @FXML
    private Pane InvPane;
    @FXML
    private Label labjj;
    @FXML
    private Label labmj;
    @FXML
    private Label labaj;
    @FXML
    private ProgressIndicator piQuantity;
    @FXML
    private ProgressBar pbQuantity;

    @FXML
    private JFXButton btnMinus;
    @FXML
    private JFXButton handleBtnPlus1;

    /**
     * Initializes the controller class.
     */
    Item item = new Item();
    @FXML
    private JFXButton btnVote;
    @FXML
    private Label score1;
    @FXML
    private Label labjj2;
    @FXML
    private Label score2;
    @FXML
    private Label vote;
 int id_org = LoginController.idLogin;
 int id_user =id_org;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        double n;
     
        item.setQuantity(0);
        item.quantityProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                pbQuantity.progressProperty().bind(item.quantityProperty());
                piQuantity.progressProperty().bind(item.quantityProperty());
               

            }
        });

     
    }

    public void setTextField(int d) {
        id_m = d;
    }

    @FXML
    private void ActualiserInformations(ActionEvent event) {
        MatchCrud mc = new MatchCrud();
        MatchCrud mtc = new MatchCrud();

        List<Match> mesMatchs = new ArrayList();
        Match match = new Match();
        Match mT = new Match();
       // mT = mtc.getMatchParId(id_m);
        match = mc.getMatchParIdVote(id_m);
        System.out.println("item" + id_m);
        nomEq1.setText(mc.getNomEquipe(match.getId_equipe1()));
        nomEq2.setText(mc.getNomEquipe(match.getId_equipe2()));
        spect.setText(String.valueOf(match.getNb_spectateur()));
        stade.setText(mc.getNomStade(match.getId_stade()));
        desc.setText(match.getDescription());
        Hdeb.setText(match.getHeure_deb());
        Hfin.setText(match.getHeure_fin());
       List<String> l = new ArrayList();
        String st =match.getScore();
         for (String s: st.split("-")) {
             l.add(s);
        System.out.println(s);
      }
         System.out.println(l);
        score1.setText(l.get(0));
        score2.setText(l.get(1));
         
             Vote v  = new Vote();
             VoteCrud vc = new VoteCrud();
           float t=  vc.CalculerVote(id_m);
             vote.setText(String.valueOf(t));

        /**
         * **********************************
         */
        int test = match.getDate().getMonth();
        String month = "";
        switch (test) {
            case 0:
                month = "Jan";
                break;
            case 1:
                month = "Feb";
                break;
            case 2:
                month = "Mars";
                break;
            case 3:
                month = "Avr";
                break;
            case 4:
                month = "Mais";
                break;
            case 5:
                month = "Jui";
                break;
            case 6:
                month = "Juil";
                break;
            case 7:
                month = "Aout";
                break;
            case 8:
                month = "Sept";
                break;
            case 9:
                month = "Oct";
                break;
            case 10:
                month = "Nov";
                break;
            case 11:
                month = "Dec";
                break;
            default:
            // code block
            }

        labJ.setText(String.valueOf(match.getDate().getDate()));
        labM.setText(month);
        labA.setText(String.valueOf(match.getDate().getYear() + 1900));

        Date d1 = new Date();
        int test2 = d1.getMonth();
        String month2 = "";
        switch (test2) {
            case 0:
                month2 = "Jan";
                break;
            case 1:
                month2 = "Feb";
                break;
            case 2:
                month2 = "Mars";
                break;
            case 3:
                month2 = "Avr";
                break;
            case 4:
                month2 = "Mais";
                break;
            case 5:
                month2 = "Jui";
                break;
            case 6:
                month2 = "Juil";
                break;
            case 7:
                month2 = "Aout";
                break;
            case 8:
                month2 = "Sept";
                break;
            case 9:
                month2 = "Oct";
                break;
            case 10:
                month2 = "Nov";
                break;
            case 11:
                month2 = "Dec";
                break;
            default:
            // code block
            }
        labjj.setText(String.valueOf(d1.getDate()));
        labmj.setText(month2);
        labaj.setText(String.valueOf(d1.getYear() + 1900));

    }

    @FXML
    private void handleBtnMinus(ActionEvent event) {
        item.setQuantity(item.getQuantity() - 0.1);

    }

    @FXML
    private void handleBtnPlus(ActionEvent event) {

        item.setQuantity(item.getQuantity() + 0.1);
    }

    @FXML
    private void ValiderVote(ActionEvent event) {
        
        int test;
        mail m = new mail();
                 int value =(int) Math.round((pbQuantity.getProgress())*10);
               //  System.out.println("mmm" + value);
                 VoteCrud vc = new VoteCrud() ;
                 Vote v = new Vote(id_m,id_user,value);
                test= vc.validerVote(id_user, id_m);
                if(test<0)
                {
                  m.notificationF("Voter"," Vous avez voté pour ce match");
                }
                else{
                     vc.ajouterVote(v);
                      m.notificationS("Voter"," Votre vote a été enregistré pour ce match");
                     
                }
    }

}
