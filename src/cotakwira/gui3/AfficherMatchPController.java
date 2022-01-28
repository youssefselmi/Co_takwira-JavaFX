/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui3;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Robot;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.print.attribute.PrintRequestAttributeSet;
import sun.awt.windows.WToolkit;
import cotakwira.services.MatchCrud;
import cotakwira.entities.Match;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import cotakwira.services.VoteCrud;
import cotakwira.services.mail;
import cotakwira.entities.Pdf;
import cotakwira.entities.Vote;
import cotakwira.gui.LoginController;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class AfficherMatchPController implements Initializable {

    @FXML
    private VBox pnl_scroll;
    int id_m;
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
    @FXML
    private JFXButton actualiser;
    @FXML
    private Pane InvPane = new Pane();
    @FXML
    private JFXButton print;
    @FXML
    private FontAwesomeIconView font;
    @FXML
    private FontAwesomeIconView sendEmail;
    @FXML
    private Text hoursTimer;
    @FXML
    private Text MinuteTimer;
    @FXML
    private Text SecondTimer;
    @FXML
    private AnchorPane timerPanel;
    @FXML
    private AnchorPane menuPanel;
    /* private JFXComboBox<Integer> HoursInput;*/
 /* private JFXComboBox<Integer> MinuteInput;
    private JFXComboBox<Integer> SecondInput;*/
    @FXML
    private AnchorPane root;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button startBtn;

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private Text hour;
    @FXML
    private Label HL_input;
    @FXML
    private JFXButton email;
    @FXML
    private Label ML_input;
    @FXML
    private Label SL_input;
    @FXML
    private Label labjj;
    @FXML
    private Label labmj;
    @FXML
    private Label labaj;
    @FXML
    private JFXButton BtnMatchs;
    @FXML
    private JFXButton BtnStade;
    @FXML
    private JFXButton BtnStat;
    @FXML
    private Label score1;
    @FXML
    private Label labjj2;
    @FXML
    private Label score2;
    @FXML
    private Label vote;
    @FXML
    private Label status;
    @FXML
    private StackPane rootM;
    int id_org = LoginController.idLogin;
    String role = LoginController.role;
    double xOffset, yOffset;

    public void setTextField(int d) {
        id_m = d;
    }

    /**
     * Initializes the controller class.
     */
    Map<Integer, String> numbermap;
    Integer currSeconds;
    Thread thrd;

    void scrollUp() {
        TranslateTransition trl = new TranslateTransition();
        trl.setDuration(Duration.millis(100));
        trl.setToX(0);
        trl.setToY(-200);

        trl.setNode(menuPanel);

        TranslateTransition tr2 = new TranslateTransition();
        tr2.setDuration(Duration.millis(100));
        trl.setToX(0);
        trl.setToY(200);

        tr2.setToX(0);
        tr2.setToY(0);
        tr2.setNode(timerPanel);
        ParallelTransition pt = new ParallelTransition(trl, tr2);

        pt.setOnFinished(e -> {
            try {
                //  System.out.println("start countdown ........");
                startCountDown();
            } catch (Exception e2) {
                e2.getMessage();
            }
        });

        pt.play();

    }

    void scrollDown() {
        TranslateTransition trl = new TranslateTransition();
        trl.setDuration(Duration.millis(100));
        trl.setToX(0);
        trl.setToY(200);
        trl.setNode(timerPanel);

        TranslateTransition tr2 = new TranslateTransition();
        tr2.setDuration(Duration.millis(100));
        tr2.setFromX(0);
        tr2.setFromY(-200);
        tr2.setToX(0);
        tr2.setToY(0);
        tr2.setNode(menuPanel);
        ParallelTransition pt = new ParallelTransition(trl, tr2);
        pt.play();
    }

    Integer hmsToSeconds(Integer h, Integer m, Integer s) {
        Integer hToSeconds = h * 3600;
        Integer mToseconds = m * 60;
        Integer total = hToSeconds + mToseconds + s;
        return total;

    }

    LinkedList<Integer> secondsToHms(Integer currSecond) {
        Integer hours = currSeconds / 3600;
        currSecond = currSecond % 3600;
        Integer minutes = currSecond / 60;
        currSecond = currSecond % 60;
        Integer seconds = currSecond;
        LinkedList<Integer> answer = new LinkedList<>();
        answer.add(hours);
        answer.add(minutes);
        answer.add(seconds);
        return answer;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        print.setVisible(true);
        ActionEvent event = new ActionEvent();
        ActualiserInformations(event);
        /*MatchCrud mc = new MatchCrud();
int nbj= mc.NbJoursRestants(id_m);
int c = nbj *24;
if(c==0)
{
    c=60;
}*/

        numbermap = new TreeMap<Integer, String>();
        for (Integer i = 0; i <= 1000; i++) {
            if (0 <= i && i <= 9) {
                numbermap.put(i, "0" + i.toString());
            } else {
                numbermap.put(i, i.toString());
            }

        }

    }

    @FXML
    private void ActualiserInformations(ActionEvent event) {

        try {
            // System.out.println("in" + id_m);
            MatchCrud mc = new MatchCrud();
            System.out.println(id_m);
            Match m = new Match();
            m = mc.getMatchParId(id_m);

            // System.out.println("matchp" + id_m);
            nomEq1.setText(mc.getNomEquipe(m.getId_equipe1()));
            nomEq2.setText(mc.getNomEquipe(m.getId_equipe2()));
            spect.setText(String.valueOf(m.getNb_spectateur()));
            stade.setText(mc.getNomStade(m.getId_stade()));
            desc.setText(m.getDescription());
            Hdeb.setText(m.getHeure_deb());
            Hfin.setText(m.getHeure_fin());

            Vote v = new Vote();
            VoteCrud vc = new VoteCrud();
            float t = vc.CalculerVote(id_m);
            vote.setText(String.valueOf(t));
            /**
             * ************************************************
             */
            int test = m.getDate().getMonth();
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

            labJ.setText(String.valueOf(m.getDate().getDate()));
            labM.setText(month);
            labA.setText(String.valueOf(m.getDate().getYear() + 1900));

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

            }
            System.out.println(String.valueOf(d1.getDate()));
            labjj.setText(String.valueOf(d1.getDate()));
            labmj.setText(month2);
            System.out.println("labbbbb" + test2);
            labaj.setText(String.valueOf(d1.getYear() + 1900));

            Date date = new Date();
            String hsys;
            String strDateFormat = "HH:mm:ss";
            SimpleDateFormat sdf_c = new SimpleDateFormat(strDateFormat);
            hsys = sdf_c.format(date);
            Date deb = sdf.parse(hsys);
            Date fin = sdf.parse(m.getHeure_deb());
            System.out.println("hsys" + deb.getHours());
            int T[] = new int[3];

            T = mc.calculateTime(m.getDate(), deb, fin, id_m);

            int h = T[0];
            int min = T[1];
            if (h < 0) {
                h = 0;
                min = 0;
            }

            HL_input.setText(String.valueOf(h));
            SL_input.setText(String.valueOf(0));
            ML_input.setText(String.valueOf(min));
            List<String> l = new ArrayList();
            String st = m.getScore();
            for (String s : st.split("-")) {
                l.add(s);
                System.out.println(s);
            }

            score1.setText(l.get(0));
            score2.setText(l.get(1));

            status.setText("Match" + " " + m.getStatus());

        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @FXML
    private void ImprimerInvitation(ActionEvent event) {
        //  printA(InvPane);
        MatchCrud mc = new MatchCrud();
        Match m = new Match();
        m = mc.getMatchParId(id_m);
        String nom = mc.getNomEquipe(m.getId_equipe1());
        String nom2 = mc.getNomEquipe(m.getId_equipe1());
        String Hdeb = m.getHeure_deb();
        String Hfin = m.getHeure_deb();
        String nomStade = mc.getNomStade(m.getId_stade());
        Pdf p = new Pdf();
        try {
            p.add("match.pdf", nom, nom2, Hdeb, Hfin, nomStade);
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (SQLException ex) {

            ex.getMessage();
        } catch (DocumentException ex) {
            ex.getMessage();
        }
    }

    @FXML
    private void start(ActionEvent event) {
        // currSeconds = hmsToSeconds(HourInput.getValue(), MinuteInput.getValue(), SecondInput.getValue());
        currSeconds = hmsToSeconds(Integer.parseInt(HL_input.getText()), Integer.parseInt(ML_input.getText()), Integer.parseInt(SL_input.getText()));
        /* HourInput.setValue(0);
        MinuteInput.setValue(0);
        SecondInput.setValue(0);*/
        HL_input.setText(String.valueOf(0));
        ML_input.setText(String.valueOf(0));
        SL_input.setText(String.valueOf(0));
        scrollUp();
    }

    @FXML
    private void unstart(ActionEvent event) {
        thrd.stop();
        scrollDown();
    }

    void startCountDown() {
        thrd = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {

                        setOutput();
                        Thread.sleep(1000);

                        if (currSeconds == 0) {
                            System.out.println("Finished ......");
                            scrollDown();
                            thrd.stop();

                        }
                        currSeconds -= 1;
                        //Thread.sleep(1000);
                    }
                } catch (Exception ex) {

                }
            }
        });
        thrd.start();
    }

    void setOutput() {
        LinkedList<Integer> currHms = secondsToHms(currSeconds);
        //System.out.println(currHms.get(0)+ "-"+ currHms.get(1)+ "-"+ currHms.get(2));
        hoursTimer.setText(numbermap.get(currHms.get(0)));
        MinuteTimer.setText(numbermap.get(currHms.get(1)));
        // MinuteTimer.setText(numbermap.get(2));
        SecondTimer.setText(numbermap.get(currHms.get(2)));
        //SecondTimer.setText(numbermap.get(2));
        // HTimer1.setText(numbermap.get(currHms.get(0)));

    }

    @FXML
    private void sendEmail(ActionEvent event) {
        mail mc = new mail();
        //

        Label head = new Label("Envoyer Votre Email");
        head.setAlignment(Pos.CENTER);
        head.setStyle("-fx-text-fill:#3a1c9d ;"
                + "-fx-font-size:18px;"
                + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);");
        JFXDialogLayout dialogContent = new JFXDialogLayout();
        dialogContent.setHeading(head);
        VBox vb;
        HBox hb;
        JFXTextArea text = new JFXTextArea();
        JFXTextField jf = new JFXTextField();

        Label r = new Label("Email");
        text.setStyle("-fx-text-fill:#aeacac ;"
                + "-fx-font-size:18px;");
        // + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);");
        jf.setStyle("-fx-text-fill:#aeacac ;"
                + "-fx-font-size:18px;");
        // + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);");

        r.setStyle(
                "-fx-text-fill:#3a1c9d ;"
                + "-fx-font-size:18px;"
                + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);"
        );
        Label rv = new Label();
        Label r2 = new Label("Message");
        Label rv2 = new Label("");
        r2.setStyle(
                "-fx-text-fill:#3a1c9d ;"
                + "-fx-font-size:18px;"
                + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);"
        );

        hb = new HBox(r);
        HBox hb2 = new HBox(r2);
        HBox hb3 = new HBox(text);
        HBox hb4 = new HBox(jf);
        hb4.setAlignment(Pos.CENTER);
        hb3.setAlignment(Pos.CENTER);
        vb = new VBox(hb, jf, rv, hb2, hb3, rv2);
        vb.setAlignment(Pos.CENTER);
        dialogContent.setBody(vb);

        dialogContent.getStyleClass().add("jfx-dialog-overlay-pane");
        JFXButton close = new JFXButton("Annuler");
        JFXButton envoyer = new JFXButton("Envoyer");
        close.getStyleClass().add("JFXButton");
        envoyer.getStyleClass().add("JFXButton");

        close.getStyleClass().add("JFXButton");
        close.setStyle(
                "-fx-text-fill:#3a1c9d ;"
                + "-fx-font-size:18px;"
                + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);"
                + "-fx-background-radius: 40;-fx-background-color:#02aab0  "
        );

        envoyer.getStyleClass().add("JFXButton");
        envoyer.setStyle(
                "-fx-text-fill:#3a1c9d ;"
                + "-fx-font-size:18px;"
                + "-fx-effect: dropshadow(three-pass-box, derive(grey, 50%), 2, 0.2, 0, 0);"
                + "-fx-background-radius: 40;-fx-background-color:#02aab0  "
        );
        dialogContent.setActions(envoyer, new Label("   "), close);
        JFXDialog dialog = new JFXDialog(rootM, dialogContent, JFXDialog.DialogTransition.CENTER);
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent __) {
                dialog.close();
            }
        });
        envoyer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int test = 0;
                String email = jf.getText();
                String msg = text.getText();
                if (email.trim().length() > 0 && ((msg.trim().length() > 0))) {
                    //   if(email)
                    long nb = mc.verifier(email);
                    System.out.println("nb" + nb);
                    if (nb == 200 || nb == 207 || nb == 215) {
                        test = 1;
                        rv.setText("Email valide");
                        rv.setStyle(
                                "-fx-text-fill:#008000 ;"
                                + "-fx-font-size:18px;"
                        );

                    }

                    if (test == 1) {
                        mc.sendInvitationParMail(msg, email);
                        mc.notificationS("Envoyer votre Email", "Email envoyé avec succée");
                    } else {
                        rv.setText("Email non valide");
                        rv.setStyle(
                                "-fx-text-fill:#FF0000 ;"
                                + "-fx-font-size:18px;"
                        );
                        mc.notificationF("Envoyer votre Email", " Email non envoyé !!");
                    }
                } else {
                    System.out.println("chaine vide");
                }

            }

        });
        dialog.show();

    }

    @FXML
    private void OnClickBtnConsulterMatchs(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherMatchLP.fxml"));
            Parent root;

            root = loader.load();
            Scene newScene = new Scene(root);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.show();
        } catch (IOException ex) {
            ex.getMessage();

        }
    }

    @FXML
    private void OnClickBtnConsulterStade(ActionEvent event) {
    }

    @FXML
    private void OnClickBtnStatMatch(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("statMatch.fxml"));
            Parent root;

            root = loader.load();
            Scene newScene = new Scene(root);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.show();
        } catch (IOException ex) {
            ex.getMessage();

        }
    }

    @FXML
    private void deconnexion(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/cotakwira/gui/Login.fxml"));
            Scene newScene = new Scene(root);
            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    window.setX(event.getScreenX() - xOffset);
                    window.setY(event.getScreenY() - yOffset);
                }
            });

            window.setScene(newScene);
            // window.initStyle(StageStyle.TRANSPARENT);
            window.show();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void back(ActionEvent event) {
        if (role.equals("organisateur")) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/cotakwira/gui3/AfficherMatchLP.fxml"));
                Scene newScene = new Scene(root);
                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    }
                });

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        window.setX(event.getScreenX() - xOffset);
                        window.setY(event.getScreenY() - yOffset);
                    }
                });
                //Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(newScene);
                window.show();
            } catch (IOException ex) {
                ex.getMessage();
            }
        }

        if (role.equals("user")) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/cotakwira/gui3/AfficherMatchLT.fxml"));
                Scene newScene = new Scene(root);
                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    }
                });

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        window.setX(event.getScreenX() - xOffset);
                        window.setY(event.getScreenY() - yOffset);
                    }
                });
                //Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(newScene);
                window.show();
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
    }

}
