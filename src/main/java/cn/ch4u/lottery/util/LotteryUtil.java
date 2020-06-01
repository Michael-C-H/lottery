package cn.ch4u.lottery.util;

import cn.ch4u.lottery.constant.DataSrcEnum;
import cn.ch4u.lottery.factory.DataSrcFactory;
import cn.ch4u.lottery.service.ILotteryDataSrc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

public class LotteryUtil {
    private final static Logger logger = LoggerFactory.getLogger(LotteryUtil.class);

    /**
     * 获取数据源API
     * @param env 环境
     * @return
     */
    public static ILotteryDataSrc getSrcApi(Environment env){
        //获取配置文件数据源
        String src="JUHE";
        if (KwHelper.isNullOrEmpty(env.getProperty("custom.config.dataSrc"))){
            src=env.getProperty("custom.config.dataSrc");
        }
        DataSrcEnum srcEnum=DataSrcEnum.getEnumByKey(src);
        if (srcEnum==null){
            logger.warn("数据源获取错误，请检查配置，当前获取配置："+src);
            return null;
        }
        //获取对应数据源实现类
        ILotteryDataSrc srcApi= DataSrcFactory.getDataSrc(srcEnum);
        if (srcApi==null){
            logger.warn("数据源实现类获取错误，请检查配置，当前获取配置："+src);
            return null;
        }
        return srcApi;
    }
}
