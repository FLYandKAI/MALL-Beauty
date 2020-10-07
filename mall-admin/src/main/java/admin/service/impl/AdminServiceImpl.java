package admin.service.impl;

import admin.entity.Admin;
import admin.mapper.AdminMapper;
import admin.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-09-29
 */
@Service
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    public Admin login(String adminUsername, String adminPassword){
        Admin admin = adminMapper.selectByname(adminUsername);
        if(admin==null&&admin.getAdminPassword()!=adminPassword) return null;
        else return admin;
    }
}
