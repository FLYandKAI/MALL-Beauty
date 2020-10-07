package admin.service;

import admin.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-09-29
 */
public interface AdminService extends IService<Admin> {
    public Admin login(String adminUsername, String adminPassword);
}
