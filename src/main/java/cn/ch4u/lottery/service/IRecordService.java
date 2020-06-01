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
    /**
     * 批量添加记录（类型+期号排重）
     * @param list
     */
    void addBatchUnique(List<Record> list);
    /**
     * 填充历史数据
     */
    void fillHistory();
}
