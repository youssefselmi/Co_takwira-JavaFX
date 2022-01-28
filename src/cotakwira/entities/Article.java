/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.entities;

import java.util.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author khali
 */
public class Article {
    int id;
    String type;
    String titre;
    String description;
    Date date;
    String url;
    ImageView img;

    public Article(int id, String type, String titre, String description, String url, int id_personnel) {
        this.id = id;
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.url = url;
        this.id_personnel = id_personnel;
    }

    public Article(int id, String type, String titre, String description, Date date, String url, int id_personnel) {
        this.id = id;
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.url = url;
        this.id_personnel = id_personnel;
    }

    public Article(String type, String titre, String description, Date date, ImageView img, int id_personnel) {
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.img = img;
        this.id_personnel = id_personnel;
    }

    

    public ImageView getImg() {
        return img;
    }

    

    

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public Article(String type, String titre, String description, Date date, String url, int id_personnel) {
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.url = url;
        this.id_personnel = id_personnel;
    }
    int id_personnel;

    public Article() {
    }

    public Article(String type, String titre, String description, Date date, int id_personnel) {
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.id_personnel = id_personnel;
    }

    public Article(int id, String type, String titre, String description, int id_personnel) {
        this.id = id;
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.id_personnel = id_personnel;
    }

    public Article(int id, String type, String titre, String description, Date date, int id_personnel) {
        this.id = id;
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.id_personnel = id_personnel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId_personnel() {
        return id_personnel;
    }

    public void setId_personnel(int id_personnel) {
        this.id_personnel = id_personnel;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", type=" + type + ", titre=" + titre + ", description=" + description + ", date=" + date + ", id_personnel=" + id_personnel + '}';
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

  
    
    
}
