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
public class Vote {

    private int id_vote;
    private int id_match;
    private int vote;
    private int id_user;

    public Vote() {
    }

    public Vote(int id_match,int id_user,int vote) {
         if(vote >=0 && vote<=10)
        {
        this.id_match = id_match;
        this.vote = vote;
        this.id_user=id_user;
        }
         else
             System.out.println("vote invalide");
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

   

    public int getId_match() {
        return id_match;
    }

    public void setId_match(int id_match) {
        this.id_match = id_match;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
    
     @Override
    public String toString() {
        return "Vote{" + "id_vote=" + id_vote + ", id_match=" + id_match + ", vote=" + vote + '}';
    }

}
