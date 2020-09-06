package cn.ch4u.workbook.mapper;

import cn.ch4u.workbook.entity.WorkSheet;
import cn.ch4u.workbook.entity.WorkTask;
import cn.ch4u.workbook.vo.Summary;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ch
 * @since 2020-09-01
 */
public interface WorkTaskMapper extends BaseMapper<WorkTask> {
    int updateOrSave(WorkTask workTask);

    /**
     * 任务摘要列表
     * @return
     */
    IPage<Summary> taskSummary(IPage<WorkTask> page,@Param("param") WorkTask task);
}
