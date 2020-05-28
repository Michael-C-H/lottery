package cn.ch4u.lottery.service;

import cn.ch4u.lottery.constant.LotteryTypeEnum;
import cn.ch4u.lottery.entity.Record;

import java.util.LinkedList;
import java.util.List;

public abstract class BaseLotteryDataSrc implements ILotteryDataSrc{

    @Override
    public Record recentdata(LotteryTypeEnum typeEnum) {
        List<LotteryTypeEnum> typeEnums=new LinkedList<>();
        typeEnums.add(typeEnum);
        List<Record> res= this.recentdatas(typeEnums);
        if (res!=null && res.size()>0){
            return res.get(0);
        }else {
            return null;
        }
    }
}
