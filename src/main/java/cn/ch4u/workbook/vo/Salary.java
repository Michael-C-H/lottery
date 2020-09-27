package cn.ch4u.workbook.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Salary {
    public Salary(Integer memberId, String memberName) {
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public Salary() {
    }

    /**
     * 人员id
     */
    private Integer memberId;
    /**
     * 人员名称
     */
    private String memberName;
    /**
     * 工作次数
     */
    private Integer workTimes;
    /**
     * 工作项数
     */
    private Integer workItems;
    /**
     * 工资
     */
    private BigDecimal workMoney;
    /**
     * 加班次数
     */
    private Integer extraTimes;
    /**
     * 加班项数
     */
    private Integer extraItems;
    /**
     * 加班工资
     */
    private BigDecimal extraMoney;



}
