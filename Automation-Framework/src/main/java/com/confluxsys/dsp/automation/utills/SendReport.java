package com.confluxsys.dsp.automation.utills;

import org.testng.IExecutionListener;

import javax.mail.MessagingException;

public class SendReport implements IExecutionListener
{
    public void onExecutionFinish() {

     /*  File currentReportFile = new File(System.getProperty("user.dir")+"\\Report\\Report_1.html");
        if(currentReportFile.isFile()) {
            try {
                //SendMailWithAttachment.sendEmailReport();
                SendMailWithAttachment.new_fun_1();
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }*/
        try {
            //SendMailWithAttachment.sendEmailReport();
            SendMailWithAttachment.sendEmailReport();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }

}
