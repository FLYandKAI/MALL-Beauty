package commodity.controller;


import api.CommonResult;
import com.alibaba.fastjson.JSONObject;
import commodity.entity.ComSort;
import commodity.service.ComSortService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 商品分类信息表 前端控制器
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-11
 */
@CrossOrigin
@Api(value = "商品分类controller", tags = "商品分类")
@RestController
public class ComSortController {

    @Resource
    private ComSortService comSortService;

    /**
     * @description: 通过编号查询一条记录
     * @param: com_type_id:  分类编号
     * @return: api.CommonResult
     * @author 黄俭豪
     * @date: 2020/10/15 9:49
     */
    @ApiOperation(value = "通过具体商品分类编号查询")
    @GetMapping("/com-sort/{com_type_id}")
    public CommonResult findComSortById(@PathVariable("com_type_id") Long com_type_id) {
        return comSortService.findComSortById(com_type_id);
    }

    /**
     * @description: 查询全部记录
     * @param:
     * @return: api.CommonResult
     * @author 黄俭豪
     * @date: 2020/10/15 9:49
     */
    @ApiOperation(value = "查询全部商品分类")
    @GetMapping("/com-sort")
    public CommonResult listComSort() {
        return comSortService.listComSort();
    }

    /**
     * @param pageSize: 页数据
     * @description: 分页查询全部记录
     * @param: currentPage: 当前页
     * @return: api.CommonResult
     * @author 黄俭豪
     * @date: 2020/10/15 9:50
     */
    @ApiOperation(value = "分页 查询全部商品分类")
    @GetMapping("/com-sort/{currentPage}/{pageSize}")
    public CommonResult listComSort(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        return comSortService.listComSort(currentPage, pageSize);
    }

    /*@ApiOperation(value = "通过具体商品分类编号添加;" +
            "必要字段：操作者、商品分类编号（不填则默认生成）、商品分类名称、商品父类编号（从商品分类信息拿）；" +
            "其他字段不必填")
    @PostMapping("/com-sort")
    public CommonResult addComSort(ComSort comSort){
        return comSortService.addComSort(comSort);
    }*/

    /*
    使用json字符串的方式    格式如下
    必填          {"byWho":2222,
    必填          "comTypeId":1315941516841082882,
    必填          "comTypeName":"aaa爽肤水",
    必填          "comTypeParentId":2,
    不必填        "comTypeStatus":1,
    不必填        "createTime":1602579881000,
    不必填        "deleted":0,
    不必填        "lastVersion":1,
    不必填        "updateTime":1602579881000}
     */

    /**
     * @description: 添加一条记录
     * @param: jsonComSort:  json格式的字符串，里面的字段是必须的
     * @return: api.CommonResult
     * @author 黄俭豪
     * @date: 2020/10/15 9:52
     */
    @ApiOperation(value = "添加商品分类;" +
            "格式为（双引号单引号都可以）：" +
            "{'byWho':22522,'comTypeId':6,'comTypeName':'卡姿兰爽肤水','comTypeParentId':2}" +
            "必须字段：用户编号、商品分类编号（这个不填的话自动生成）、分类名称、父类编号")
    @PostMapping("/com-sort")
    public CommonResult addComSort(@RequestBody String jsonComSort) {
        //将字符串转为需要的对象
        ComSort comSort = JSONObject.parseObject(jsonComSort, ComSort.class);
        //添加
        return comSortService.addComSort(comSort);
    }

    /*@ApiOperation(value = "通过具体商品分类编号修改;" +
            "可填字段：商品分类编号（必填）、操作者（必填）、商品分类名称（有修改就填）、商品父类编号（有修改就填）、商品状态（1为可用，0为不可用，有修改就填）；" +
            "其他字段不必填")
    @PutMapping("/com-sort")
    public CommonResult updateComSort(ComSort comSort){
        return comSortService.updateComSort(comSort);
    }*/

    /**
     * @description: 修改一条记录
     * @param: jsonComSort:  json格式的字符串，里面的字段是必须的
     * @return: api.CommonResult
     * @author 黄俭豪
     * @date: 2020/10/15 9:52
     */
    @ApiOperation(value = "通过具体商品分类编号修改;" +
            "格式为（双引号单引号都可以）：" +
            "{'byWho':22522,'comTypeId':6,'comTypeName':'卡姿兰爽肤水','comTypeParentId':2}" +
            "必须字段：用户编号、商品分类编号、分类名称、父类编号")
    @PutMapping("/com-sort")
    public CommonResult updateComSort(@RequestBody String jsonComSort) {
        //将字符串转为需要的对象
        ComSort comSort = JSONObject.parseObject(jsonComSort, ComSort.class);
        //更新
        return comSortService.updateComSort(comSort);
    }

    /**
     * @param userId: 用户编号
     * @description: 删除一条记录
     * @param: com_type_id: 商品分类编号
     * @return: api.CommonResult
     * @author 黄俭豪
     * @date: 2020/10/15 9:53
     */
    @ApiOperation(value = "通过具体商品参数编号删除商品参数（规格）;" +
            "必要字段：操作者、商品分类编号；" +
            "其他字段不必填")
    @DeleteMapping("/com-sort/{com_type_id}/{userId}")
    public CommonResult deleteComSort(@PathVariable("com_type_id") Long com_type_id, @PathVariable("userId") Long userId) {
        return comSortService.deleteComSort(com_type_id, userId);
    }
}

