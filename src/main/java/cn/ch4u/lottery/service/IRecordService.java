package cn.ch4u.lottery.service;

import cn.ch4u.lottery.constant.LotteryTypeEnum;
import cn.ch4u.lottery.constant.RecommendEnum;
import cn.ch4u.lottery.entity.CustomRes;
import cn.ch4u.lottery.entity.RecommendRes;
import cn.ch4u.lottery.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ch
 * @since 2020-05-28
 */
public interface IRecordService extends IService<Record> {
    /**
     * 批量添加记录（类型+期号排重）
     * @param list
     */
    void addBatchUnique(List<Record> list);
    /**
     * 填充历史数据
     */
    void fillHistory();

    /**
     * 推荐
     * @param typeEnum
     * @param recommendEnum
     * @return
     */
    RecommendRes recommend(LotteryTypeEnum typeEnum, RecommendEnum recommendEnum);

    /**
     * 自定义推算
     * @param typeEnum 彩票类型
     * @param red 红球限制
     * @param blue 蓝球限制
     * @return
     */
    CustomRes custom(LotteryTypeEnum typeEnum, List<Integer> red, List<Integer> blue);
}
