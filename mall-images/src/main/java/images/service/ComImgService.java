package images.service;

import images.entity.ComImg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品图片信息表 服务类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-02
 */
public interface ComImgService extends IService<ComImg> {
    /**
     * @description: 插入一张商品图片
     * @param: comImg:  商品图片实体类
     * @return: java.lang.Integer
     * @author 黄俭豪
     * @date: 2020/10/2 19:19
     */
    Integer addImg(ComImg comImg);


    /***
     * @description: 删除一张商品图片 (逻辑删除)
     * @param: ComImgId:  商品图片ID
     * @return: java.lang.Integer
     * @author 黄俭豪
     * @date: 2020/10/2 20:02
     */
    Integer deleteImg(Long ComImgId);
    
    /** 
     * @description: 查询所有图片 
     * @param:  
     * @return: java.util.List<images.entity.ComImg> 
     * @author 黄俭豪
     * @date: 2020/10/2 21:28
     */ 
    List<ComImg> findAll();
}
