package admin.service;

import admin.entity.Admin;
import api.CommonResult;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 郑树凯
 * @since 2020-09-29
 */
public interface AdminService extends IService<Admin> {
    public CommonResult login(String adminUsername, String adminPassword);
    public boolean IsExistAdmin(String adminUsername);


}
