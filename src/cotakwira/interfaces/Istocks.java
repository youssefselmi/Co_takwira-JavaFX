package cotakwira.interfaces;

import javafx.collections.ObservableList;
import cotakwira.entities.stocks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Istocks<T> {
    public void ajouterStocks(T p);
    public void modifierStocks(T p);
    public void lowonequantite(T p) throws SQLException;
    public void supprimerStocks(T p);
    public void ajouterStocks2();
    public ResultSet printpdf() throws SQLException;
    public void sendMaill();
    public void printToText();
    public void draftEmail();
    public void sendit();
    public void apiex();
    public void setupServerProperties();
    public Float summ(int d);
    public List<T> stocksList();
    public void sendEmail();
    public ObservableList<T> getDatausers();
}
