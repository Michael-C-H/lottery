package cn.ch4u.workbook.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author ch
 * @since 2020-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("workSheet")
public class WorkSheet implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 人员id
     */
    private Integer mid;
    /**
     * 工作类型：0白班、1夜班、2加班
     */
    @TableField("workType")
    private Integer workType;

    /**
     * 日期
     */
    @TableField("workDate")
    private LocalDate workDate;

    /**
     * 工作状态：全勤1、未到0、其他比例
     */
    @TableField("workStatus")
    private Integer workStatus;


}
