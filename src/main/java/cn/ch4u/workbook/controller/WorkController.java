package cn.ch4u.workbook.controller;

import cn.ch4u.workbook.entity.WorkSheet;
import cn.ch4u.workbook.entity.WorkTask;
import cn.ch4u.workbook.service.ISalaryService;
import cn.ch4u.workbook.service.IWorkSheetService;
import cn.ch4u.workbook.service.IWorkTaskService;
import cn.ch4u.workbook.util.DateUtils;
import cn.ch4u.workbook.util.KwHelper;
import cn.ch4u.workbook.vo.HttpRes;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/work")
public class WorkController {
    @Resource
    private IWorkSheetService workSheetService;
    @Resource
    private IWorkTaskService workTaskService;
    @Resource
    private ISalaryService salaryService;

    @RequestMapping("sheets")
    public HttpRes sheets(){
        return new HttpRes();
    }

    @RequestMapping("monthSheet")
    public HttpRes monthSheet(String date){
        if (KwHelper.isNullOrEmpty(date)){
            return new HttpRes("参数错误！",null);
        }
        LocalDate ldate=DateUtils.strToLocalDate(date,null);
        //获取月初
        LocalDate monthOfFirstDate=ldate.with(TemporalAdjusters.firstDayOfMonth());
        //获取月末
        LocalDate monthOfLastDate=ldate.with(TemporalAdjusters.lastDayOfMonth());
        QueryWrapper<WorkSheet> wrapper=new QueryWrapper<>();
        wrapper.ge("workDate",monthOfFirstDate)
                .le("workDate",monthOfLastDate);

        return new HttpRes(workSheetService.list(wrapper));
    }

    @RequestMapping("saveSheets")
    public HttpRes saveSheets(@RequestBody List<WorkSheet> list){
        if (KwHelper.isNullOrEmpty(list)){
            return new HttpRes("参数错误！",null);
        }
        return new HttpRes(workSheetService.saveSheets(list));
    }

    @RequestMapping("delSheet")
    public HttpRes delSheet(int id){
        return new HttpRes(workSheetService.removeById(id));
    }

    @RequestMapping("taskSummary")
    public HttpRes taskSummary(@RequestBody Map<String,String> map){
        WorkTask task=new WorkTask();
        //查询条件
        task.setStDate(DateUtils.strToLocalDate(map.get("stDate"),null));
        task.setEnDate(DateUtils.strToLocalDate(map.get("enDate"),null));
        task.setWorkItem(map.get("workItem"));
        //分页参数
        int pageIdx=1;
        if(!KwHelper.isNullOrEmpty(map.get("pageIdx"))){
            pageIdx=Integer.parseInt(map.get("pageIdx"));
        }
        int pageSize=10;
        if(!KwHelper.isNullOrEmpty(map.get("pageSize"))){
            pageSize=Integer.parseInt(map.get("pageSize"));
        }
        return new HttpRes(workTaskService.taskSummary(task,pageIdx,pageSize));
    }

    @RequestMapping("taskSummaryDetail")
    public HttpRes taskSummaryDetail(String date,Integer workType){
        if (KwHelper.isNullOrEmpty(date) || workType ==null){
            return new HttpRes("参数错误！",null);
        }

        QueryWrapper<WorkTask> wrapper=new QueryWrapper<>();
        wrapper.eq("workDate",DateUtils.strToLocalDate(date,null))
                .eq("workType",workType)
                .orderByAsc("workItem");
        return new HttpRes(workTaskService.list(wrapper));
    }

    @RequestMapping("saveTask")
    public HttpRes saveTask(@RequestBody WorkTask workTask){
        if (workTask==null || KwHelper.isNullOrEmpty(workTask.getWorkItem()) || workTask.getWorkDate()==null){
            return new HttpRes("参数错误！",null);
        }
        return new HttpRes(workTaskService.saveTask(workTask));
    }

    @RequestMapping("delTaskByDate")
    public HttpRes delTask(String date,Integer workType){
        if (KwHelper.isNullOrEmpty(date) || workType==null){
            return new HttpRes("参数错误！",null);
        }
        LocalDate ldate=DateUtils.strToLocalDate(date,null);
        QueryWrapper<WorkTask> wrapper=new QueryWrapper<>();
        wrapper.eq("workDate",ldate).eq("workType",workType);
        return new HttpRes(workTaskService.remove(wrapper));
    }
    @RequestMapping("findTaskType")
    public HttpRes findTaskType(String date){
        if (KwHelper.isNullOrEmpty(date)){
            return new HttpRes("参数错误！",null);
        }
        LocalDate ldate=DateUtils.strToLocalDate(date,null);
        QueryWrapper<WorkTask> wrapper=new QueryWrapper<>();
        wrapper.eq("workDate",ldate);
        return new HttpRes(workTaskService.getOne(wrapper));
    }

    /**
     * 工资汇算
     * @param date 日期（月汇算:2020-09  日汇算:2020-09-xx）
     * @return
     */
    @RequestMapping("salary")
    public HttpRes salary(String date){
        if (KwHelper.isNullOrEmpty(date)){
            return new HttpRes("参数错误！",null);
        }
        LocalDate date1,date2;
        if (date.length()>7){
            date1=DateUtils.strToLocalDate(date,"yyyy-MM-dd");
            date2=date1;
        }else {
            date1=DateUtils.strToLocalDate(date+"-01","yyyy-MM-dd").with(TemporalAdjusters.firstDayOfMonth());
            date2=DateUtils.strToLocalDate(date+"-01","yyyy-MM-dd").with(TemporalAdjusters.lastDayOfMonth());
        }
        return new HttpRes(salaryService.calcSalary(date1,date2));
    }
}
