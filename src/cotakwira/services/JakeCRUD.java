package cotakwira.services;

import javafx.collections.ObservableList;
import cotakwira.entities.factures;
import cotakwira.entities.jake;
import cotakwira.entities.stocks;
import cotakwira.interfaces.IFactures;
import cotakwira.tools.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JakeCRUD {
    public void ajouterCurrency(jake j) {
        try {
            String requete = "INSERT INTO takwira_currency (Link)" + "VALUES ('" + j.getLink() + "')";
            Statement st = Connection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("currency ajouter !");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
