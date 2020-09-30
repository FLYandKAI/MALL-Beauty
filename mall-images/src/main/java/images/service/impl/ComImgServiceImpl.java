package images.service.impl;

import images.entity.ComImg;
import images.mapper.ComImgMapper;
import images.service.ComImgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品图片信息表 服务实现类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-09-30
 */
@Service
public class ComImgServiceImpl extends ServiceImpl<ComImgMapper, ComImg> implements ComImgService {

}
