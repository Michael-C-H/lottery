package cn.ch4u.lottery;

import cn.ch4u.lottery.util.LotteryUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
@RunWith(SpringRunner.class)
public class CommonTest {
    @Test
    public void testSort(){
        Map<String,Integer> map=new HashMap<>(3);
        map.put("abc",2);
        map.put("def",1);
        map.put("333",3);
        System.out.println(map);
        System.out.println(LotteryUtil.sortMapByValues(map,false));

    }
}
