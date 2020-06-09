package cn.ch4u.lottery.constant;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 推荐类型枚举
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RecommendEnum {
    low_rate("lowRate","低频模式","数字出现次数越少，推荐权重越高为标准"),
    high_rate("highRate","高频模式","数字出现次数越多，推荐权重越高为标准")
    ;
    String key;
    String name;
    String memo;

    RecommendEnum(String key,String name,String memo){
        this.key=key;
        this.name=name;
        this.memo=memo;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }


    public String getMemo() {
        return memo;
    }

    public static RecommendEnum getEnumByKey(String key) {
        for(RecommendEnum e : RecommendEnum.values()) {
            if(e.getKey().equals(key)) {
                return e;
            }
        }
        return null;
    }
}