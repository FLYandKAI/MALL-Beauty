package shoppingCart.service.impl;

import shoppingCart.entity.Commodity;
import shoppingCart.mapper.CommodityMapper;
import shoppingCart.service.CommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-09-30
 */
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService {

}
