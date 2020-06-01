package cn.ch4u.lottery.config;

import cn.ch4u.lottery.service.IRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 启动后做的事
 */
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
    private final static Logger logger = LoggerFactory.getLogger(ApplicationRunnerImpl.class);
    @Autowired
    private IRecordService recordService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //初始化历史数据
        logger.info("初始化历史数据……");
        recordService.fillHistory();
        logger.info("初始化历史数据完成！");
    }
}