/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.interfaces;
import cotakwira.entities.Article;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author khali
 */
public interface IArticleService <Article> {
     public void ajouterArticle(Article a);
     public void supprimerArticle(Article a);
     public void updateArticle(Article a);
     public List<Article> displayArticles();
     public List<Article>chercher_article(String titre);
     public List<Pair<Integer, Article>>article_plus_commentes();
     public List<Article> afficher_article();
     
}
    
