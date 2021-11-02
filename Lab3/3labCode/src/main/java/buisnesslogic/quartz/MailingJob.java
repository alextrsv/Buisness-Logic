package buisnesslogic.quartz;


import buisnesslogic.Repositories.UserRepository;
import buisnesslogic.email.MailSender;
import buisnesslogic.email.OfferMailingMessage;
import buisnesslogic.exceptions.SendMessageExeption;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.sql.Time;
import java.util.Date;

public class MailingJob extends QuartzJobBean {

    @Autowired
    UserRepository userRepositty;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        System.out.println("Mailing, " + date.toString());
//        OfferMailingMessage offerMailingMessage = new OfferMailingMessage();
//        userRepositty.findAll().forEach((it)-> {
//            if(it.getStudent() != null) {
//                offerMailingMessage.setToAddr(it.getStudent().getEmail());
//                try {
//                    MailSender.makeSend(offerMailingMessage);
//                } catch (SendMessageExeption sendMessageExeption) {
//                    sendMessageExeption.printStackTrace();
//                }
//            }
//        });
    }
}
