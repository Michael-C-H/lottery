package cn.ch4u.lottery.entity;

import cn.ch4u.lottery.util.KwHelper;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * <p>
 * 
 * </p>
 *
 * @author ch
 * @since 2020-05-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("typeKey")
    private String typeKey;

    @TableField("periodNo")
    private Integer periodNo;

    @TableField("pubDate")
    private LocalDateTime pubDate;

    private String res;

    public void setRes(String res) {
        this.res = res;
        if (!KwHelper.isNullOrEmpty(res)){
            String[] arr = res.split(",");
            if (arr!=null && arr.length>0){
                //转换为int数组
                this.setResArr(Arrays.asList(arr).stream().mapToInt(Integer::parseInt).toArray());
            }
        }
    }
    @TableField(exist = false)
    private int[] resArr;


}
