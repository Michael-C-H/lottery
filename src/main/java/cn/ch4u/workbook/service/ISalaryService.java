package cn.ch4u.workbook.service;

import cn.ch4u.workbook.vo.Salary;

import java.time.LocalDate;
import java.util.List;

public interface ISalaryService {

    /**
     * 工资汇算
     * @param stDate 起始日期
     * @param enDate 结束日期
     * @return
     */
    List<Salary> calcSalary(LocalDate stDate, LocalDate enDate);
}
