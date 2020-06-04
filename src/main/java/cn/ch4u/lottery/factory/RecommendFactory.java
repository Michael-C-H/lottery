package cn.ch4u.lottery.factory;

import cn.ch4u.lottery.constant.RecommendEnum;
import cn.ch4u.lottery.service.ILotteryRecommend;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RecommendFactory implements ApplicationContextAware {
 
    private static Map<RecommendEnum, ILotteryRecommend> maps;
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, ILotteryRecommend> map = applicationContext.getBeansOfType(ILotteryRecommend.class);
        maps = new HashMap<>();
        map.forEach((key, value) -> maps.put(value.findCode(), value));
    }
 
    public static <T extends ILotteryRecommend> T getInstance(RecommendEnum code) {
        return (T)maps.get(code);
    }
 
}