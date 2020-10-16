package commodity;

import commodity.entity.Commodity;
import commodity.mapper.CommodityMapper;
import commodity.service.impl.CommodityServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class CommodityApplicationTests {

    @Autowired
    private CommodityServiceImpl commodityServiceImpl;

    @Resource
    private CommodityMapper commodityMapper;


    @Test
    public void testFind(){
        // System.out.println(commodityServiceImpl.listCommodity(0,3));
        // System.out.println("=-=======");
        // System.out.println(commodityServiceImpl.listCommodity(2,1));
        Commodity commodity = commodityMapper.selectById(1L);
        String groupName = commodity.getMainImage().substring(0,commodity.getMainImage().indexOf("/"));
        System.out.println("=============================");
        System.out.println(groupName);
        String addressName = commodity.getMainImage().substring(
                commodity.getMainImage().lastIndexOf("/"), commodity.getMainImage().length());
        System.out.println(addressName);
        System.out.println("=============================");
    }

    @Test
    public void testAdd(){
        Commodity commodity = new Commodity();
        commodity.setComSortId(3L);
        commodity.setComName("韩素黑面膜");
        commodity.setComPrice(100);
        commodity.setComStock(100);
        commodity.setMainImage(null);
        commodity.setByWho(6544L);
        commodityMapper.insert(commodity);
    }



}
