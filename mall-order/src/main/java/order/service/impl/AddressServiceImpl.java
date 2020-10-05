package order.service.impl;

import order.entity.Address;
import order.mapper.AddressMapper;
import order.service.AddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收货地址信息表 服务实现类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-09-29
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

}
