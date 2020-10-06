package images.mapper;

import images.cache.MybatisRedisCache;
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
// @CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface IntiImgMapper extends BaseMapper<IntiImg> {

}
