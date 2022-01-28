/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

/**
 *
 * @author pc_dell
 */
public class Randompassword {
    String a=makePassword(8);
    public static String makePassword(int length)
    {String password="";
    for(int i=0;i<length-2;i++)
    { password=password + randomCaracter("bcdefjhijklmnopqrstuvwxyz");
    }
            
    String randomDigit=randomCaracter("23456789");
        password=insertAtRandom(password,randomDigit);

        String randomSymbol=randomCaracter("+-*%*$/&?!");

    password=insertAtRandom(password,randomSymbol);
    
    return password;
    
    
    
    
    
    
    }

    public static String randomCaracter(String caracters) {
int n=caracters.length();
int r=(int) (n * Math.random()) ;
return caracters.substring(r, r+1);
    
    
    }
    public static String insertAtRandom(String str,String toInsert)
    {
        int n=str.length();
        int r=(int) ((n+1)* Math.random());
        return str.substring(0, r) + toInsert + str.substring(r);
        
    }
    
    
    
    
    
}
