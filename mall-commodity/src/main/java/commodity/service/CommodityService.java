package commodity.service;

import api.CommonResult;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import commodity.entity.Commodity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * <p>
 * 商品信息表 服务类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-11
 */
public interface CommodityService extends IService<Commodity> {

    /*@Cacheable：取缓存

    @CachePut：修改缓存

    @CacheEvict：删除缓存，allEntries：true表示清除value中的全部缓存，默认为false。*/

    /**
     * @description: 通过商品编号查询
     * @param: com_id:  商品编号
     * @return: api.CommonResult<commodity.entity.Commodity>
     * @author 黄俭豪
     * @date: 2020/10/13 20:10
     */
    @Cacheable(value = "commodity")
    CommonResult<Commodity> findCommodityById(Long com_id);

    /**
     * @description: 查询所有商品（不包括商品参数与商品分类）
     * @param:
     * @return: java.util.List<commodity.entity.Commodity>
     * @author 黄俭豪
     * @date: 2020/10/11 21:47
     */
    //value是存到redis的键值对的键值，值就是我们需要的缓存
    @Cacheable(value = "commodity")
    CommonResult<Commodity> listCommodity();

    /**
     * @param currentSize: 一页的数据大小
     * @description: 重载--分页查询所有商品（不包括商品参数与商品分类）
     * @param: currentPage: 从哪一页开始的页数
     * @return: java.util.List<commodity.entity.Commodity>
     * @author 黄俭豪
     * @date: 2020/10/11 21:47
     */
    //value是存到redis的键值对的键值，值就是我们需要的缓存
    @Cacheable(value = "commodity")
    CommonResult<Commodity> listCommodity(Integer currentPage, Integer currentSize);

    /**
     * @description: 添加一个商品
     * @param: commodity:  商品实体类
     * @return: api.CommonResult<commodity.entity.Commodity>
     * @author 黄俭豪
     * @date: 2020/10/13 21:50
     */
    @CacheEvict(value = {"commodity"}, allEntries = true)
    CommonResult<Commodity> addCommodity(Commodity commodity);

    /**
     * @description: 修改一个商品（不包括图片）
     * @param: commodity:  商品实体类
     * @return: api.CommonResult<commodity.entity.Commodity>
     * @author 黄俭豪
     * @date: 2020/10/13 21:51
     */
    @CacheEvict(value = {"commodity"}, allEntries = true)
    CommonResult<Commodity> updateCommodity(Commodity commodity);

    /**
     * @param com_num: 用户购买数量
     * @param userId:  用户编号
     * @description: 购买商品时库存作相应操作（减少数据库商品数量）
     * @param: com_id: 商品编号
     * @return: api.CommonResult<commodity.entity.Commodity>
     * @author 黄俭豪
     * @date: 2020/10/15 20:14
     */
    @CacheEvict(value = {"commodity"}, allEntries = true)
    CommonResult<Commodity> updateCommodity(Long com_id, Integer com_num, Long userId);

    /**
     * @description: 增加、修改商品的图片
     * （方法一：先删除服务器中的图片，再上传，然后更新数据库）
     * （方法二：直接上传图片，然后更新数据库--旧图片一直存在服务器中）
     * @param: commodity:  商品实体类
     * @return: api.CommonResult<commodity.entity.Commodity>
     * @author 黄俭豪
     * @date: 2020/10/13 21:51
     */
    @CacheEvict(value = {"commodity"}, allEntries = true)
    CommonResult<Commodity> updateMainImg(Long com_id, String mainImg, Long userId);

    /**
     * @param userId: 用户编号
     * @description: 删除一个商品
     * @param: com_id: 商品编号
     * @return: api.CommonResult<commodity.entity.Commodity>
     * @author 黄俭豪
     * @date: 2020/10/13 21:52
     */
    @CacheEvict(value = {"commodity"}, allEntries = true)
    CommonResult<Commodity> deleteCommodity(Long com_id, Long userId);

}
