package images.service;

import images.entity.ComImg;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;


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


    /*@Cacheable：取缓存

    @CachePut：修改缓存

    @CacheEvict：删除缓存，allEntries：true表示清除value中的全部缓存，默认为false。*/

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


    @CacheEvict(value = "comImg",allEntries = true)
    Integer deleteImg(Long ComImgId);
    
    /** 
     * @description: 查询所有图片 
     * @param:  
     * @return: java.util.List<images.entity.ComImg> 
     * @author 黄俭豪
     * @date: 2020/10/2 21:28
     */ 


    @Cacheable(value = "comImg")
    List<ComImg> findAll();
}
