package cn.ch4u.lottery.config;

import cn.ch4u.lottery.job.GetLotteryDataJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail GetLotteryDataJobDetail(){
        return JobBuilder.newJob(GetLotteryDataJob.class)//PrintTimeJob我们的业务类
                .withIdentity("getLotteryDataJob")//可以给该JobDetail起一个id
                //每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中可以通过context获取
                .usingJobData("msg", "Hello Quartz")//关联键值对
                .storeDurably()//即使没有Trigger关联时，也不需要删除该JobDetail
                .build();
    }
    @Bean
    public Trigger GetLotteryDataJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 0/6 * * ? *");
        return TriggerBuilder.newTrigger()
                .forJob(GetLotteryDataJobDetail())//关联上述的JobDetail
                .withIdentity("quartzTaskService")//给Trigger起个名字
                .withSchedule(cronScheduleBuilder)
                .build();
    }
}