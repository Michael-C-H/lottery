package cn.ch4u.lottery.service;

import cn.ch4u.lottery.constant.LotteryTypeEnum;
import cn.ch4u.lottery.constant.RecommendEnum;
import cn.ch4u.lottery.entity.RecommendRes;
import cn.ch4u.lottery.entity.Record;

import java.util.List;

/**
 * 推荐算法
 */
public interface ILotteryRecommend {
    /**
     * 推荐类型枚举
     * @return
     */
    RecommendEnum findCode();

    /**
     * 推荐
     * @param typeEnum 类型
     * @param list 计算数据
     * @return
     */
    RecommendRes recommend(LotteryTypeEnum typeEnum, List<Record> list);

}
