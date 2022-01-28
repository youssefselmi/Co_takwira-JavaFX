/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.services;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import cotakwira.entities.Match;
import cotakwira.entities.User;
import cotakwira.tools.ConnexionBD;
import cotakwira.tools.Connection;

/**
 *
 * @author esprit
 */
public class LoginCrud {

   
   //Connection c = ConnexionBD.getInstance().getCnx();
    
    public String verifierUser(String login,String mdp) throws SQLException
    {   String req = "select * from user ";
       
       Statement ste = Connection.getInstance().getCnx().createStatement();
       ResultSet res= ste.executeQuery(req);
        while (res.next()) {
         if(res.getString("email").equals(login)&&res.getString("mdp").equals(mdp))
         {
            return res.getString("role");
         }   
        }
        
        
        
        
        return "false";
    }
    
    
     public int finId (String login,String mdp) throws SQLException
    {   String req = "select * from user ";
       
       Statement ste = Connection.getInstance().getCnx().createStatement();
       ResultSet res= ste.executeQuery(req);
        while (res.next()) {
         if(res.getString("email").equals(login)&&res.getString("mdp").equals(mdp))
         {
            return res.getInt("id");
         }   
        }
        
        
        
        
        return 0 ;
    }
   
    public int idUserFind(String login) throws SQLException
    {   String req = "select * from user ";
       
       Statement ste = Connection.getInstance().getCnx().createStatement();
       ResultSet res= ste.executeQuery(req);
        while (res.next()) {
         if(res.getString("email").equals(login))
         {
            return res.getInt("id");
         }   
        }
        
        
        
        return -1;
    }   

    public int idUser(String login) throws SQLException
    {   String req = "select * from user";
       
       Statement ste = Connection.getInstance().getCnx().createStatement();
       ResultSet res= ste.executeQuery(req);
        while (res.next()) {
         if(res.getString("email").equals(login))
        
            return res.getInt("id");
       
         
         
           
        }
        return -1;
}
    
    
    public User getByMail(String Email) throws SQLException{
        User a=new User();
        try {
            String req="SELECT * FROM user where email='"+Email+"'";
            //Statement s=Connection.getInstance().getConnection().createStatement();
              Statement s = Connection.getInstance().getCnx().createStatement();
         
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
 
            a.setId(rs.getInt("id"));
            a.setUsername(rs.getString("email"));
            a.setPassword(rs.getString("mdp"));
            a.setRole(rs.getString("role"));
                                
            }
        } catch (SQLException ex) {
ex.getMessage();
        }
        return a;
    }
    
    

   

      

}
