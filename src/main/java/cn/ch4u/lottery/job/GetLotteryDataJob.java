package cn.ch4u.lottery.job;

import cn.ch4u.lottery.constant.LotteryTypeEnum;
import cn.ch4u.lottery.entity.Record;
import cn.ch4u.lottery.service.ILotteryDataSrc;
import cn.ch4u.lottery.service.IRecordService;
import cn.ch4u.lottery.util.KwHelper;
import cn.ch4u.lottery.util.LotteryUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class GetLotteryDataJob extends QuartzJobBean {
    private final static Logger logger = LoggerFactory.getLogger(GetLotteryDataJob.class);
    @Autowired
    Environment env;
    @Autowired
    IRecordService recordService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            //获取对应数据源实现类
            ILotteryDataSrc srcApi= LotteryUtil.getSrcApi(env);
            if (srcApi==null){
                return;
            }
            //获取数据源支持的类型
            List<LotteryTypeEnum> tlist=srcApi.supportType();
            if (KwHelper.isNullOrEmpty(tlist)){
                return;
            }
            //根据类型去获取数据
            List<Record> rlist=srcApi.recentdatas(tlist);
            if (KwHelper.isNullOrEmpty(rlist)){
                return;
            }
            //入库
            recordService.addBatchUnique(rlist);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}