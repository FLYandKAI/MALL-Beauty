package shoppingCart.service.impl;

import shoppingCart.entity.ShoppingCart;
import shoppingCart.mapper.ShoppingCartMapper;
import shoppingCart.service.ShoppingCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车信息表 服务实现类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-09-30
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}
