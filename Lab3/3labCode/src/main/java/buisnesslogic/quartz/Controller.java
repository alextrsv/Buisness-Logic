package buisnesslogic.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
public class Controller {

    @Autowired
    private Scheduler scheduler;

    @PostMapping("/shed")
    public void doJob(){
        JobDetail jobDetail = buildJobDetail();
        Trigger trigger = buildTrigger(jobDetail);

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    private JobDetail buildJobDetail(){
        return JobBuilder.newJob(MailingJob.class)
                .withIdentity(UUID.randomUUID().toString())
                .withDescription("sending email job")
                .storeDurably()
                .build();
    }

    private Trigger buildTrigger(JobDetail jobDetail){
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-triggers")
                .withDescription("send email trigger")
                .withSchedule(
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .repeatForever()
                                .withIntervalInMilliseconds(5000))
                .build();
    }
}
