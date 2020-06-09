package cn.ch4u.lottery.service.impl;

import cn.ch4u.lottery.constant.LotteryTypeEnum;
import cn.ch4u.lottery.constant.RecommendEnum;
import cn.ch4u.lottery.entity.RecommendRes;
import cn.ch4u.lottery.entity.Record;
import cn.ch4u.lottery.service.BaseLotteryRecommend;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 低出现率算法
 */
@Service
public class LotteryRecommendHighRate extends BaseLotteryRecommend {

    @Override
    public RecommendEnum findCode() {
        return RecommendEnum.high_rate;
    }

    @Override
    public RecommendRes recommend(LotteryTypeEnum typeEnum, List<Record> list) {
        return this.commonRec(typeEnum,list,false);
    }
}
