package cn.ch4u.lottery.service.impl;

import cn.ch4u.lottery.constant.LotteryTypeEnum;
import cn.ch4u.lottery.constant.RecommendEnum;
import cn.ch4u.lottery.entity.RecommendRes;
import cn.ch4u.lottery.entity.Record;
import cn.ch4u.lottery.service.ILotteryRecommend;
import cn.ch4u.lottery.util.KwHelper;
import cn.ch4u.lottery.util.LotteryUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 低出现率算法
 */
@Service
public class LotteryRecommendLowRate implements ILotteryRecommend {

    @Override
    public RecommendEnum findCode() {
        return RecommendEnum.low_rate;
    }

    @Override
    public RecommendRes recommend(LotteryTypeEnum typeEnum, List<Record> list) {
        if (typeEnum==null || KwHelper.isNullOrEmpty(list)) return null;
        JSONObject rule=JSONObject.parseObject(typeEnum.getRule());
        RecommendRes res=new RecommendRes();
        res.setTypeKey(typeEnum.getKey());
        res.setTypeName(typeEnum.getName());
        res.setDivideIdx(rule.getJSONObject("part1").getInteger("length"));
        int parts=rule.getInteger("parts");
        for (int i = 1; i <= parts; i++) {
            //分部配置
            JSONObject pconf=rule.getJSONObject("part"+i);
            int plen=pconf.getInteger("length");
            int pmax=pconf.getInteger("max");
            int pmin=pconf.getInteger("min");
            boolean porder=pconf.getBoolean("order");
            boolean prepeat=pconf.getBoolean("repeat");

            if(porder){
                //TODO:有序做法
            }else{
                if (prepeat){
                    //TODO:允许重复做法
                }else{
                    //初始化map
                    Map<Integer,Integer> map=new HashMap<>(pmax);
                    for (int j = pmin; j <=pmax; j++) {
                         map.put(j,0);
                    }
                    //获取历史数据取值范围
                    int start=0;
                    for (int j = i; j > 1; j--) {
                         //起始位置+上一部分的长度
                         start+=rule.getJSONObject("part"+(j-1)).getInteger("length");
                    }
                    int end=start+plen;
                    //从历史数据中获取出现次数
                    Record record;
                    for (int j = 0; j < list.size(); j++) {
                        record =  list.get(i);
                        int[] history=record.getResArr();
                        for (int k = start; k < end; k++) {
                            int num = history[k];
                            //累加出现次数
                            map.put(num,map.get(num)+1);
                        }
                    }
                    //map根据value排序
                    Map<Integer,Integer> smap= LotteryUtil.sortMapByValues(map,true);
                    //遍历map，取前plen个数字，放入结果
                    int addNum=1;
                    for(Integer key : smap.keySet()) {
                        if (addNum>plen)break;;
                        res.addRes(key);
                        addNum++;
                    }
                }
            }
        }
        return res;
    }
}
