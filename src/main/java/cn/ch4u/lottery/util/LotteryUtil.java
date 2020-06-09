package cn.ch4u.lottery.util;

import cn.ch4u.lottery.constant.DataSrcEnum;
import cn.ch4u.lottery.entity.RecommendRes;
import cn.ch4u.lottery.factory.DataSrcFactory;
import cn.ch4u.lottery.service.ILotteryDataSrc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.util.*;
import java.util.stream.Collectors;

public class LotteryUtil {
    private final static Logger logger = LoggerFactory.getLogger(LotteryUtil.class);

    private static ILotteryDataSrc dataSrc=null;


    /**
     * 获取数据源API
     * @param env 环境
     * @return
     */
    public static ILotteryDataSrc getSrcApi(Environment env){
        if (dataSrc!=null) return dataSrc;

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
        ILotteryDataSrc srcApi= DataSrcFactory.getInstance(srcEnum);
        if (srcApi==null){
            logger.warn("数据源实现类获取错误，请检查配置，当前获取配置："+src);
            return null;
        }
        dataSrc=srcApi;
        return srcApi;
    }


    /**
     * 根据map的value对集合进行排序
     * @param aMap 要排序的集合
     * @param isDesc 是否是降序
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K extends Comparable, V extends Comparable> Map<K, V> sortMapByValues(Map<K, V> aMap, boolean isDesc) {
        HashMap<K, V> finalOut = new LinkedHashMap<>();
        aMap.entrySet()
                .stream()
                .sorted((p1, p2) -> isDesc?p2.getValue().compareTo(p1.getValue()):p1.getValue().compareTo(p2.getValue()))
                .collect(Collectors.toList()).forEach(ele -> finalOut.put(ele.getKey(), ele.getValue()));
        return finalOut;
    }

    /**
     * 对推荐结果进行分段升序排序
     * @param res
     */
    public static void sortRes(RecommendRes res){
        if (res==null||KwHelper.isNullOrEmpty(res.getRes()))return;
        List<Integer> oldList=res.getRes();
        int divideIdx=res.getDivideIdx();
        List<Integer> list1=oldList.subList(0,divideIdx);
        List<Integer> list2=oldList.subList(divideIdx,oldList.size());
        Collections.sort(list1);
        Collections.sort(list2);
    }
}
