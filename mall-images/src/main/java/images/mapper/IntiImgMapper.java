package images.mapper;

import images.entity.IntiImg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 帖子图片信息表 Mapper 接口
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-02
 */
@Repository
public interface IntiImgMapper extends BaseMapper<IntiImg> {

}
