package commodity.controller;


import api.CommonResult;
import com.alibaba.fastjson.JSONObject;
import commodity.entity.ComSort;
import commodity.entity.Commodity;
import commodity.service.CommodityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utils.FastDFSUtil;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * <p>
 * 商品信息表 前端控制器
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-11
 */
@CrossOrigin
@Api(value = "商品controller", tags = "商品")
@RestController
public class CommodityController {

    @Resource
    private CommodityService commodityService;

    /**
     * @description: 通过商品编号查询一条记录
     * @param: com_id:  商品编号
     * @return: api.CommonResult
     * @author 黄俭豪
     * @date: 2020/10/15 21:47
     */
    @ApiOperation(value = "通过商品编号查询一条记录")
    @GetMapping("/commodity/{com_id}")
    public CommonResult findCommodityById(@PathVariable("com_id") Long com_id) {
        return commodityService.findCommodityById(com_id);
    }

    /**
     * @description: 查询全部商品
     * @param:
     * @return: api.CommonResult
     * @author 黄俭豪
     * @date: 2020/10/15 21:50
     */
    @ApiOperation(value = "查询全部商品")
    @GetMapping("/commodity")
    public CommonResult listCommodity() {
        return commodityService.listCommodity();
    }

    /**
     * @param pageSize: 页数量
     * @description: 分页 查询全部商品
     * @param: currentPage: 当前页码
     * @return: api.CommonResult
     * @author 黄俭豪
     * @date: 2020/10/15 21:52
     */
    @ApiOperation(value = "分页 查询全部商品")
    @GetMapping("/commodity/{currentPage}/{pageSize}")
    public CommonResult listCommodity(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        return commodityService.listCommodity(currentPage, pageSize);
    }

    @ApiOperation(value = "添加商品（不包含图片）;" +
            "格式为（双引号单引号都可以）：" +
            "{'comSortId': 4,'comName': '怡宝护肤水','comPrice': 45,'comStock': 65,'byWho': 0}" +
            "必须字段：商品分类编号、商品名称、价格、库存，用户编号")
    @PostMapping("/commodity")
    public CommonResult addCommodity(@RequestBody String jsonCommodity) throws IOException {
        //将字符串转为需要的对象
        Commodity commodity = JSONObject.parseObject(jsonCommodity, Commodity.class);
        //添加
        return commodityService.addCommodity(commodity);
    }

    @ApiOperation(value = "修改商品（不包括图片）;" +
            "格式为（双引号单引号都可以）：" +
            "{'comId':1,'comSortId': 4,'comName': '怡宝护肤水','comPrice': 45,'comStock': 65,'byWho': 0}" +
            "必须字段：商品编号，用户编号；" +
            "可修改字段：商品分类编号、商品名称、价格、库存，")
    @PutMapping("/commodity")
    public CommonResult updateCommodity(@RequestBody String jsonCommodity) {
        //将字符串转为需要的对象
        Commodity commodity = JSONObject.parseObject(jsonCommodity, Commodity.class);
        //修改
        return commodityService.updateCommodity(commodity);
    }

    @ApiOperation(value = "修改商品仓库数量（购买商品）;" +
            "必须字段：商品编号、减少的商品数量、用户编号")
    @PutMapping("/commodityNum/{com_id}/{com_num}/{userId}")
    public CommonResult updateCommodityNum(@PathVariable("com_id") Long com_id,
                                           @PathVariable("com_num") Integer com_num,
                                           @PathVariable("userId") Long userId) {
        //修改
        return commodityService.updateCommodity(com_id, com_num, userId);
    }

    @ApiOperation(value = "添加、修改商品图片;" +
            "格式为（双引号单引号都可以）：" +
            "必须字段：商品编号、上传图片、用户编号")
    @PutMapping("/commodityImages/{com_id}/{userId}")
    public CommonResult updateMainImg(@PathVariable("com_id") Long com_id,
                                      MultipartFile myFile,
                                      @PathVariable("userId") Long userId) throws IOException {
        //获取文件对应的字节数组
        byte[] buffFile = myFile.getBytes();
        //获取文件名
        String fileName = myFile.getOriginalFilename();
        //可能会出现问题因为有些文件可能没有扩展名，因此必要时需要做逻辑控制
        String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String[] result = FastDFSUtil.upload(buffFile, fileExtName);
        String mainImg =  result[0] + "/" + result[1];
        //"47.106.183.207/" +
        //修改
        return commodityService.updateMainImg(com_id, mainImg, userId);
    }

    @ApiOperation(value = "删除一条商品记录;" +
            "格式为（双引号单引号都可以）：" +
            "必须字段：商品编号、用户编号")
    @DeleteMapping("/commodity/{com_id}/{userId}")
    public CommonResult deleteCommodityl(@PathVariable("com_id") Long com_id,
                                         @PathVariable("userId") Long userId) {
        //删除
        return commodityService.deleteCommodity(com_id, userId);
    }
}

