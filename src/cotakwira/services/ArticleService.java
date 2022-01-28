/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.services;

import cotakwira.entities.Article;
import cotakwira.interfaces.IArticleService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;
import cotakwira.tools.Connection;

/**
 *
 * @author khali
 */
public class ArticleService implements IArticleService<Article> {
    @Override
    public void ajouterArticle(Article a) {
         try {
            String requete= "INSERT INTO article (type,titre,description,date,url,id_personnel)"
                    + "VALUES (?,?,?,CURRENT_TIMESTAMP(),?,?)";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, a.getType());
            pst.setString(2, a.getTitre());
            pst.setString(3, a.getDescription());
            pst.setString(4, a.getUrl());
            pst.setInt(5,a.getId_personnel());
            pst.executeUpdate();
            System.out.println("Article inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
    @Override
    public void supprimerArticle(Article a) {
        try {
            String requete = "DELETE FROM article where id=?";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, a.getId());
            pst.executeUpdate();
            System.out.println("article supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    @Override
    public void updateArticle(Article a) {
        try {
            String requete = "UPDATE article SET type=?,titre=?,description=?,date=CURRENT_TIMESTAMP(),id_personnel=?  WHERE id=?";
            PreparedStatement pst = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, a.getType());
            pst.setString(2, a.getTitre());
            pst.setString(3, a.getDescription());
            pst.setInt(4,a.getId_personnel());
            pst.setInt(5,a.getId());
            
            pst.executeUpdate();
            System.out.println("Article modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public List<Article> displayArticles() {
         List<Article> articlesList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM article";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Article a = new Article();
                a.setId(rs.getInt("id"));
                a.setType(rs.getString("type"));
                a.setTitre(rs.getString("titre"));
                a.setDescription(rs.getString("description"));
                a.setDate(rs.getDate("date"));
                a.setUrl(rs.getString("url"));
                a.setId_personnel(rs.getInt("id_personnel"));
                articlesList.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return articlesList;
    }
    
    @Override
    public List<Article>chercher_article(String titre) {
        String requete="select * from article where titre=?";
        ResultSet rs=null;
        List<Article> list=new ArrayList();
        try{
            PreparedStatement ps = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            ps.setString(1, titre);
            rs=ps.executeQuery();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        Article a=new Article();
        try{
            while(rs.next()){
               a.setId(rs.getInt("id"));
                a.setType(rs.getString("type"));
                a.setTitre(rs.getString("titre"));
                a.setDescription(rs.getString("description"));
                a.setDate(rs.getDate("date"));
                a.setId_personnel(rs.getInt("id_personnel"));
                list.add(new Article(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getInt(6)));
            }
        }catch(SQLException exc){
             System.err.println(exc.getMessage());
        }
        return list;
    }
    
    public List<Article>trier_article() {
                 List<Article> list=new ArrayList<>();
        try{
            String requete="select * from article order by date desc";
            PreparedStatement ps = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                list.add(new Article(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getInt(7)));
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
    @Override
    public List<Pair<Integer, Article>>article_plus_commentes() {
                 List<Pair<Integer,Article>> list = new ArrayList<Pair<Integer,Article>>();
        try{
            String requete="select * from article";
            Statement ps = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  ps.executeQuery(requete);
            while(rs.next()){
                Pair<Integer, Article> pair = new Pair<>(0, new Article(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getInt(7)));
                list.add(pair);
            }
            
        }
  
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        
        
        try{
            for(int i=0;i<list.size();i++)
            {
                Pair<Integer, Article> pair = list.get(i);
                Article a = pair.getValue();
            String requete="select count(*) from commentaire where id_article=? ";
            PreparedStatement ps = Connection.getInstance().getCnx()
                    .prepareStatement(requete);
            ps.setInt(1,a.getId());
            ResultSet rs=ps.executeQuery();
            int nbc = 0;
            while(rs.next()){
                nbc=rs.getInt(1);
            }
            Pair<Integer, Article> pair2 = new Pair<>(nbc,a);
            list.set(i, pair2);
            }
            Collections.sort(list, new Comparator<Pair<Integer, Article>>() {
    @Override
    public int compare(final Pair<Integer, Article> o1, final Pair<Integer, Article> o2) {
        return o2.getKey()-o1.getKey();
    }
});
        }
  
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
    @Override
    public List<Article> afficher_article() {
                    List<Article> list=new ArrayList<>();
        try{

             String requete="select * from article";
            Statement st = Connection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            
            while(rs.next()){
             Article a=new Article();
             ImageView img=new ImageView();
             Image image;
             try{
                 image=new Image(new FileInputStream(rs.getString("urlimage")));
                 img.setImage(image);
                 Effect DropShadow = new DropShadow();
                 img.setEffect(DropShadow);
                 img.setPreserveRatio(true);
                 img.setFitWidth(70);
                 img.setFitHeight(70);
             }catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
             a.setImg(img);
              list.add(new Article(rs.getString("type"),rs.getString("titre"),rs.getString("description"),rs.getDate("date"),a.getImg(),rs.getInt("id_personnel")));
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    
    
    
    
    

}
