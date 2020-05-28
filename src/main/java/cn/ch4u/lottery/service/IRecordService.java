package cn.ch4u.lottery.service;

import cn.ch4u.lottery.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ch
 * @since 2020-05-28
 */
public interface IRecordService extends IService<Record> {
    void addBatchUnique(List<Record> list);
}
