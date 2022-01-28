/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.entities;

/**
 *
 * @author khali
 */

import cotakwira.entities.Article;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author NMEDIA
 */
public class sendSMS {
    
        public static final String ACCOUNT_SID = System.getenv("ACb8e37bb0bf3cea10e7bdde983d27d7a9");
    public static final String AUTH_TOKEN = System.getenv("b57e36889995d8e63ac432b1faff674b");

    public static void sendSMS(Article a) {
        Twilio.init("AC4ea20b60cbd3bc07fd5c6f81542129ac", "509774f7d01b0c9364970f931e86d7ed");
        Message message = Message.creator(new PhoneNumber("+21652117548"),
        new PhoneNumber("+14043416314"), 
        "Titre: "+a.getTitre()+" Type: "+a.getType()+" Description: "+a.getDescription()).create();
       

        System.out.println(message.getSid());
    }
    
}
