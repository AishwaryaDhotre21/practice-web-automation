package com.confluxsys.dsp.automation.utills;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.List;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
public class Sendemail {
    public static void main(String args[]) throws EmailException, MessagingException, IOException {
      /*EmailAttachment attachment = new EmailAttachment();
       attachment.setPath(System.getProperty("user.dir")+"\\Report\\Report_1.html");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Report 1");
        attachment.setName("Report 1");

        EmailAttachment attachment1 = new EmailAttachment();
        attachment1.setPath(System.getProperty("user.dir")+"\\Report\\Report_2.html");
        attachment1.setDisposition(EmailAttachment.ATTACHMENT);
        attachment1.setDescription("Report 2");
        attachment1.setName("Report 2");

        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("aishwaryadhotre1000@gmail.com", "sqwynmztzaerjpaz"));
        email.setSSLOnConnect(true);
        email.setFrom("aishwaryadhotre1000@gmail.com");
        email.setSubject("TestMail");
        email.setMsg("This is a test mail ... :-)");
        email.addTo("aishwarya.dhotre@confluxsys.com");
      //  email.attach(attachment);
       // email.attach(attachment1);
        email.send();

        List<String> list=new ArrayList<String>();
        list.add(System.getProperty("user.dir")+"\\Report\\Report_1.html");
        list.add(System.getProperty("user.dir")+"\\Report\\Report_2.html");
        list.add(System.getProperty("user.dir")+"\\Report\\Report_3.html");
        list.add(System.getProperty("user.dir")+"\\Report\\Report_4.html");

        for(String str:list)
        System.out.println(str);

        for (String attachmentPath : attachmentPaths) {
            MimeBodyPart attachPart = new MimeBodyPart();
            attachPart.attachFile(attachmentPath);
            email.addPart(attachPart);
        }*/
        String folderPath=System.getProperty("user.dir")+"\\Report";
        List<String> l1=new ArrayList<String>();
        try {
            Files.walkFileTree(
                    Paths.get(folderPath),
                    EnumSet.noneOf(FileVisitOption.class),
                    Integer.MAX_VALUE,
                    new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            System.out.println(file);  // Print the path of the file
                            l1.add(file.toString());
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                            // Handle the error
                            return FileVisitResult.CONTINUE;
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }


        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user","aishwaryadhotre1000@gmail.com" );
        properties.put("mail.password", "sqwynmztzaerjpaz");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("aishwaryadhotre1000@gmail.com", "sqwynmztzaerjpaz");
            }
        };
        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress("aishwaryadhotre1000@gmail.com"));
        InternetAddress[] toAddresses = { new InternetAddress("aishwarya.dhotre@confluxsys.com") };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject("Test reports");
        msg.setSentDate(new Date());

        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Test reports Attachments:-", "text/html");

        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        List<String> list=new ArrayList<String>();
        list.add(System.getProperty("user.dir")+"\\Report\\Report_1.html");
        list.add(System.getProperty("user.dir")+"\\Report\\Report_2.html");
        list.add(System.getProperty("user.dir")+"\\Report\\Report_3.html");
        list.add(System.getProperty("user.dir")+"\\Report\\Report_4.html");

        // adds attachments
        if (l1 != null && l1.size() > 0) {
            for (String filePath : l1) {
                MimeBodyPart attachPart = new MimeBodyPart();
                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                multipart.addBodyPart(attachPart);
            }
        }

        // sets the multi-part as e-mail's content
        msg.setContent(multipart);

        // sends the e-mail
        Transport.send(msg);

    }
}
