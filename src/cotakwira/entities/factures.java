package cotakwira.entities;

import java.util.Date;

public class factures {
    private int ID_Facture;
    private String Nom_Facture;
    private float Prix_Facture;
    private String Status;

    public factures()
    {

    }
    public factures(int ID_Facture, String Nom_Facture,float Prix_Facture, String Status)
    {this.ID_Facture=ID_Facture;
        this.Nom_Facture=Nom_Facture;
        this.Prix_Facture=Prix_Facture;
        this.Status=Status;

    }

    public int getID_Facture() {
        return ID_Facture;
    }

    public String getNom_Facture() {
        return Nom_Facture;
    }



    public float getPrix_Facture() {
        return Prix_Facture;
    }

    public String getStatus() {
        return Status;
    }

    public void setID_Facture(int ID_Facture) {
        this.ID_Facture = ID_Facture;
    }

    public void setNom_Facture(String nom_Facture) {
        Nom_Facture = nom_Facture;
    }



    public void setPrix_Facture(float prix_Facture) {
        Prix_Facture = prix_Facture;
    }

    public void setStatus(String status) {
        Status = status;
    }
    @Override
    public String toString() {
        return "factures{" +
                ", ID_Facture=" + ID_Facture +
                "nom_Facture=" + Nom_Facture +
                ", prix_Facture=" + Prix_Facture +
                ", status=" + Status +
                '}';
    }
}
