package com.confluxsys.dsp.automation.utills;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.MessagingException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class SendMailWithAttachment {

    public static void sendEmailReport_2() throws EmailException, MessagingException {

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
        properties.put("mail.user","adc.notification.sender@gmail.com" );
        properties.put("mail.password", "zgklkfyypbtozsvq");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("adc.notification.sender@gmail.com", "zgklkfyypbtozsvq");
            }
        };
        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress("adc.notification.sender@gmail.com"));
        InternetAddress[] toAddresses = { new InternetAddress("aishwarya.dhotre@confluxsys.com") };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject("Test reports");
        msg.setSentDate(new Date());

        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Hello Everyone,\r\n" +
                "Test suite has been executed successfully.\r\n" +
                "\r\n" +
                "Please find below Summary report attached.\r\n" +
                "Note: Please Download the report the report and open with browser."
                + "\n\n\n\n\n\nThanks and Regards"
                + "\nConfluxsys Pvt. Ltd. India", "text/html");

        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

      /*  List<String> list=new ArrayList<String>();
        list.add(System.getProperty("user.dir")+"\\Report\\Report_1.html");
        list.add(System.getProperty("user.dir")+"\\Report\\Report_2.html");
        list.add(System.getProperty("user.dir")+"\\Report\\Report_3.html");
        list.add(System.getProperty("user.dir")+"\\Report\\Report_4.html");*/

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
    public  void sendEmailReport_1() throws EmailException {
        // Create the email message

        List<String> list=new ArrayList<String>();
        list.add(System.getProperty("user.dir")+"\\Report\\Report_1.html");


        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("adc.notification.sender@gmail.com", "zgklkfyypbtozsvq"));
        email.setSSLOnConnect(true);
        email.addTo("aishwarya.dhotre@confluxsys.com", "Aishwarya Dhotre");
        email.setFrom("aishwarya.dhotre@confluxsys.com", "Quality Assurance Team");
        email.setSubject("Test Suite Execution Report");
        email.setMsg("Hello Everyone,\r\n" +
                "Test suite has been executed successfully.\r\n" +
                "\r\n" +
                "Please find below Summary report attached.\r\n" +
                "Note: Please Download the report the report and open with browser."
                + "\n\n\n\n\n\nThanks and Regards"
                + "\nConfluxsys Pvt. Ltd. India");

        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(System.getProperty("user.dir")+"\\Report\\Report_1.html");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Report 1");
        attachment.setName("Report 1");

        EmailAttachment attachment1 = new EmailAttachment();
        attachment1.setPath(System.getProperty("user.dir")+"\\Report\\Report_2.html");
        attachment1.setDisposition(EmailAttachment.ATTACHMENT);
        attachment1.setDescription("Report 2");
        attachment1.setName("Report 2");

        email.attach(attachment);
        email.attach(attachment1);
        email.send();
}

    public static void sendEmailReport() throws MessagingException {
        Initialization obj=new Initialization();
        Set<String> fileList=obj.getSet();
        // Initialize a list to store matched files
        List<File> matchedFiles = new ArrayList<>();
        String directoryPath = "C:\\Users\\Confluxsys\\Desktop\\DSP_Automation_Framework\\dsp-automation-selenium\\report";
        try {
            File directory = new File(directoryPath);
            File[] filesInDirectory = directory.listFiles();
            if (filesInDirectory != null) {

                // Iterate through the files in the directory
                for (File file : filesInDirectory) {
                    String fileName = file.getName();

                    // Check if the file name is in the list
                    if (fileList.contains(fileName)) {
                        matchedFiles.add(file);
                    }
                }
            }
        }catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println("Matched File names-\n\n");
        for(File s:matchedFiles)
        {
            System.out.println(s);
        }
        System.out.println();
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user","adc.notification.sender@gmail.com" );
        properties.put("mail.password", "zgklkfyypbtozsvq");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("adc.notification.sender@gmail.com", "zgklkfyypbtozsvq");
            }
        };
        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress("adc.notification.sender@gmail.com"));
        InternetAddress[] toAddresses = { new InternetAddress("aishwarya.dhotre@confluxsys.com") };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject("Test reports");
        msg.setSentDate(new Date());

        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Hello Everyone,\r\n" +
                "Test suite has been executed successfully.\r\n" +
                "\r\n" +
                "Please find below Summary report attached.\r\n" +
                "Note: Please Download the report the report and open with browser."
                + "\n\n\n\n\n\nThanks and Regards"
                + "\nConfluxsys Pvt. Ltd. India", "text/html");

        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        // adds attachments
        for (File matchedFile : matchedFiles) {
            MimeBodyPart attachmentPart = new MimeBodyPart();
            try {
                attachmentPart.attachFile(matchedFile);
            }catch(Exception e)
            {System.out.println(e);}
            multipart.addBodyPart(attachmentPart);
        }


        // sets the multi-part as e-mail's content
        msg.setContent(multipart);

        // sends the e-mail
        Transport.send(msg);
    }
}
