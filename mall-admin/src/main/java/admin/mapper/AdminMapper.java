package admin.mapper;

import admin.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-09-29
 */
public interface AdminMapper extends BaseMapper<Admin> {
    Admin selectByname(@Param("name") String name);
 }
