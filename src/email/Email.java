package email;

import java.text.DateFormatSymbols;
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
    
    String host = "pop.gmail.com"; //research how to get reigate.ac.uk domains to work instead of just gmail
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
    
    public int[] getEmailCountPerMonth(String user, String password, String folder) {
        int[] emailCount = new int[12];
        
        try {
            if (store.isConnected()) {
                store.close();
            }
            store.connect(host, user, password);
            
            Folder emailFolder = store.getFolder(folder.toUpperCase());
            emailFolder.open(Folder.READ_ONLY);
            Message[] messages = emailFolder.getMessages();
            
            int index = 0;
            
            for (String monthTemp : new DateFormatSymbols().getMonths()) {
                int value = 0;
                if (!monthTemp.equals("")) {
                    for (int i = 0, n = messages.length; i < n; i++) {
                        if (dateFormat.format(messages[i].getSentDate()).equalsIgnoreCase(monthTemp.substring(0, 3))) {
                            value++;
                        }
                    }
                    emailCount[index] = value;
                    index++;
                }
            }
            
            
        } catch (NoSuchProviderException e) {
            GUI.printError(e.toString());
        } catch (MessagingException e) {
            GUI.printError(e.toString());
        } catch (Exception e) {
            GUI.printError(e.toString());
        }
        
        return emailCount;

    }
    
    public int getEmailCount(String user, String password, String folder, String month) {
        int emailCount = 0;
        
        try {
            if (store.isConnected()) {
                store.close();
            }
            store.connect(host, user, password);
            
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
            GUI.printError(e.toString());
        } catch (MessagingException e) {
            GUI.printError(e.toString());
        } catch (Exception e) {
            GUI.printError(e.toString());
        }
        
        return emailCount;
    }

}