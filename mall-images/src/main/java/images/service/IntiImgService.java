package images.service;

import api.CommonResult;
import images.entity.ComImg;
import images.entity.IntiImg;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * <p>
 * 帖子图片信息表 服务类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-02
 */
public interface IntiImgService extends IService<IntiImg> {

      /*@Cacheable：取缓存

    @CachePut：修改缓存

    @CacheEvict：删除缓存，allEntries：true表示清除value中的全部缓存，默认为false。*/


    /**
     * @description:  插入一张帖子图片
     * @param: intiImg:  帖子图片实体类
     * @return: api.CommonResult<images.entity.IntiImg>
     * @author 黄俭豪
     * @date: 2020/10/17 19:54
     */
    @CacheEvict(value = "intiImg", allEntries = true)
    CommonResult<IntiImg> addImg(IntiImg intiImg);

    /**
     * @description:  删除一张帖子图片 (逻辑删除)
     * @param: IntiImg: 帖子图片编号
     * @param userId:  用户编号
     * @return: api.CommonResult<images.entity.IntiImg>
     * @author 黄俭豪
     * @date: 2020/10/17 19:55
     */
    @CacheEvict(value = "intiImg", allEntries = true)
    CommonResult<IntiImg> deleteImg(Long IntiImgId, Long userId);

    /**
     * @description:  通过帖子编号查询全部图片
     * @param: inti_id:  帖子编号
     * @return: api.CommonResult<images.entity.IntiImg>
     * @author 黄俭豪
     * @date: 2020/10/17 19:56
     */
    @Cacheable(value = "intiImg")
    CommonResult<IntiImg> findAllById(Long inti_id);

    /**
     * @description:  分页  根据帖子编号查询图片，一个帖子编号对应多张图片
     * @param: inti_id: 帖子编号
     * @param currentPage: 当前页
     * @param currentSize:  页数据
     * @return: api.CommonResult<images.entity.IntiImg>
     * @author 黄俭豪
     * @date: 2020/10/17 19:57
     */
    @Cacheable(value = "intiImg")
    CommonResult<IntiImg> findAllById(Long inti_id, Integer currentPage, Integer currentSize);
}
