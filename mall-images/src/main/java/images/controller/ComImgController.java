package images.controller;


import images.entity.ComImg;
import images.service.ComImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
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
@RestController
@RequestMapping("/com-img")
public class ComImgController {

    @Autowired
    private ComImgService comImgService;


    /**
     * @description:  文件上传（这里用作图片上传）
     * @param: id: 前端传过来的商品id（不是图片id）
     * @param myFile: 参数 MultipartFile 为Spring提供的一个类，专门用于封装请求中的文件数据,属性名必须与表单中文件域的名字完全相同否则无法获取文件数据
     * @param userId:操作者编号
     * @return: java.lang.String
     * @author 黄俭豪
     * @date: 2020/10/2 19:19
     */
    @PostMapping("/upload")
    public String upload(Long id, MultipartFile myFile,Long userId) throws IOException {

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
        comImg.setComId(id);//商品编号
        comImg.setComImgGroup(result[0]);//商品上传到服务器的组名（路径的一部分）
        comImg.setComImgAddress(result[1]);//商品上传到服务器的地址
        comImg.setComImgName(fileName);//商品上传名称
        comImg.setByWho(userId);//操作者编号

        comImgService.addImg(comImg);//保存到数据库
        return "success";
    }

    /**
     * @description: 查询所有
     * @param:
     * @return: java.util.List<images.entity.ComImg>
     * @author 黄俭豪
     * @date: 2020/10/2 21:54
     */
    @RequestMapping("/findAllComImg")
    public List<ComImg> findAllComImg(){
        return comImgService.findAll();
    }

    /**
     * @description: 删除一张图片
     * @param: id: 图片id
     * @return: java.lang.String
     * @author 黄俭豪
     * @date: 2020/10/2 21:54
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        comImgService.deleteImg(id);
        return "success";
    }
}

