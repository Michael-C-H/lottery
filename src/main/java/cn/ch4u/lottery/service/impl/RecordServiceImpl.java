package cn.ch4u.lottery.service.impl;

import cn.ch4u.lottery.entity.Record;
import cn.ch4u.lottery.mapper.RecordMapper;
import cn.ch4u.lottery.service.IRecordService;
import cn.ch4u.lottery.util.KwHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ch
 * @since 2020-05-28
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

    @Override
    public void addBatchUnique(List<Record> list) {
        if (KwHelper.isNullOrEmpty(list))return;
        Record record;
        for (int i = 0; i < list.size(); i++) {
            record =  list.get(i);
            QueryWrapper<Record> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("periodNo",record.getPeriodNo()).eq("typeKey",record.getTypeKey());
            if(this.count(queryWrapper)>0){
                continue;
            }
            this.save(record);

        }

    }
}
