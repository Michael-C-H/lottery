package cn.ch4u.workbook.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Summary {
    /**
     * 日期
     */
    private LocalDate workDate;

    /**
     * 工作项任务数
     */
    private Integer items;

    /**
     * 合计价格
     */
    private BigDecimal money;
    /**
     * 白班夜班
     */
    private Integer workType;


}
