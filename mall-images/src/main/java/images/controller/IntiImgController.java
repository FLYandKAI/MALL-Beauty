package images.controller;


import api.CommonResult;
import images.entity.ComImg;
import images.entity.IntiImg;
import images.service.ComImgService;
import images.service.IntiImgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utils.FastDFSUtil;

import java.io.IOException;

/**
 * <p>
 * 帖子图片信息表 前端控制器
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-02
 */
@RestController
@CrossOrigin
@Api(value = "帖子图片controller", tags = "帖子图片")
public class IntiImgController {

    @Autowired
    private IntiImgService intiImgService;


    /**
     * @param myFile: 参数 MultipartFile 为Spring提供的一个类，专门用于封装请求中的文件数据,属性名必须与表单中文件域的名字完全相同否则无法获取文件数据
     * @param userId: 用户编号
     * @description: 文件上传（这里用作图片上传）
     * @param: inti_id: 前端传过来的帖子id（不是图片id）
     * @return: api.CommonResult<images.entity.IntiImg>
     * @author 黄俭豪
     * @date: 2020/10/18 0:11
     */
    @ApiOperation(value = "添加一张图片；" +
            "必填项：帖子编号、上传一张图片、用户编号；" +
            "其他不必填；" +
            "图片编号自动生成")
    @PostMapping("/inti-img")
    public CommonResult<IntiImg> addIntiImg(Long inti_id, MultipartFile myFile, Long userId) throws IOException {

        //获取文件对应的字节数组
        byte[] buffFile = myFile.getBytes();
        //获取文件名
        String fileName = myFile.getOriginalFilename();
        Long fileSize = myFile.getSize();
        String fileType = myFile.getContentType();

        //可能会出现问题因为有些文件可能没有扩展名，因此必要时需要做逻辑控制
        String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String[] result = FastDFSUtil.upload(buffFile, fileExtName);
        //保存对象
        IntiImg intiImg = new IntiImg();
        intiImg.setIntiId(inti_id);//帖子编号
        intiImg.setIntiImgGroup(result[0]);//图片上传到服务器的组名（路径的一部分）
        intiImg.setIntiImgAddress(result[1]);//图片上传到服务器的地址

        intiImg.setIntiImgName(fileName);//商品上传名称
        intiImg.setByWho(userId);//操作者编号

        return intiImgService.addImg(intiImg);//保存到数据库
    }

    /**
     * @description: 查询所有
     * @param: inti_id:  帖子图片
     * @return: api.CommonResult<images.entity.IntiImg>
     * @author 黄俭豪
     * @date: 2020/10/18 0:13
     */
    @ApiOperation(value = "查询某个帖子的所有图片；" +
            "必填项：帖子编号")
    @GetMapping("/inti-img/{inti_id}")
    public CommonResult<IntiImg> findAllById(@PathVariable("inti_id") Long inti_id) {
        return intiImgService.findAllById(inti_id);
    }

    /**
     * @param currentPage: 当前页
     * @param pageSize:    页数据
     * @description: 分页查询
     * @param: inti_id: 帖子编号
     * @return: api.CommonResult<images.entity.IntiImg>
     * @author 黄俭豪
     * @date: 2020/10/18 0:14
     */
    @ApiOperation(value = "分页查询某个帖子的所有图片；" +
            "必填项：帖子编号、当前页、页数据；")
    @GetMapping("/inti-img/{inti_id}/{currentPage}/{pageSize}")
    public CommonResult<IntiImg> findAllById(@PathVariable("inti_id") Long inti_id,
                                             @PathVariable("currentPage") Integer currentPage,
                                             @PathVariable("pageSize") Integer pageSize) {
        return intiImgService.findAllById(inti_id, currentPage, pageSize);
    }

    /**
     * @description:  删除一张图片
     * @param: inti_img_id: 图片id
     * @param userId:  用户编号
     * @return: api.CommonResult<images.entity.IntiImg>
     * @author 黄俭豪
     * @date: 2020/10/18 0:17
     */
    @ApiOperation(value = "删除一张图片；" +
            "必填项：图片编号、用户编号；")
    @DeleteMapping("/inti-img/{inti_img_id}/{userId}")
    public CommonResult<IntiImg> deleteImg(@PathVariable("inti_img_id") Long inti_img_id,
                                          @PathVariable("userId") Long userId) {
        return intiImgService.deleteImg(inti_img_id, userId);
    }

}

