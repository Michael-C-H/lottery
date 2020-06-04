package cn.ch4u.lottery.entity;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class RecommendRes {
    /**
     * 类型key
     */
    private String typeKey;
    /**
     * 类型名称
     */
    private String typeName;
    /**
     * 结果
     */
    private List<Integer> res;
    /**
     * 红蓝分割下标
     */
    private Integer divideIdx;

    /**
     * 添加元素
     * @param num
     * @return
     */
    public List<Integer> addRes(Integer num){
        if (this.res==null){
            this.res=new LinkedList<>();
        }
        this.res.add(num);
        return  this.res;
    }


}
