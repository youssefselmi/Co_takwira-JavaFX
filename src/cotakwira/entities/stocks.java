package cotakwira.entities;

public class stocks {
    private int ID_Produit;
    private String Nom_Produit;
    private int Quantite;
    private float Prix_Unite;
    public stocks()
    {

    }

    public stocks(int ID_Produit, String Nom_Produit,int Quantite,float Prix_unite)
    {this.ID_Produit=ID_Produit;
    this.Nom_Produit=Nom_Produit;
    this.Prix_Unite=Prix_unite;
    this.Quantite=Quantite;

    }
    public stocks(String Nom_Produit,int Quantite,float Prix_unite)
    {

    }
    public int getID_Produit(){
        return ID_Produit;
    }
    public String getNom_Produit(){
        return Nom_Produit;
    }
    public int getQuantite(){
        return Quantite;
    }
    public float getPrix_Unite(){
        return Prix_Unite;
    }
    public void setID_Produit(int ID_Produit)
    {
        this.ID_Produit=ID_Produit;
    }
    public void setNom_Produit(String Nom_Produit)
    {
        this.Nom_Produit=Nom_Produit;
    }
    public void setQuantite(int Quantite)
    {
        this.Quantite=Quantite;
    }
    public void setPrix_Unite(float Prix_unite)
    {
        this.Prix_Unite=Prix_unite;
    }

    @Override
    public String toString() {
        return "stocks{" +
                "ID_Produit=" + ID_Produit +
                ", Nom_Produit='" + Nom_Produit + '\'' +
                ", Quantite=" + Quantite +
                ", Prix_Unite=" + Prix_Unite +
                '}';
    }
}
