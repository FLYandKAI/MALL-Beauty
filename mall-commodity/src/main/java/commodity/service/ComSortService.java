package commodity.service;

import api.CommonResult;
import commodity.entity.ComSort;
import com.baomidou.mybatisplus.extension.service.IService;
import commodity.entity.ItemParam;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>
 * 商品分类信息表 服务类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-11
 */
public interface ComSortService extends IService<ComSort> {

    /**
     * @description: 查询商品分类--只有查询了商品分类，添加商品时才可以选择商品分类ID
     * @param:
     * @return: api.CommonResult<commodity.entity.ComSort>
     * @author 黄俭豪
     * @date: 2020/10/12 11:21
     */
    //value是存到redis的键值对的键值，值就是我们需要的缓存
    @Cacheable(value = "comSort")
    CommonResult<ComSort> listComSort();

    /**
     * @description: 重载分页 查询商品分类
     * @param: currentPage: 从哪一页开始的页数
     * @param currentSize: 一页的数据大小
     * @return: api.CommonResult<commodity.entity.ComSort>
     * @author 黄俭豪
     * @date: 2020/10/12 11:22
     */
    //value是存到redis的键值对的键值，值就是我们需要的缓存
    @Cacheable(value = "comSort")
    CommonResult<ComSort> listComSort(Integer currentPage, Integer currentSize);

    /**
     * @description: 通过商品分类id查询一条记录
     * @param: com_type_id:  商品分类id
     * @return: api.CommonResult<commodity.entity.ComSort>
     * @author 黄俭豪
     * @date: 2020/10/13 8:12
     */
    //value是存到redis的键值对的键值，值就是我们需要的缓存
    @Cacheable(value = "comSort")
    CommonResult<ComSort> findComSortById(Long com_type_id);

    /**
     * @description: 添加一条商品分类
     * @param: comSort:  商品分类实体类
     * @return: api.CommonResult<commodity.entity.ComSort>
     * @author 黄俭豪
     * @date: 2020/10/13 14:57
     */
    @CacheEvict(value = {"comSort"}, allEntries = true)
    CommonResult<ComSort> addComSort(ComSort comSort);

    /**
     * @description: 修改一条商品分类
     * @param: comSort:  商品分类实体类
     * @return: api.CommonResult<commodity.entity.ComSort>
     * @author 黄俭豪
     * @date: 2020/10/13 15:01
     */
    @CacheEvict(value = {"comSort"}, allEntries = true)
    CommonResult<ComSort> updateComSort(ComSort comSort);

    /** 
     * @description: 删除一条商品分类--注意是作为父级分类的话不能被删除
     * @param: com_type_id: 分类编号
     * @param userId:  用户编号
     * @return: api.CommonResult<commodity.entity.ComSort> 
     * @author 黄俭豪
     * @date: 2020/10/13 15:03
     */ 
    @CacheEvict(value = {"comSort"}, allEntries = true)
    CommonResult<ComSort> deleteComSort(Long com_type_id, Long userId);
}
