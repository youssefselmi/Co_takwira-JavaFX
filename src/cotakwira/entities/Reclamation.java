/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.entities;

import java.sql.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author yassin
 */
public class Reclamation {

    private int idRec, idJoueur;
    private String SujetRec, DescriptionRec, StatusRec, PrioriteRec;
    private String NomJoueur, PrenomJoueur, EmailJoueur, TlJoueur, NomPrenomCoach, imgRec;
    private Date DateRec, DateTraitement;
    private ImageView imgReclamation;

    public Reclamation() {
    }

    public Reclamation(int idRec, int idJoueur, String SujetRec, String DescriptionRec, String NomPrenomCoach, ImageView imgReclamation) { //Ajout
        this.idRec = idRec;
        this.idJoueur = idJoueur;
        this.SujetRec = SujetRec;
        this.DescriptionRec = DescriptionRec;
        //this.PrioriteRec = PrioriteRec;
        //this.StatusRec = StatusRec;
        //this.DateRec = DateRec;
        //this.DateTraitement = DateTraitement;
        this.NomPrenomCoach = NomPrenomCoach;
        this.imgReclamation = imgReclamation;
    }

    public Reclamation(int idRec, int idJoueur, String SujetRec, String DescriptionRec, String NomPrenomCoach, String imgRec) { //Ajout
        this.idRec = idRec;
        this.idJoueur = idJoueur;
        this.SujetRec = SujetRec;
        this.DescriptionRec = DescriptionRec;
        //this.PrioriteRec = PrioriteRec;
        //this.StatusRec = StatusRec;
        //this.DateRec = DateRec;
        //this.DateTraitement = DateTraitement;
        this.NomPrenomCoach = NomPrenomCoach;
        this.imgRec = imgRec;
    }

    public ImageView getImgReclamation() {
        return imgReclamation;
    }

    public void setImgReclamation(ImageView imgReclamation) {
        this.imgReclamation = imgReclamation;
    }

    public Reclamation(int idRec, int idJoueur, String SujetRec, String DescriptionRec, String imgRec)//lupdate sans coach
    {
        this.idRec = idRec;
        this.idJoueur = idJoueur;
        this.SujetRec = SujetRec;
        this.DescriptionRec = DescriptionRec;
        this.imgRec = imgRec;
    }

    public Reclamation(String SujetRec, String DescriptionRec, String NomPrenomCoach, int idRec, String imgRec)//lupdate avec coach
    {
        this.idRec = idRec;
        this.SujetRec = SujetRec;
        this.DescriptionRec = DescriptionRec;
        this.NomPrenomCoach = NomPrenomCoach;
        this.imgRec = imgRec;
    }

    public Reclamation(int idRec, int idJoueur, String SujetRec, String DescriptionRec)//lupdate sans coach
    {
        this.idRec = idRec;
        this.idJoueur = idJoueur;
        this.SujetRec = SujetRec;
        this.DescriptionRec = DescriptionRec;
        this.imgRec = imgRec;
    }
    
        public Reclamation(int idRec,String StatusRec ) //lupdate Statut Pour interface Admin
    {
        this.idRec = idRec;
        this.StatusRec = StatusRec;
    }

    public Reclamation(String SujetRec, String DescriptionRec, String NomPrenomCoach, int idRec)//lupdate avec coach
    {
        this.idRec = idRec;
        this.SujetRec = SujetRec;
        this.DescriptionRec = DescriptionRec;
        this.NomPrenomCoach = NomPrenomCoach;
        this.imgRec = imgRec;
    }

    public Reclamation(int idRec)//lel Suppression
    {
        this.idRec = idRec;
    }
//    public Reclamation(int idRec,String SujetRec,String DescriptionRec)//update
//    {
//     this.idRec = idRec; 
//     this.SujetRec = SujetRec;
//     this.DescriptionRec = DescriptionRec;
//    }
//    public Reclamation(int idRec,String SujetRec,String DescriptionRec,String NomPrenomCoach)//update coach
//    {
//     this.idRec = idRec; 
//     this.SujetRec = SujetRec;
//     this.DescriptionRec = DescriptionRec;
//     this.NomPrenomCoach = NomPrenomCoach;
//    }

    public int getIdRec() {
        return idRec;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public String getSujetRec() {
        return SujetRec;
    }

    public String getDescriptionRec() {
        return DescriptionRec;
    }

    public String getPrioriteRec() {
        return PrioriteRec;
    }

    public String getStatusRec() {
        return StatusRec;
    }

    public Date getDateRec() {
        return DateRec;
    }

    public Date getDateTraitement() {
        return DateTraitement;
    }

    public String getNomPrenomCoach() {
        return NomPrenomCoach;
    }

    public String getImgRec() {
        return imgRec;
    }

    public void setImgRec(String imgRec) {
        this.imgRec = imgRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public void setSujetRec(String SujetRec) {
        this.SujetRec = SujetRec;
    }

    public void setDescriptionRec(String DescriptionRec) {
        this.DescriptionRec = DescriptionRec;
    }

    public void setPrioriteRec(String PrioriteRec) {
        this.PrioriteRec = PrioriteRec;
    }

    public void setStatusRec(String StatusRec) {
        this.StatusRec = StatusRec;
    }

    public void setDateRec(Date DateRec) {
        this.DateRec = DateRec;
    }

    public void setDateTraitement(Date DateTraitement) {
        this.DateTraitement = DateTraitement;
    }

    public void setNomPrenomCoach(String NomPrenomCoach) {
        this.NomPrenomCoach = NomPrenomCoach;
    }

    public void setNomJoueur(String NomJoueur) {
        this.NomJoueur = NomJoueur;
    }

    public void setPrenomJoueur(String PrenomJoueur) {
        this.PrenomJoueur = PrenomJoueur;
    }

    public void setEmailJoueur(String EmailJoueur) {
        this.EmailJoueur = EmailJoueur;
    }

    public void setTlJoueur(String TlJoueur) {
        this.TlJoueur = TlJoueur;
    }

//    @Override
//    public String toString()                                                                                                                                                                                                          
//    {
//     return "Reclamation{"+"idRec="+idRec+",idJoueur="+idJoueur+",SujetRec="+SujetRec+",DescriptionRec="+DescriptionRec+",PrioriteRec="+PrioriteRec+",StatusRec="+StatusRec+",DateRec="+DateRec+",DateTraitement="+DateTraitement+",idCoach="+idCoach+",NomJoueur="+NomJoueur+",PrenomJoueur="+PrenomJoueur+",EmailJoueur="+EmailJoueur+",TlJoueur="+TlJoueur+'}';    
//    }
    @Override
    public String toString() {
        return "Reclamation{" + "idRec=" + idRec + ",SujetRec=" + SujetRec + ",DescriptionRec=" + DescriptionRec + ",StatusRec=" + StatusRec + ",DateRec=" + DateRec + ",NomPrenomCoach=" + NomPrenomCoach + '}';
    }

}
