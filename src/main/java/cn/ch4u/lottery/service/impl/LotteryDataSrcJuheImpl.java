package cn.ch4u.lottery.service.impl;

import cn.ch4u.lottery.constant.DataSrcEnum;
import cn.ch4u.lottery.constant.LotteryTypeEnum;
import cn.ch4u.lottery.entity.HttpClientResult;
import cn.ch4u.lottery.entity.Record;
import cn.ch4u.lottery.service.BaseLotteryDataSrc;
import cn.ch4u.lottery.util.DateUtils;
import cn.ch4u.lottery.util.HttpClientUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class LotteryDataSrcJuheImpl extends BaseLotteryDataSrc {
    /**
     * api的key
     */
    private static final String appKey = "7a743de0b3285e6e8daae193a0bc3d69";
    /**
     * 基本url
     */
    private static final String baseUrl ="http://apis.juhe.cn/lottery/";
    /**
     * 历史数据每次获取的最大条数
     */
    private static final int historyMaxSize= 50;

    private static final Logger logger = LoggerFactory.getLogger(LotteryDataSrcJuheImpl.class);

    @Override
    public DataSrcEnum findSrcCode() {
        return DataSrcEnum.JUHE;
    }

    @Override
    public List<LotteryTypeEnum> supportType() {
        List<LotteryTypeEnum> list =new LinkedList<>();
        //发起http请求
        Map<String,String> params=new HashMap<>(1);
        params.put("key",appKey);
        try {
            HttpClientResult result = HttpClientUtils.doGet(baseUrl + "types", params);
            //解析结果
            JSONObject json = JSONObject.parseObject(result.getContent());
            if (json.getInteger("error_code") == 0) {
                JSONArray array = json.getJSONArray("result");
                JSONObject temp = null;
                for (int i = 0; i < array.size(); i++) {
                    temp = array.getJSONObject(i);
                    LotteryTypeEnum typeEnum = LotteryTypeEnum.getEnumByKey(temp.getString("lottery_id"));
                    if (typeEnum != null) {
                        list.add(typeEnum);
                    }
                }
            }
        }catch (Exception e){
            logger.error("获取支持类型出错："+e.getMessage(),e);
        }
        return list;
    }

    @Override
    public List<Record> recentdatas(List<LotteryTypeEnum> typeEnums) {
        List<Record> list =new LinkedList<>();
        if (typeEnums==null || typeEnums.isEmpty()) return list;
        String url=baseUrl+"query";
        Map<String,String> params=new HashMap<>(2);
        params.put("key",appKey);
        LotteryTypeEnum typeEnum;
        for (int i = 0; i < typeEnums.size(); i++) {
            typeEnum =  typeEnums.get(i);
            params.put("lottery_id",typeEnum.getKey());
            try {
                HttpClientResult result = HttpClientUtils.doGet(url, params);
                //解析结果
                JSONObject json = JSONObject.parseObject(result.getContent());
                if (json.getInteger("error_code") == 0) {
                    json=json.getJSONObject("result");
                    Record record=new Record();
                    record.setTypeKey(typeEnum.getKey());
                    record.setPeriodNo(json.getInteger("lottery_no"));
                    record.setPubDate(DateUtils.asLocalDateTime(json.getDate("lottery_date")));
                    record.setRes(json.getString("lottery_res"));

                    list.add(record);
                }
            }catch (Exception e){
                logger.error("获取"+typeEnum.getName()+"最近数据出错："+e.getMessage(),e);
            }

        }
        return list;
    }

    @Override
    public List<Record> historyDatas(List<LotteryTypeEnum> typeEnums, int count) {
        List<Record> list =new LinkedList<>();
        if (typeEnums==null || typeEnums.isEmpty()) return list;
        if (count<1) return list;

        String url=baseUrl+"history";
        Map<String,String> params=new HashMap<>(4);
        params.put("key",appKey);
        LotteryTypeEnum typeEnum;
        JSONObject temp;

        for (int i = 0; i < typeEnums.size(); i++) {
            typeEnum =  typeEnums.get(i);
            int page=1;
            int pageSize=historyMaxSize;

            if (count<historyMaxSize){
                pageSize=count;
            }

            params.put("lottery_id",typeEnum.getKey());
            //数据源的最大页数
            int totalPage=0;
            //当前已经获取的总数
            int total=0;
            //循环分页获取数据
            do {
                params.put("page", page + "");
                params.put("page_size", pageSize + "");
                try {
                    HttpClientResult result = HttpClientUtils.doGet(url, params);
                    //解析结果
                    JSONObject json = JSONObject.parseObject(result.getContent());
                    if (json.getInteger("error_code") == 0) {
                        json = json.getJSONObject("result");

                        JSONArray array=json.getJSONArray("lotteryResList");
                        for (int j = 0; j < array.size(); j++) {
                            temp =  array.getJSONObject(j);
                            Record record = new Record();
                            record.setTypeKey(typeEnum.getKey());
                            record.setPeriodNo(temp.getInteger("lottery_no"));
                            record.setPubDate(DateUtils.asLocalDateTime(temp.getDate("lottery_date")));
                            record.setRes(temp.getString("lottery_res"));

                            list.add(record);
                        }

                        //设置totalPage
                        totalPage=json.getInteger("totalPage");

                    }

                    //循环递增
                    total+=pageSize;
                    page++;
                    //最后一页获取
                    if (page*pageSize>count){
                        pageSize=pageSize-page*pageSize+count;
                    }
                } catch (Exception e) {
                    logger.error("获取" + typeEnum.getName() + "数据,page="+page+";pageSize"+pageSize+"出错：" + e.getMessage(), e);
                    break;
                }
            }while (total<count && page<=totalPage);

        }
        return list;
    }
}
