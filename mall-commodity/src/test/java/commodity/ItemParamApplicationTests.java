package commodity;

import api.CommonResult;
import commodity.entity.ItemParam;
import commodity.service.ItemParamService;
import org.codehaus.jettison.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemParamApplicationTests {

    @Autowired
    private ItemParamService itemParamService;

    //查询
    @Test
    public void testFind(){
        System.out.println(itemParamService.findItemParamById(1L));
        System.out.println(itemParamService.findItemParamById(2L));
    }

   /* //增加
    @Test
    public void insertItem(){
        CommonResult<ItemParam> param = itemParamService.addItemParam(3L, 3L, "这里需要一个json字符串", 65465416546L);
        System.out.println(param);
    }

    //更新
    @Test
    public void updateItem(){
        CommonResult<ItemParam> param = itemParamService.updateItemParam(3L, 3L, "方天画戟11", 65461147L);
        System.out.println(param);
    }*/

    //删除
    @Test
    public void deleteItem(){
        CommonResult<ItemParam> param = itemParamService.deleteItemParam(3L, 34654646L);
        System.out.println(param);
    }



}
