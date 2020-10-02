package cn.ch4u.workbook.service.impl;

import cn.ch4u.workbook.entity.Member;
import cn.ch4u.workbook.entity.WorkSheet;
import cn.ch4u.workbook.entity.WorkTask;
import cn.ch4u.workbook.service.IMemberService;
import cn.ch4u.workbook.service.ISalaryService;
import cn.ch4u.workbook.service.IWorkSheetService;
import cn.ch4u.workbook.service.IWorkTaskService;
import cn.ch4u.workbook.util.KwHelper;
import cn.ch4u.workbook.vo.Salary;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class SalaryServiceImpl implements ISalaryService {
    @Resource
    private IMemberService memberService;
    @Resource
    private IWorkTaskService taskService;
    @Resource
    private IWorkSheetService sheetService;

    @Override
    public List<Salary> calcSalary(LocalDate stDate, LocalDate enDate) {
        //查出所有用户
        QueryWrapper<Member> mwrapper=new QueryWrapper<>();
        mwrapper.orderByAsc("status","sort");
        List<Member> mlist=memberService.list(mwrapper);
        //查出指定日期区间的考勤
        QueryWrapper<WorkSheet> swrapper=new QueryWrapper<>();
        swrapper.ge(stDate!=null,"workDate",stDate);
        swrapper.le(enDate!=null,"workDate",enDate);
        List<WorkSheet> slist=sheetService.list(swrapper);
        //查出指定日期区间的工作项
        QueryWrapper<WorkTask> twrapper=new QueryWrapper<>();
        twrapper.ge(stDate!=null,"workDate",stDate);
        twrapper.le(enDate!=null,"workDate",enDate);
        List<WorkTask> tlist=taskService.list(twrapper);
        //空判断
        if (KwHelper.isNullOrEmpty(mlist) || KwHelper.isNullOrEmpty(slist) || KwHelper.isNullOrEmpty(tlist)){
            return null;
        }
        //遍历，计算值
        Member member=null;
        WorkTask task = null;
        WorkSheet sheet = null;
        List<Salary> list=new LinkedList<>();
        int nums=0;
        //遍历用户
        for (int i = 0; i < mlist.size(); i++) {
            member=mlist.get(i);
            Salary salary=new Salary(member.getId(),member.getName());
            //遍历考勤表
            for (int j = 0; j < slist.size(); j++) {
                sheet =  slist.get(j);
                if (member.getId().equals(sheet.getMid())){
                    //获取当前情况的总人数，用于计算平均工资
                    nums=findWorkMembers(slist,sheet.getWorkType(),sheet.getWorkDate());
                    if (nums == 0){
                        continue;
                    }
                    setWorkMoney(tlist,sheet.getWorkType(),sheet.getWorkDate(),salary,nums);
                }

            }

            //排除空用户
            if (salary.getWorkMoney()==null && salary.getExtraMoney()==null){
                continue;
            }
            list.add(salary);
        }

        return list;
    }

    /**
     * 获取考勤表中具体某一天、某一类型的人数
     * @param slist 考勤表
     * @param workType 工作类型（用于区分正常上班、加班）
     * @param workDate 工作日期
     * @return
     */
    private int findWorkMembers(List<WorkSheet> slist,Integer workType,LocalDate workDate){
        if (KwHelper.isNullOrEmpty(slist) || workType==null || workDate ==null){
            return 0;
        }
        int nums=0;
        WorkSheet sheet=null;
        for (int i = 0; i < slist.size(); i++) {
            sheet =  slist.get(i);
            if (sheet.getWorkDate().isEqual(workDate) && ((workType==2 && sheet.getWorkType().equals(workType)) || (workType!=2 && sheet.getWorkType()!=2))){
                nums++;
            }
        }
        return nums;
    }

    /**
     * 设置
     * @param tlist 任务项list
     * @param workType 工作类型（用于区分正常上班、加班）
     * @param workDate 工作日期
     * @param salary 设置的对象
     * @param nums 当前条件参与工作的总人数，用于计算平均工资
     */
    private void setWorkMoney(List<WorkTask> tlist,Integer workType,LocalDate workDate,Salary salary,int nums){
        if (KwHelper.isNullOrEmpty(tlist) || workType==null || workDate ==null || salary==null){
            return;
        }
        //通用计算
        //工作项，工资
        int items=0;
        BigDecimal money=new BigDecimal("0");
        WorkTask task =null;
        for (int i = 0; i < tlist.size(); i++) {
            task =  tlist.get(i);
            if (task.getWorkDate().equals(workDate) && ((workType==2 && task.getWorkType().equals(workType)) || (workType!=2 && task.getWorkType()!=2))){
                items++;
                //累加单价*总数
                money=money.add(task.getQuantity().multiply(task.getUnitPrice()));
            }
        }
        //求平均(四舍五入，保留两位小数)
        money=money.divide(new BigDecimal(nums),2,BigDecimal.ROUND_HALF_UP);


        //判断类型
        if (workType == 2){
            //填充加班对象
            //工作次数
            if (salary.getExtraTimes()==null){
                salary.setExtraTimes(1);
            }else {
                salary.setExtraTimes(salary.getExtraTimes()+1);
            }
            //工作项，工资
            if (salary.getExtraItems()==null){
                salary.setExtraItems(items);
            }else {
                salary.setExtraItems(salary.getExtraItems()+items);
            }
            if (salary.getExtraMoney() == null){
                salary.setExtraMoney(money);
            }else {
                salary.setExtraMoney(salary.getExtraMoney().add(money));
            }
        }else {
            //填充正常上班对象
            //工作次数
            if (salary.getWorkTimes()==null){
                salary.setWorkTimes(1);
            }else {
                salary.setWorkTimes(salary.getWorkTimes()+1);
            }
            //工作项，工资
            if (salary.getWorkItems()==null){
                salary.setWorkItems(items);
            }else {
                salary.setWorkItems(salary.getWorkItems()+items);
            }
            if (salary.getWorkMoney() == null){
                salary.setWorkMoney(money);
            }else {
                salary.setWorkMoney(salary.getWorkMoney().add(money));
            }
        }


    }
}
