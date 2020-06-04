package cn.ch4u.lottery.constant;

/**
 * 推荐类型枚举
 */
public enum RecommendEnum {
    low_rate //低频模式（以数字出现次数越少，推荐权重越高为标准）
    ;

    public static RecommendEnum getEnumByKey(String key) {
        for(RecommendEnum e : RecommendEnum.values()) {
            if(e.name().equals(key)) {
                return e;
            }
        }
        return null;
    }
}