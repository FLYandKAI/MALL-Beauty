package images.mapper;


import images.entity.ComImg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商品图片信息表 Mapper 接口
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-02
 */

@Repository
public interface ComImgMapper extends BaseMapper<ComImg> {
    ComImg selectByComId(Long id);
}
