package cn.ch4u.workbook.service.impl;

import cn.ch4u.workbook.entity.WorkTask;
import cn.ch4u.workbook.mapper.WorkTaskMapper;
import cn.ch4u.workbook.service.IWorkTaskService;
import cn.ch4u.workbook.util.KwHelper;
import cn.ch4u.workbook.vo.Summary;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ch
 * @since 2020-09-01
 */
@Service
public class WorkTaskServiceImpl extends ServiceImpl<WorkTaskMapper, WorkTask> implements IWorkTaskService {
    @Resource
    private WorkTaskMapper taskMapper;

    @Override
    public int saveTask(WorkTask task) {
        return taskMapper.updateOrSave(task);
    }

    @Override
    public IPage<Summary> taskSummary(WorkTask task,int pageIdx,int pageSize) {
                Page<WorkTask> page = new Page<>(pageIdx,pageSize);
        IPage<Summary> mapIPage = taskMapper.taskSummary(page, task);
        return mapIPage;
    }
}
