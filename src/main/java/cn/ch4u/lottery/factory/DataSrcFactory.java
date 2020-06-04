package cn.ch4u.lottery.factory;

import cn.ch4u.lottery.constant.DataSrcEnum;
import cn.ch4u.lottery.service.ILotteryDataSrc;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataSrcFactory implements ApplicationContextAware {
 
    private static Map<DataSrcEnum, ILotteryDataSrc> DataSrcBeanMap;
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, ILotteryDataSrc> map = applicationContext.getBeansOfType(ILotteryDataSrc.class);
        DataSrcBeanMap = new HashMap<>();
        map.forEach((key, value) -> DataSrcBeanMap.put(value.findSrcCode(), value));
    }
 
    public static <T extends ILotteryDataSrc> T getInstance(DataSrcEnum code) {
        return (T)DataSrcBeanMap.get(code);
    }
 
}