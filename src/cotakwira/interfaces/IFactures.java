package cotakwira.interfaces;

import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IFactures<T> {
    public void ajouterFacture(T f);
    public void modifierFacture(T f);
    public void supprimerFacture(T f);
    public ResultSet printpdffacture() throws SQLException;
    public void ajouterFacture2();
    public void draftEmail();
    public void sendit();
    public void apiex();
    public void setupServerProperties();
    public List<T> FactureList();
    public ObservableList<T> getDatausers();
    public void supprimerFacture();
}

