package email;

import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class Email {

    Properties properties;
    Store store;
    
    String host = "pop.gmail.com";
    String mailStoreType = "pop3";
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM");
    
    public Email() throws NoSuchProviderException {
        properties = new Properties();
        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.starttls.enable", "true");
        
        Session emailSession = Session.getDefaultInstance(properties);
        store = emailSession.getStore("pop3s");
    }
    
    public int getEmailCount(String user, String password, String folder, String month) {
        int emailCount = 0;
        
        try {
            if (!store.isConnected()) {
                store.connect(host, user, password);
            }
            Folder emailFolder = store.getFolder(folder.toUpperCase());
            emailFolder.open(Folder.READ_ONLY);
            Message[] messages = emailFolder.getMessages();
            
            if (month.equalsIgnoreCase("ALL")) {
                emailCount = messages.length;
            } else {
                for (int i = 0, n = messages.length; i < n; i++) {
                    if (dateFormat.format(messages[i].getSentDate()).equalsIgnoreCase(month)) {
                        emailCount++;
                    }
                }
            }
            
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return emailCount;
    }

}