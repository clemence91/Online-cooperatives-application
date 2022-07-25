
package memoire.UIModel;


import java.io.Serializable;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.primefaces.model.UploadedFile;


/**
 *
 * @author Niyigena Clemence
 */
@ManagedBean(name="emai")
@SessionScoped
public class SendEmail implements Serializable{
    final String username = "niyirorakohati2020";
    final String password = "niyigena";
    UploadedFile filet;
    
public void sendEmail(){
    Properties props = new Properties();
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("niyirorakohati2020@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(mail));
        message.setSubject("perpetual");
        message.setText("PFA");

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();
        String file = "C:\\Users\\Niyigena Clemence\\Desktop\\New folder\\"+filet.getFileName()+"";
        String fileName = "perpertual";
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        System.out.println("Sending");

        Transport.send(message);

        System.out.println("Done");
FacesContext ct = FacesContext.getCurrentInstance();
                FacesMessage msg = new FacesMessage("well done ");
                ct.addMessage(null, msg);
    } catch (MessagingException e) {
        FacesContext ct = FacesContext.getCurrentInstance();
                FacesMessage msg = new FacesMessage("Wrong  "+e.getMessage());
                ct.addMessage(null, msg);
        e.printStackTrace();
    }
  }

    public UploadedFile getFilet() {
        return filet;
    }

    public void setFilet(UploadedFile filet) {
        this.filet = filet;
    }

    

private String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
