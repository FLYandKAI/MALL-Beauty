package images.service;

import api.CommonResult;
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
     * @return: api.CommonResult<images.entity.ComImg>
     * @author 黄俭豪
     * @date: 2020/10/13 22:00
     */
    @CacheEvict(value = "comImg", allEntries = true)
    CommonResult<ComImg> addImg(ComImg comImg);

    /**
     * @description: 删除一张商品图片 (逻辑删除)
     * @param: ComImgId:  商品图片ID
     * @param: userId:  用户编号
     * @return: api.CommonResult<images.entity.ComImg>
     * @author 黄俭豪
     * @date: 2020/10/13 22:01
     */
    @CacheEvict(value = "comImg", allEntries = true)
    CommonResult<ComImg> deleteImg(Long ComImgId,Long userId);

    /**
     * @description: 根据商品编号查询图片，一个商品编号对应多张图片
     * @param: com_id:  商品编号
     * @return: api.CommonResult<images.entity.ComImg>
     * @author 黄俭豪
     * @date: 2020/10/13 22:02
     */
    @Cacheable(value = "comImg")
    CommonResult<ComImg> findAllById(Long com_id);

    /**
     * @param currentPage: 当前页
     * @param currentSize: 页数据
     * @description: 分页  根据商品编号查询图片，一个商品编号对应多张图片
     * @param: com_id: 商品编号
     * @return: api.CommonResult<images.entity.ComImg>
     * @author 黄俭豪
     * @date: 2020/10/13 22:04
     */
    @Cacheable(value = "comImg")
    CommonResult<ComImg> findAllById(Long com_id, Integer currentPage, Integer currentSize);

}
