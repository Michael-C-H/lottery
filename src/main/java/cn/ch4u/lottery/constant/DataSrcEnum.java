package cn.ch4u.lottery.constant;

/**
 * 数据源枚举
 */
public enum DataSrcEnum {
    JUHE;

    public static DataSrcEnum getEnumByKey(String key) {
        for(DataSrcEnum e : DataSrcEnum.values()) {
            if(e.name().equals(key)) {
                return e;
            }
        }
        return null;
    }
}