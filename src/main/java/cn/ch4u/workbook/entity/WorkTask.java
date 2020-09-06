package cn.ch4u.workbook.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("workTask")
public class WorkTask implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 工作类型：白班、夜班
     */
    @TableField("workType")
    private Integer workType;

    /**
     * 工作日期
     */
    @TableField("workDate")
    private LocalDate workDate;

    /**
     * 工作项
     */
    @TableField("workItem")
    private String workItem;

    /**
     * 单价
     */
    @TableField("unitPrice")
    private BigDecimal unitPrice;

    /**
     * 数量
     */
    private BigDecimal quantity;

    /**
     * 备注
     */
    private String memo;
    @TableField(exist = false)
    private LocalDate stDate;
    @TableField(exist = false)
    private LocalDate enDate;


}
