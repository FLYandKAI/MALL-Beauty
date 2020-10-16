package images.controller;


import api.CommonResult;
import images.entity.ComImg;
import images.service.ComImgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import utils.FastDFSUtil;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 商品图片信息表 前端控制器
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-02
 */
@CrossOrigin
@Api(value = "商品图片controller",tags = "商品图片")
@RestController
@RequestMapping("/com-img")
public class ComImgController {

    @Autowired
    private ComImgService comImgService;

    /**
     * @description:  文件上传（这里用作图片上传）
     * @param: com_id: 前端传过来的商品id（不是图片id）
     * @param myFile: 参数 MultipartFile 为Spring提供的一个类，专门用于封装请求中的文件数据,属性名必须与表单中文件域的名字完全相同否则无法获取文件数据
     * @param userId:  用户编号
     * @return: api.CommonResult<images.entity.ComImg>
     * @author 黄俭豪
     * @date: 2020/10/13 23:29
     */
    @ApiOperation(value = "添加一张图片；" +
            "必填项：商品编号、上传一张图片、用户编号；" +
            "其他不必填；" +
            "图片编号自动生成")
    @PostMapping("/addComImg")
    public CommonResult<ComImg> addComImg(Long com_id, MultipartFile myFile,Long userId) throws IOException {

        //获取文件对应的字节数组
        byte[] buffFile=myFile.getBytes();
        //获取文件名
        String fileName=myFile.getOriginalFilename();
        Long fileSize=myFile.getSize();
        String fileType=myFile.getContentType();

        //可能会出现问题因为有些文件可能没有扩展名，因此必要时需要做逻辑控制
        String fileExtName=fileName.substring(fileName.lastIndexOf(".")+1);
        String[] result= FastDFSUtil.upload(buffFile,fileExtName);
        ComImg comImg = new ComImg();
        // comImg.setComImgId();商品图片编号，这里让他自动生成？
        comImg.setComId(com_id);//商品编号
        comImg.setComImgGroup(result[0]);//商品上传到服务器的组名（路径的一部分）
        comImg.setComImgAddress(result[1]);//商品上传到服务器的地址
        /*
        拼接下载地址：47.106.183.207/result[0]/result[1]
         */

        comImg.setComImgName(fileName);//商品上传名称
        comImg.setByWho(userId);//操作者编号

        return comImgService.addImg(comImg);//保存到数据库
    }

    /**
     * @description:  查询所有
     * @param: com_id:  商品编号
     * @return: api.CommonResult<images.entity.ComImg>
     * @author 黄俭豪
     * @date: 2020/10/13 23:30
     */
    @ApiOperation(value = "查询某个商品的所有图片；" +
            "必填项：商品编号")
    @GetMapping("/findAllById/{com_id}")
    public CommonResult<ComImg> findAllById(@PathVariable("com_id") Long com_id){
        return comImgService.findAllById(com_id);
    }

    @ApiOperation(value = "分页查询某个商品的所有图片；" +
            "必填项：商品编号、当前页、页数据；")
    @GetMapping("/findAllById/{com_id}/{currentPage}/{pageSize}")
    public CommonResult<ComImg> findAllById(@PathVariable("com_id") Long com_id,
                                            @PathVariable("currentPage") Integer currentPage,
                                            @PathVariable("pageSize") Integer pageSize){
        return comImgService.findAllById(com_id,currentPage,pageSize);
    }

    /**
     * @description:  删除一张图片
     * @param: com_img_id: 图片id
     * @param userId:  用户编号
     * @return: api.CommonResult<images.entity.ComImg>
     * @author 黄俭豪
     * @date: 2020/10/13 23:30
     */
    @ApiOperation(value = "分页查询某个商品的所有图片；" +
            "必填项：图片编号、用户编号；")
    @DeleteMapping("/delete/{com_img_id}/{userId}")
    public CommonResult<ComImg> deleteImg(@PathVariable("com_img_id") Long com_img_id,
                                          @PathVariable("userId") Long userId){
        return comImgService.deleteImg(com_img_id,userId);
    }
}

