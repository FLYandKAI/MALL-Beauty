package commodity.controller;


import api.CommonResult;
import com.alibaba.fastjson.JSONObject;
import commodity.entity.ComSort;
import commodity.entity.ItemParam;
import commodity.service.ItemParamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 商品详细信息表 前端控制器
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-11
 */
@CrossOrigin
@Api(value = "商品参数（商品规格）controller", tags = "商品参数（商品规格）")
@RestController
public class ItemParamController {

    @Resource
    private ItemParamService itemParamService;

    /**
     * @description: 通过具体商品编号查询商品参数（规格）
     * @param: com_id:  商品编号
     * @return: api.CommonResult
     * @author 黄俭豪
     * @date: 2020/10/15 10:08
     */
    @ApiOperation(value = "通过具体商品编号查询商品参数（规格）")
    @GetMapping("/item-param/{com_id}")
    public CommonResult findItemParamById(@PathVariable("com_id") Long com_id) {
        return itemParamService.findItemParamById(com_id);
    }

    /**
     * @description: 查询全部商品参数（规格）
     * @param:
     * @return: api.CommonResult
     * @author 黄俭豪
     * @date: 2020/10/15 10:09
     */
    @ApiOperation(value = "查询全部商品参数（规格）")
    @GetMapping("/item-param")
    public CommonResult findItemParam() {
        return itemParamService.findItemParam();
    }

    /**
     * @param pageSize: 页数
     * @description: 分页查询全部商品参数
     * @param: currentPage: 当前页
     * @return: api.CommonResult
     * @author 黄俭豪
     * @date: 2020/10/15 10:17
     */
    @ApiOperation(value = "分页 查询全部商品参数（规格）")
    @GetMapping("/item-param/{currentPage}/{pageSize}")
    public CommonResult findItemParam(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        return itemParamService.findItemParam(currentPage, pageSize);
    }

    /*@ApiOperation(value = "通过具体商品编号添加商品参数（规格）;" +
            "必要字段：操作者、商品编号（从商品信息拿）、商品购买参数；" +
            "其他字段不必填")
    @PostMapping("/item-param")
    public CommonResult addItemParam(ItemParam itemParam){
        return itemParamService.addItemParam(itemParam);
    }*/

    /**
     * @description: 通过具体商品编号添加商品参数（规格）
     * @param: jsonItemParam:  json字符串
     * @return: api.CommonResult
     * @author 黄俭豪
     * @date: 2020/10/15 10:18
     */
    @ApiOperation(value = "通过具体商品编号添加商品参数（规格）;" +
            "格式为（双引号单引号都可以）：" +
            "{'byWho':22522,'item_param_id':65464,'com_id':23,'item_param_data':'这是数据'}" +
            "必须字段：用户编号、商品参数编号（可不填、自动生成）、商品编号、参数数据")
    @PostMapping("/item-param")
    public CommonResult addItemParam(@RequestBody String jsonItemParam) {
        //将字符串转为需要的对象
        ItemParam itemParam = JSONObject.parseObject(jsonItemParam, ItemParam.class);
        //插入
        return itemParamService.addItemParam(itemParam);
    }

    /*@ApiOperation(value = "通过具体商品参数编号修改商品参数（规格）;" +
            "必要字段：操作者（有修改就填）、商品编号（从商品信息拿）、商品购买参数（有修改就填）；" +
            "其他字段不必填")
    @PutMapping("/item-param")
    public CommonResult updateItemParam(ItemParam itemParam){
        return itemParamService.updateItemParam(itemParam);
    }*/

    /**
     * @description: 通过具体商品编号修改商品参数（规格）
     * @param: jsonItemParam:
     * @return: api.CommonResult
     * @author 黄俭豪
     * @date: 2020/10/15 10:18
     */
    @ApiOperation(value = "通过具体商品编号修改商品参数（规格）;" +
            "格式为（双引号单引号都可以）：" +
            "{'byWho':22522,'com_id':23,'item_param_data':'这是数据'}" +
            "必须字段：用户编号、商品编号、参数数据")
    @PutMapping("/item-param")
    public CommonResult updateItemParam(@RequestBody String jsonItemParam) {
        //将字符串转为需要的对象
        ItemParam itemParam = JSONObject.parseObject(jsonItemParam, ItemParam.class);
        //更新
        return itemParamService.updateItemParam(itemParam);
    }

    /**
     * @param userId: 用户编号
     * @description: 通过具体商品编号删除商品参数（规格）
     * @param: com_id: 商品编号
     * @return: api.CommonResult
     * @author 黄俭豪
     * @date: 2020/10/15 10:20
     */
    @ApiOperation(value = "通过具体商品编号删除商品参数（规格）;" +
            "必要字段：操作者、商品编号（从商品信息拿）；" +
            "其他字段不必填")
    @DeleteMapping("/item-param/{com_id}/{userId}")
    public CommonResult deleteItemParam(@PathVariable("com_id") Long com_id, @PathVariable("userId") Long userId) {
        return itemParamService.deleteItemParam(com_id, userId);
    }
}

