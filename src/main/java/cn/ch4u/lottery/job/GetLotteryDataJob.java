package cn.ch4u.lottery.job;

import cn.ch4u.lottery.constant.DataSrcEnum;
import cn.ch4u.lottery.constant.LotteryTypeEnum;
import cn.ch4u.lottery.entity.Record;
import cn.ch4u.lottery.factory.DataSrcFactory;
import cn.ch4u.lottery.service.ILotteryDataSrc;
import cn.ch4u.lottery.service.IRecordService;
import cn.ch4u.lottery.util.KwHelper;
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
            //获取配置文件数据源
            String src="JUHE";
            if (KwHelper.isNullOrEmpty(env.getProperty("custom.config.dataSrc"))){
                src=env.getProperty("custom.config.dataSrc");
            }
            DataSrcEnum srcEnum=DataSrcEnum.getEnumByKey(src);
            if (srcEnum==null){
                logger.warn("数据源获取错误，请检查配置，当前获取配置："+src);
                return;
            }
            //获取对应数据源实现类
            ILotteryDataSrc srcApi= DataSrcFactory.getDataSrc(srcEnum);
            if (srcApi==null){
                logger.warn("数据源实现类获取错误，请检查配置，当前获取配置："+src);
                return;
            }
            //获取数据源支持的类型
            List<LotteryTypeEnum> tlist=srcApi.supportType();
            if (KwHelper.isNullOrEmpty(tlist)){
                logger.warn("当前数据源支持的类型为空，当前获取配置："+src);
                return;
            }
            //根据类型去获取数据
            List<Record> rlist=srcApi.recentdatas(tlist);
            if (KwHelper.isNullOrEmpty(rlist)){
                logger.warn("当前数据源获取的最近开奖记录为空，当前获取配置："+src);
                return;
            }
            //入库
            recordService.addBatchUnique(rlist);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}