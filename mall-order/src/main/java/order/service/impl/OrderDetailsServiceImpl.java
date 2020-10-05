package order.service.impl;

import order.entity.OrderDetails;
import order.mapper.OrderDetailsMapper;
import order.service.OrderDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单详细信息表 服务实现类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-09-29
 */
@Service
public class OrderDetailsServiceImpl extends ServiceImpl<OrderDetailsMapper, OrderDetails> implements OrderDetailsService {

}
