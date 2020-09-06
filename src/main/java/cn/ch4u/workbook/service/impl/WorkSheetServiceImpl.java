package cn.ch4u.workbook.service.impl;

import cn.ch4u.workbook.entity.WorkSheet;
import cn.ch4u.workbook.mapper.WorkSheetMapper;
import cn.ch4u.workbook.service.IWorkSheetService;
import cn.ch4u.workbook.util.KwHelper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ch
 * @since 2020-09-01
 */
@Service
public class WorkSheetServiceImpl extends ServiceImpl<WorkSheetMapper, WorkSheet> implements IWorkSheetService {
    @Resource
    private WorkSheetMapper sheetMapper;

    @Override
    @Transactional
    public List<WorkSheet> saveSheets(List<WorkSheet> list){
        if (KwHelper.isNullOrEmpty(list)){
            return list;
        }
        for (int i = 0; i < list.size(); i++) {
            WorkSheet workSheet =  list.get(i);
            sheetMapper.updateOrSave(workSheet);
        }
        return list;
    }

}
