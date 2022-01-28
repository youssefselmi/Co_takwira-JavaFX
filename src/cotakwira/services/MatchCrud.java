/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.services;

/*import crud.pidev.entities.Match;
import crud.pidev.interfaces.IMatch;
import crud.pidev.tools.Connection;*/
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Collections.list;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import cotakwira.entities.Equipe;
import cotakwira.entities.Match;
import cotakwira.entities.Stade;
import cotakwira.interfaces.IMatch;
import cotakwira.tools.Connection;

/**
 *
 * @author esprit
 */
public class MatchCrud implements IMatch<Match> {

    int indice;

    @Override
    public void ajouterMatch(Match t) {

        try {

            String requete = "INSERT INTO `match`(`id_match`, `id_stade`, `id_equipe1`, `id_equipe2`, `id_org` ,`date`, `heure_deb`, `heure_fin`, `nb_spectateur`, `description`, `status`,`score`) "
                    + " VALUES (?, ?, ?,?, ?,?,?,?,?,?,?,?)";

            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);

            java.sql.Date sqlDate = new java.sql.Date(t.getDate().getTime());

            pst.setInt(1, t.getId_match());
            pst.setInt(2, t.getId_stade());
            pst.setInt(3, t.getId_equipe1());
            pst.setInt(4, t.getId_equipe2());
            pst.setInt(5, t.getId_org());
            pst.setDate(6, sqlDate);
            pst.setString(7, t.getHeure_deb());
            pst.setString(8, t.getHeure_fin());
            pst.setInt(9, t.getNb_spectateur());
            pst.setString(10, t.getDescription());
            pst.setString(11, t.getStatus());
            pst.setString(12, t.getScore());

            pst.executeUpdate();
            System.out.println("Match ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimerMatch(Match t) {
        try {
            String requete = "DELETE FROM `match` where id_stade=?";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, t.getId_stade());
            pst.executeUpdate();
            System.out.println("Match supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimerMatch2(int id) {
        try {
            String requete = "DELETE FROM `match` where id_match=" + id;
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            // pst.setInt(1, t.getIdStade());
            pst.executeUpdate();
            System.out.println("Match supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void updateStatus(String s, int idd) {
        try {

            String requete = "  UPDATE `match` SET `status`=? WHERE id_match=?";

            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);

            pst.setString(1, s);

            pst.setInt(2, idd);
            pst.executeUpdate();
            System.out.println("status modifiée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateScore(String s, int idd) {
        try {

            String requete = "  UPDATE `match` SET `score`=? WHERE id_match=?";

            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);

            pst.setString(1, s);

            pst.setInt(2, idd);
            pst.executeUpdate();
            System.out.println("score modifiée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateMatch(Match t) {
        try {

            String requete = "  UPDATE `match` SET `id_stade`=?,`id_equipe1`=?,`id_equipe2`=?,`date`=?,`heure_deb`=?,`heure_fin`=?,`nb_spectateur`=?,`description`=?,`status`=?  WHERE id_match=?";

            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            java.sql.Date sqlDate = new java.sql.Date(t.getDate().getTime());
            pst.setInt(1, t.getId_stade());
            pst.setInt(2, t.getId_equipe1());
            pst.setInt(3, t.getId_equipe2());

            pst.setDate(4, sqlDate);
            pst.setString(5, t.getHeure_deb());
            pst.setString(6, t.getHeure_fin());
            pst.setInt(7, t.getNb_spectateur());

            pst.setString(8, t.getDescription());
            pst.setString(9, t.getStatus());

            pst.setInt(10, t.getId_match());
            pst.executeUpdate();
            System.out.println("Match modifiée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //  @Override
    public List<Match> displayMatchs(int id) {
        List<Match> ListMatchs = new ArrayList<>();
        try {

            String requete = "SELECT * FROM `match` WHERE id_org=" + id;
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Match p = new Match();

                p.setId_match(rs.getInt("id_match"));
                p.setId_equipe1(rs.getInt("id_equipe1"));
                p.setId_equipe2(rs.getInt("id_equipe2"));
                p.setId_stade(rs.getInt("id_stade"));
                p.setDate(rs.getDate("date"));
                p.setHeure_deb(rs.getString("heure_deb"));
                p.setHeure_fin(rs.getString("heure_fin"));

                p.setDescription(rs.getString("description"));
                p.setNb_spectateur(rs.getInt("nb_spectateur"));
                p.setStatus(rs.getString("status"));
                p.setScore(rs.getString("score"));
                ListMatchs.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListMatchs;
    }

    public void setIndice(int i) {
        indice = i;
    }

    public int getIndice() {
        return indice;
    }

    public int getNbMatchs() {
        int total = 0;

        String requete = "select count(*) from `match` ";

        Statement st;
        try {
            st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                String chaine = String.valueOf(rs.getString(1));
                total = Integer.parseInt(chaine);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
    }

    public int getNbMatchsTermine(int idd) {
        int total = 0;

        String requete = "select count(*) from `match`WHERE id_org='" + idd + "'" + " and  status= '" + "Termine" + "'";

        Statement st;
        try {
            st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                String chaine = String.valueOf(rs.getString(1));
                total = Integer.parseInt(chaine);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
    }

    public int getNbMatchsProgramme(int idd) {
        int total = 0;

        String requete = "select count(*) from `match`WHERE id_org='" + idd + "'" + " and  status= '" + "En Programme" + "'";

        Statement st;
        try {
            st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                String chaine = String.valueOf(rs.getString(1));
                total = Integer.parseInt(chaine);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
    }

    public int getNbMatchsEnCours(int idd) {
        int total = 0;

        String requete = "select count(*) from `match`WHERE id_org='" + idd + "'" + " and  status= '" + "En Cours" + "'";

        Statement st;
        try {
            st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                String chaine = String.valueOf(rs.getString(1));
                total = Integer.parseInt(chaine);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
    }

    public int getNbMatchsVote() {
        int total = 0;

        String requete = "select count(*) from `match`WHERE status= '" + "Termine" + "'";

        Statement st;
        try {
            st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                String chaine = String.valueOf(rs.getString(1));
                total = Integer.parseInt(chaine);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
    }

    public int getNbMatchOrg(int idd) {

        int total = 0;

        String requete = "select count(*) from `match`WHERE id_org= " + idd;

        Statement st;
        try {
            st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                String chaine = String.valueOf(rs.getString(1));
                total = Integer.parseInt(chaine);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
    }

    public int getNbMatchAvril(int idd) {

        int total = 0;
        int a = 0;
  
        String r = "select * from `match`WHERE id_org= " + idd;

        Statement st2;
        try {
            List<Integer> t = new ArrayList<>();
            st2 = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st2.executeQuery(r);
            while (rs.next()) {

                int d = rs.getDate("date").getMonth()+1;
                if (d == 4) {
                    t.add(d);
                    a++;

                }

            }
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    
        return a;
    }

    public int getNbMatchMars(int idd) {

       int total = 0;
        int a = 0;
  
        String r = "select * from `match`WHERE id_org= " + idd;

        Statement st2;
        try {
            List<Integer> t = new ArrayList<>();
            st2 = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st2.executeQuery(r);
            while (rs.next()) {

                int d = rs.getDate("date").getMonth()+1;
                if (d == 3) {
                    t.add(d);
                    a++;

                }

            }
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    
        return a;
        
    }

    public int getNbMatchFev(int idd) {

         int total = 0;
        int a = 0;
  
        String r = "select * from `match`WHERE id_org= " + idd;

        Statement st2;
        try {
            List<Integer> t = new ArrayList<>();
            st2 = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st2.executeQuery(r);
            while (rs.next()) {

                int d = rs.getDate("date").getMonth()+1;
                if (d == 2) {
                    t.add(d);
                    a++;

                }

            }
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    
        return a;
    }

    public int[] getIDMatch(int nbj) {

        int tab[] = new int[nbj];
        int i = 0;

        String requete = "select * from `match` WHERE status= '" + "Termine" + "'";

        try {

            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Match p = new Match();

                tab[i] = rs.getInt("id_match");
                i++;

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tab;
    }
 public ObservableList<Match> getMatchsUser() throws ParseException{
  ObservableList<Match> ListMatchs = FXCollections.observableArrayList();
        try {

            Date d = new Date();
            String requete = "SELECT * FROM `match`";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                int nb;
                String s = rs.getString("status");
                int T[] = new int[2];
                /**
                 * ***********************
                 */
                Date date = new Date();
                String hsys;
                String strDateFormat = "HH:mm:ss";
                SimpleDateFormat sdf_c = new SimpleDateFormat(strDateFormat);
                hsys = sdf_c.format(date);

                Date deb;

                deb = sdf.parse(hsys);
                Date fin = sdf.parse(rs.getString("heure_fin"));

                T = calculateTime(rs.getDate("date"), deb, fin, rs.getInt("id_match"));

                /**
                 * ***************************
                 */
                nb = NbJoursRestants(rs.getInt("id_match"));

                // if (T[0] <= 0 && T[1]==0) {
                if ((T[0] < 0) || (T[0] == 0 && T[1] == 0)) {

                    s = "Termine";
                    updateStatus(s, rs.getInt("id_match"));
                }
                ListMatchs.add(new Match(rs.getInt("id_match"), rs.getInt("id_stade"), rs.getInt("id_org"), rs.getInt("id_equipe1"), rs.getInt("id_equipe2"), rs.getDate("date"), rs.getString("heure_deb"),
                        rs.getString("heure_fin"), rs.getInt("nb_spectateur"), rs.getString("description"), s, rs.getString("score")));

            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
        return ListMatchs;  
}
    public ObservableList<Match> getMatchs(int id) throws ParseException {
        ObservableList<Match> ListMatchs = FXCollections.observableArrayList();
        try {

            Date d = new Date();
            String requete = "SELECT * FROM `match` where id_org =" + id;
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                int nb;
                String s = rs.getString("status");
                int T[] = new int[2];
                /**
                 * ***********************
                 */
                Date date = new Date();
                String hsys;
                String strDateFormat = "HH:mm:ss";
                SimpleDateFormat sdf_c = new SimpleDateFormat(strDateFormat);
                hsys = sdf_c.format(date);

                Date deb;

                deb = sdf.parse(hsys);
                Date fin = sdf.parse(rs.getString("heure_fin"));

                T = calculateTime(rs.getDate("date"), deb, fin, rs.getInt("id_match"));

                /**
                 * ***************************
                 */
                nb = NbJoursRestants(rs.getInt("id_match"));

                // if (T[0] <= 0 && T[1]==0) {
                if ((T[0] < 0) || (T[0] == 0 && T[1] == 0)) {

                    s = "Termine";
                    updateStatus(s, rs.getInt("id_match"));
                }
                ListMatchs.add(new Match(rs.getInt("id_match"), rs.getInt("id_stade"), rs.getInt("id_org"), rs.getInt("id_equipe1"), rs.getInt("id_equipe2"), rs.getDate("date"), rs.getString("heure_deb"),
                        rs.getString("heure_fin"), rs.getInt("nb_spectateur"), rs.getString("description"), s, rs.getString("score")));

            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
        return ListMatchs;
    }

    public List<Match> getListMatchs(int id) {
        List<Match> ListMatchs = new ArrayList();
        try {

            String requete = "SELECT * FROM `match` where id_org=" + id;
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {

                ListMatchs.add(new Match(rs.getInt("id_match"), rs.getInt("id_stade"), rs.getInt("id_equipe1"), rs.getInt("id_equipe2"), rs.getDate("date"), rs.getString("heure_deb"),
                        rs.getString("heure_fin"), rs.getInt("nb_spectateur"), rs.getString("description"), rs.getString("status"), rs.getString("score")));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListMatchs;
    }

    public Match getMatchParId(int idd) {
        Match p = new Match();
        try {

            String requete = "SELECT * FROM `match` WHERE id_match = " + idd;
            Statement st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                p.setId_match(rs.getInt("id_match"));
                p.setId_equipe1(rs.getInt("id_equipe1"));
                p.setId_equipe2(rs.getInt("id_equipe2"));
                p.setId_stade(rs.getInt("id_stade"));
                p.setDate(rs.getDate("date"));
                p.setHeure_deb(rs.getString("heure_deb"));
                p.setHeure_fin(rs.getString("heure_fin"));
                p.setDescription(rs.getString("description"));
                p.setNb_spectateur(rs.getInt("nb_spectateur"));
                p.setStatus(rs.getString("status"));
                p.setScore(rs.getString("score"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;

    }

    public Match getMatchParIdVote(int idd) {
        Match p = new Match();
        try {

            String requete = "SELECT * FROM `match` WHERE  id_match = '" + idd + "'" + " and status= '" + "Termine" + "'";
            Statement st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                p.setId_match(rs.getInt("id_match"));
                p.setId_equipe1(rs.getInt("id_equipe1"));
                p.setId_equipe2(rs.getInt("id_equipe2"));
                p.setId_stade(rs.getInt("id_stade"));
                p.setDate(rs.getDate("date"));
                p.setHeure_deb(rs.getString("heure_deb"));
                p.setHeure_fin(rs.getString("heure_fin"));
                p.setDescription(rs.getString("description"));
                p.setNb_spectateur(rs.getInt("nb_spectateur"));
                p.setStatus(rs.getString("status"));
                p.setScore(rs.getString("score"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;

    }

    public String getNomStade(int idd) {
        String nom;
        Stade p = new Stade();
        try {

            String requete = "SELECT * FROM `stade` WHERE id_stade = " + idd;
            Statement st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                p.setNom_stade(rs.getString("nom_stade"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(MatchCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        nom = p.getNom_stade();
        return nom;

    }

    public String getNomEquipe(int idd) {
        String nom;
        Equipe p = new Equipe();
        try {

            String requete = "SELECT * FROM `equipe` WHERE id_equipe = " + idd;
            Statement st = Connection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                p.setNom_equipe(rs.getString("nom_equipe"));

            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        nom = p.getNom_equipe();
        return nom;

    }

    public void TrieMatchParDate() {

        try {

            String requete = "SELECT * FROM `match` ORDER BY `date`";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                System.out.println("Matchs [ id: " + rs.getInt("id_match") + "Equipe1: " + rs.getInt("id_equipe1") + "Equipe2: " + rs.getInt("id_equipe2") + "Score" + rs.getString("score") + " ID Stade "
                        + rs.getInt("id_stade") + "Date" + rs.getDate("date") + "Duree" + rs.getInt("duree") + "Nb Spectateur" + rs.getInt("nb_spectateur") + rs.getString("status") + "]");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public int NbJoursRestants(int id) {
        int res = 0;
        try {
            java.util.Date date = new Date();

            String requete = "SELECT * FROM `match` WHERE id_match = " + id;
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                java.util.Date dateApres = rs.getDate("date");

                long diff = dateApres.getTime() - date.getTime();

                res = (int) (diff / (1000 * 60 * 60 * 24));
                // System.out.println("id"+id+"nb"+res);

            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return res;
    }

    public int ComparerDate(Date d) {

        java.util.Date date = new Date();
        System.out.println(date);
        long diff = d.getTime() - date.getTime();
        int res = (int) (diff / (1000 * 60 * 60 * 24));
        return res;

    }

    public int SelectEquipe1() {
        int eqp1 = 0;
        try {
            Random rnd = new Random();

            String chaine;
            int max = 0;
            String requete = "select MAX(id_equipe) from `equipe` ";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                chaine = String.valueOf(rs.getString(1));
                max = Integer.parseInt(chaine);
                //System.out.println("max"+max);

            }
            while (eqp1 == 0) {
                eqp1 = 1 + rnd.nextInt(max);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return eqp1;
    }

    public int SelectEquipe2() {
        int eqp2 = 0;
        int eqp1;
        try {
            Random rnd = new Random();

            String chaine;
            int max = 0;
            String requete = "select MAX(id_equipe) from `equipe` ";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                chaine = String.valueOf(rs.getString(1));
                max = Integer.parseInt(chaine);

            }
            eqp2 = 1 + rnd.nextInt(max);

            eqp1 = SelectEquipe1();

            while (eqp1 == eqp2) {

                eqp2 = 1 + rnd.nextInt(max);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return eqp2;
    }

    public void ajouterMatchEquipeAleatoire(Match t) {

        try {

            String requete = "INSERT INTO `match`(`id_match`, `id_stade`, `id_equipe1`, `id_equipe2`, `date`, `heure_deb`, `heure_fin`, `nb_spectateur`, `description`, `status`) "
                    + " VALUES (?, ?, ?, ?,?,?,?,?,?,?)";

            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);

            java.sql.Date sqlDate = new java.sql.Date(t.getDate().getTime());

            int id1 = SelectEquipe1();
            int id2 = SelectEquipe2();

            while (id1 == id2) {
                id2 = SelectEquipe2();
            }
            pst.setInt(1, t.getId_match());
            pst.setInt(2, t.getId_stade());
            pst.setInt(3, id1);
            pst.setInt(4, id2);

            pst.setDate(5, sqlDate);
            pst.setString(6, t.getHeure_deb());
            pst.setString(7, t.getHeure_fin());
            pst.setInt(8, t.getNb_spectateur());
            pst.setString(9, t.getDescription());

            pst.executeUpdate();
            System.out.println("Match ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void ComparerDeuxHeures(String h_deb, String h_fin) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            Date deb = sdf.parse(h_deb);
            Date fin = sdf.parse(h_fin);

            int minutes;
            int hours;
            int secondes;
            if (fin.getMinutes() < deb.getMinutes()) {
                minutes = deb.getMinutes() - fin.getMinutes();
                hours = fin.getHours() - deb.getHours() - 1;
            } else {
                minutes = fin.getMinutes() - deb.getMinutes();
                hours = fin.getHours() - deb.getHours();
            }
            System.out.println(hours + ":" + minutes);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int[] calculateTime(Date d, Date deb, Date fin, int id) {
        //  try {
        int tab[] = new int[3];

        int minutes;
        int hours;
        int secondes;

        //int nbj = ComparerDate(d);
        int nbj = NbJoursRestants(id);

        long seconds;
        seconds = nbj * 60 * 60 * 24;

        int h = (int) (TimeUnit.SECONDS.toHours(seconds) - TimeUnit.SECONDS.toHours(TimeUnit.SECONDS.toDays(seconds)));
        int m = (int) (TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toMinutes(TimeUnit.SECONDS.toHours(seconds)));

        //  if (fin.getHours() < deb.getHours()) {
        if ((fin.getHours() < deb.getHours())) {
            hours = (24 - deb.getHours()) + fin.getHours();
            //true

            if (fin.getMinutes() < deb.getMinutes()) {
                //false
                minutes = (60 - deb.getMinutes()) + fin.getMinutes();
                hours = hours - 1;
                //  System.out.println("aaaaaaaa"+hours);

            } else {
                //true
                minutes = fin.getMinutes() - deb.getMinutes();

            }

        } else {
            //false

            hours = fin.getHours() - deb.getHours();
            if (fin.getMinutes() < deb.getMinutes()) {
                //false
                minutes = (60 - deb.getMinutes()) + fin.getMinutes();
                hours = hours - 1;

            } else {
                //true
                minutes = fin.getMinutes() - deb.getMinutes();

            }
        }
        hours += h;
        tab[0] = hours;
        tab[1] = minutes;
        Date df = new Date();
        if ((d.getDate() == df.getDate()) && (deb.getHours() > fin.getHours())) {
            hours = 0;
            tab[0] = 0;
            tab[1] = 0;
        }
        //System.out.println("d"+d.getDate()+"++"+df.getDate());
        return tab;

    }
}
