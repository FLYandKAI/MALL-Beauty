package commodity.service;

import api.CommonResult;
import commodity.entity.ItemParam;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>
 * 商品详细信息表 服务类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-11
 */
public interface ItemParamService extends IService<ItemParam> {
    /*
    @Cacheable：取缓存

 @CachePut：修改缓存

 @CacheEvict：删除缓存，allEntries：true表示清除value中的全部缓存，默认为false。
     */

    /**
     * @description: 根据商品id查询商品的规格参数
     * @param: com_id:  商品参数 Long类型
     * @return: api.CommonResult<commodity.entity.ItemParam>
     * @author 黄俭豪
     * @date: 2020/10/12 11:28
     */
    //value是存到redis的键值对的键值，值就是我们需要的缓存
    @Cacheable(value = "itemParam")
    CommonResult<ItemParam> findItemParamById(Long com_id);

    /**
     * @description: 查询所有商品参数
     * @param:
     * @return: api.CommonResult<commodity.entity.ItemParam>
     * @author 黄俭豪
     * @date: 2020/10/13 9:12
     */
    @Cacheable(value = "itemParam")
    CommonResult<ItemParam> findItemParam();

    /**
     * @description: 通过分页查询所有商品参数
     * @param: currentPage: 当前页
     * @param currentSize:  页数据大小
     * @return: api.CommonResult<commodity.entity.ItemParam>
     * @author 黄俭豪
     * @date: 2020/10/13 9:12
     */
    @Cacheable(value = "itemParam")
    CommonResult<ItemParam> findItemParam(Integer currentPage, Integer currentSize);



    /**
     * @description: 重载方法 添加商品参数 传入一个商品参数对象
     * @param: itemParam:  商品参数对象
     * @return: api.CommonResult<commodity.entity.ItemParam>
     * @author 黄俭豪
     * @date: 2020/10/13 8:22
     */
    @CacheEvict(value = {"itemParam"}, allEntries = true)
    CommonResult<ItemParam> addItemParam(ItemParam itemParam);



    /**
     * @description: 重载 修改商品参数 传入一个商品参数对象
     * @param: itemParam:  商品参数对象
     * @return: api.CommonResult<commodity.entity.ItemParam>
     * @author 黄俭豪
     * @date: 2020/10/13 8:23
     */
    @CacheEvict(value = {"itemParam"}, allEntries = true)
    CommonResult<ItemParam> updateItemParam(ItemParam itemParam);

    /**
     * @param userId:操作者--用户id
     * @description: 删除对应的商品数据
     * @param: item_param_id:参数id
     * @return: api.CommonResult<commodity.entity.ItemParam>
     * @author 黄俭豪
     * @date: 2020/10/12 16:55
     */
    @CacheEvict(value = {"itemParam"}, allEntries = true)
    CommonResult<ItemParam> deleteItemParam(Long com_id, Long userId);
}
