package cn.ch4u.lottery.constant;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LotteryTypeEnum {

    DaLeTou(
            "dlt",
            "大乐透",
            1,
            "{\"week\":\"1,3,6\"}",
            "{\"parts\":2,\"repeat\":true,\"part1\":{\"length\":5,\"min\":1,\"max\":35,\"order\":false,\"repeat\":false},\"part2\":{\"length\":2,\"min\":1,\"max\":12,\"order\":false,\"repeat\":false}}"
    ),
    ShuangSeQiu(
            "ssq",
            "双色球",
            2,
            "{\"week\":\"2,4,7\"}",
            "{\"parts\":2,\"repeat\":true,\"part1\":{\"length\":6,\"min\":1,\"max\":33,\"order\":false,\"repeat\":false},\"part2\":{\"length\":1,\"min\":1,\"max\":16,\"order\":false,\"repeat\":false}}"
    );


    String key;
    String name;
    int sort;
    String publishDate;
    String rule;

    LotteryTypeEnum(String key, String name, int sort, String publishDate, String rule) {
        this.key = key;
        this.name = name;
        this.sort = sort;
        this.publishDate = publishDate;
        this.rule = rule;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public int getSort() {
        return sort;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getRule() {
        return rule;
    }

    /**
     * 根据key获取枚举
     * @param key 键
     * @return LotteryTypeEnum
     */
    public static LotteryTypeEnum getEnumByKey(String key) {
        for(LotteryTypeEnum e : LotteryTypeEnum.values()) {
            if(e.getKey().equals(key)) {
                return e;
            }
        }
        //throw new IllegalArgumentException("未定义的枚举类型key=" + key);//未定义的枚举类型!
        return null;
    }

    public static String getEnumName(String key){
        return getEnumByKey(key).getName();
    }
    public static String getEnumPublishDate(String key){
        return getEnumByKey(key).getPublishDate();
    }
    public static String getEnumRule(String key){
        return getEnumByKey(key).getRule();
    }
    public static int getEnumSort(String key){
        return getEnumByKey(key).getSort();
    }

}
