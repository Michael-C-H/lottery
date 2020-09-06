package cn.ch4u.workbook.service;

import cn.ch4u.workbook.entity.WorkSheet;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ch
 * @since 2020-09-01
 */
public interface IWorkSheetService extends IService<WorkSheet> {
    /**
     * 保存或更新考勤记录
     * @param list
     * @return
     */
    List<WorkSheet> saveSheets(List<WorkSheet> list);

}
