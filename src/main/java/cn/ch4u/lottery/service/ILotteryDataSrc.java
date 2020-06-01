package cn.ch4u.lottery.service;

import cn.ch4u.lottery.constant.DataSrcEnum;
import cn.ch4u.lottery.constant.LotteryTypeEnum;
import cn.ch4u.lottery.entity.Record;

import java.util.List;

public interface ILotteryDataSrc {
    /**
     * 数据源类型枚举
     * @return
     */
    DataSrcEnum findSrcCode();

    /**
     * 获取支持的类型
     * @return
     */
    List<LotteryTypeEnum> supportType();

    /**
     * 根据类型获取该类型最新的数据
     * @param typeEnum
     * @return
     */
    Record recentdata(LotteryTypeEnum typeEnum);

    /**
     * 根据类型集合获取类型最新的数据
     * @param typeEnums
     * @return
     */
    List<Record> recentdatas(List<LotteryTypeEnum> typeEnums);

    /**
     * 根据类型集合获取类型的历史数据
     * @param typeEnums 类型集合
     * @param count 一共要获取多少条
     * @return
     */
    List<Record> historyDatas(List<LotteryTypeEnum> typeEnums,int count);
}
