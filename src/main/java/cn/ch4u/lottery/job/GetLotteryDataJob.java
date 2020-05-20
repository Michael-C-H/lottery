package cn.ch4u.lottery.job;

import cn.ch4u.lottery.util.HttpClientUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class GetLotteryDataJob extends QuartzJobBean {
    private final static Logger logger = LoggerFactory.getLogger(GetLotteryDataJob.class);
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    	//获取JobDetail中关联的数据
        String msg = (String) context.getJobDetail().getJobDataMap().get("msg");
        try {
            logger.info(HttpClientUtils.doGet("https://www.baidu.com/").getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}