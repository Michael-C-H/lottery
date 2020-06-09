package cn.ch4u.lottery;

import cn.ch4u.lottery.service.IRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LotteryApplicationTests {
    @Autowired
    IRecordService recordService;

    @Test
    void contextLoads() {
    }

    @Test
    void testRecommend(){
        //System.out.println(recordService.recommend(LotteryTypeEnum.DaLeTou));
    }

}
