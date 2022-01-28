/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

import cotakwira.services.JoueurCrud;
import java.util.TimerTask;

/**
 *
 * @author USER-PC
 */
public class Mytask extends TimerTask{
    JoueurCrud jcd=new JoueurCrud();
    HomeJoueurController hjc=new HomeJoueurController();
    @Override
    public void run() {
        jcd.miseajour();
        System.out.println("aaaaa");
       // hjc.refreshJoueur();
        
    }
    
}
