package cn.ch4u.workbook.mapper;

import cn.ch4u.workbook.entity.WorkSheet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ch
 * @since 2020-09-01
 */
public interface WorkSheetMapper extends BaseMapper<WorkSheet> {
    int updateOrSave(WorkSheet workSheet);

}
