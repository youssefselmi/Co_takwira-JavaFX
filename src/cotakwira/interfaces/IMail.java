package cotakwira.interfaces;

import javax.mail.Message;
import javax.mail.Session;

public interface IMail<T> {
    public  void sendmail(String recepient ,String pwd  ) throws Exception;
    public Message prepareMessage(Session session, String login, String recepient, String pwd );
}
