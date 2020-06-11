package cn.ch4u.lottery.entity;

import lombok.Data;

@Data
public class HttpRes {
    /**
     * 成功返回的代码
     */
    private static final int SUCCESS_CODE=1;
    /**
     * 失败返回的代码
     */
    private static final int FAIL_CODE=-1;


    private int code;
    private String msg;
    private Object data;

    /**
     * 成功构造函数
     * @param data
     */
    public HttpRes(Object data){
        this.code=SUCCESS_CODE;
        this.msg="";
        this.data=data;
    }

    /**
     * 失败构造函数
     * @param msg
     * @param errCode
     */
    public HttpRes(String msg,Integer errCode){
        if (errCode!=null){
            this.code=errCode;
        }else {
            this.code=FAIL_CODE;
        }
        this.msg=msg;
        this.data="";
    }

    /**
     * 全元素构造
     * @param code
     * @param msg
     * @param data
     */
    public HttpRes(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 默认构造
     */
    public HttpRes() {
    }
}
