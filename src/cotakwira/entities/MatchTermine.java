/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.entities;

/**
 *
 * @author esprit
 */
public class MatchTermine {

    private int id_match;
    private String score;
    private int duree;
    private String image;

    public MatchTermine() {

    }

    public MatchTermine(int id_match, String score, int duree, String image) {
        this.id_match = id_match;
        this.score = score;
        this.duree = duree;
        this.image = image;
    }
/*************** getteur *********************/
    public int getId_match() {
        return id_match;
    }

    public String getScore() {
        return score;
    }

    public int getDuree() {
        return duree;
    }

    public String getImage() {
        return image;
    }
/***************** setteur ***************************/
    public void setId_match(int id_match) {
        this.id_match = id_match;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
