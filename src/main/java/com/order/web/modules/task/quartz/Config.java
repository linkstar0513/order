package com.order.web.modules.task.quartz;


import com.order.web.modules.task.quartz.job.SendMailJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    //建立任务实例
    @Bean
    public JobDetail uploadTaskDetail(){
        return JobBuilder.newJob(SendMailJob.class).withIdentity("sendMailTask").storeDurably().build();
    }
    //注册触发器
    @Bean
    public Trigger myTrigger(){
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(uploadTaskDetail())
                .withIdentity("myTrigger1","myTriggerGroup1")
                .usingJobData("job_trigger_param","job_trigger_param1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                //.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? 2018"))
                .build();
        return trigger;
    }
}
