package cn.ch4u.lottery.entity;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class CustomRes {
    /**
     * 参与计算的总条数
     */
    private Integer totalSize;
    /**
     * 命中规则的条数
     */
    private Integer hitSize;
    /**
     * 命中统计结果(红球)
     */
    private Map<Integer,Integer> redCount;
    /**
     * 命中统计结果(蓝球)
     */
    private Map<Integer,Integer> blueCount;

    public CustomRes addRedCount(Integer ball,Integer count){
        if (redCount == null) redCount=new LinkedHashMap<>();
        redCount.put(ball,count);
        return this;
    }

    public CustomRes addRedCount(Integer ball){
        if (redCount == null) {
            addRedCount(ball,1);
            return this;
        }
        Integer old=redCount.get(ball);
        if (old==null){
            redCount.put(ball,1);
        }else{
            addRedCount(ball,old+1);
        }
        return this;
    }


    public CustomRes addBlueCount(Integer ball,Integer count){
        if (blueCount == null) blueCount=new LinkedHashMap<>();
        blueCount.put(ball,count);
        return this;
    }

    public CustomRes addBlueCount(Integer ball){
        if (blueCount == null) {
            addBlueCount(ball,1);
            return this;
        }
        Integer old=blueCount.get(ball);
        if (old==null){
            blueCount.put(ball,1);
        }else{
            addBlueCount(ball,old+1);
        }
        return this;
    }

}
