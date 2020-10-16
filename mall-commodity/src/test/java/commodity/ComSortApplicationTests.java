package commodity;

import api.CommonResult;
import com.alibaba.fastjson.JSON;
import commodity.entity.ComSort;
import commodity.entity.Commodity;
import commodity.mapper.CommodityMapper;
import commodity.service.ComSortService;
import commodity.service.impl.CommodityServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ComSortApplicationTests {

    @Autowired
    private ComSortService comSortService;


    @Test
    public void testFind(){
        CommonResult<ComSort> comSortById = comSortService.findComSortById(1315941516841082882L);
        System.out.println(JSON.toJSONString(comSortById));

    }

}
