package cn.ch4u.workbook.service;

import cn.ch4u.workbook.entity.WorkSheet;
import cn.ch4u.workbook.entity.WorkTask;
import cn.ch4u.workbook.vo.Summary;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ch
 * @since 2020-09-01
 */
public interface IWorkTaskService extends IService<WorkTask> {
    /**
     * 保存或更新工作内容
     * @param task
     * @return
     */
    int saveTask(WorkTask task);

    /**
     * 任务摘要
     * @return
     */
    IPage<Summary> taskSummary(WorkTask task,int pageIdx,int pageSize);
}
