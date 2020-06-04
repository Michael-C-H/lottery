package cn.ch4u.lottery.service.impl;

import cn.ch4u.lottery.constant.LotteryTypeEnum;
import cn.ch4u.lottery.entity.RecommendRes;
import cn.ch4u.lottery.entity.Record;
import cn.ch4u.lottery.mapper.RecordMapper;
import cn.ch4u.lottery.service.ILotteryDataSrc;
import cn.ch4u.lottery.service.ILotteryRecommend;
import cn.ch4u.lottery.service.IRecordService;
import cn.ch4u.lottery.util.KwHelper;
import cn.ch4u.lottery.util.LotteryUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
    private static final Logger logger = LoggerFactory.getLogger(RecordServiceImpl.class);

    @Autowired
    Environment env;

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

    @Override
    @Transactional
    public void fillHistory() {
        //获取配置文件个数*类型数
        int conf_f=findNeedleastRecords();
        int conf=conf_f * LotteryTypeEnum.values().length;
        //获取数据库数据条数
        int db=this.count();
        //如果数据数据大于等于配置数据库，则不进行填充
        if (db>=conf)return;
        //清空库
        this.remove(Wrappers.emptyWrapper());
        //获取数据
        ILotteryDataSrc srcApi= LotteryUtil.getSrcApi(env);
        if (srcApi==null)return;
        List<Record> list=srcApi.historyDatas(Arrays.asList(LotteryTypeEnum.values()),conf_f);
        //批量入库
        if (!KwHelper.isNullOrEmpty(list)){
            this.saveBatch(list);
        }
    }

    @Override
    public RecommendRes recommend(LotteryTypeEnum typeEnum) {
        if (typeEnum == null) return null;
        //获取类型的历史数据
        QueryWrapper<Record> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("typeKey",typeEnum.getKey()).orderByDesc("periodNo");
        Page<Record> page=new Page<>(1,findNeedleastRecords(),false);
        Page<Record> plist=this.page(page,queryWrapper);
        if (plist == null)return null;
        List<Record> list=plist.getRecords();
        if (KwHelper.isNullOrEmpty(list)) return null;
        //获取数据
        ILotteryRecommend srcApi= LotteryUtil.getLotteryRecommend(env);
        if (srcApi==null)return null;
        return srcApi.recommend(typeEnum,list);
    }


    /**
     * 返回计算所需的条数
     * @return
     */
    private int findNeedleastRecords(){
        int size=100;
        try {
            size=Integer.parseInt(env.getProperty("custom.config.needRecords"));
        }catch (Exception e){
            logger.warn("获取配置needRecords失败，采用默认配置100条。",e);
        }
        return size;
    }
}
