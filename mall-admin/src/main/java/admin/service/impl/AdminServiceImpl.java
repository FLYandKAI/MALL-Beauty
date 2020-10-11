package admin.service.impl;

import admin.entity.Admin;
import admin.mapper.AdminMapper;
import admin.service.AdminService;
import api.CommonResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.JWTUtil;

/**
 * <p>
 *  服务实现类
 * </p>
 * @author 郑树凯
 * @since 2020-09-29
 */
@Service
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    //登录
    public CommonResult login(String adminUsername, String adminPassword){
        Admin admin =adminMapper.selectByname(adminUsername);
        System.out.println(!admin.getAdminPassword().equals(adminPassword));
            if(admin==null) return CommonResult.validateFailed();
            if (!admin.getAdminPassword().equals(adminPassword))  return CommonResult.validateFailed();
        return CommonResult.success(JWTUtil.createToken(admin.getAdminId()));
    }

    //检测该用户是否存在
    public boolean IsExistAdmin(String adminUsername) {
        if (adminMapper.selectByname(adminUsername) != null) {
            return true;
        }
        return false;
    }

}
