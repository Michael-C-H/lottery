package cn.ch4u.lottery.controller;

import cn.ch4u.lottery.constant.LotteryTypeEnum;
import cn.ch4u.lottery.constant.RecommendEnum;
import cn.ch4u.lottery.entity.HttpRes;
import cn.ch4u.lottery.service.IRecordService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/")
public class MainController {
    @Resource
    private IRecordService recordService;

    @GetMapping("/type")
    public HttpRes index(){
        Map<String,Object> map=new HashMap<>(2);
        map.put("lotteryType",LotteryTypeEnum.values());
        map.put("recommendType",RecommendEnum.values());
        return new HttpRes(map);
    }

    @GetMapping("/recommend")
    public HttpRes recommend(String lotteryType,String recommendType){
        LotteryTypeEnum typeEnum=LotteryTypeEnum.getEnumByKey(lotteryType);
        RecommendEnum recommendEnum=RecommendEnum.getEnumByKey(recommendType);
        if (typeEnum==null || recommendEnum==null)
            return new HttpRes("参数错误！",null);
        return new HttpRes(recordService.recommend(typeEnum,recommendEnum));
    }
}
