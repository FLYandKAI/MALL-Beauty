package admin.controller;



import admin.anno.PassToken;
import admin.entity.Admin;
import admin.service.impl.AdminServiceImpl;
import api.CommonResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.JWTUtil;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 郑树凯
 * @since 2020-09-29
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;
        //后台登陆验证

    /**
     *
     * @param adminUsername
     * @param adminPassword
     * @return  token令牌
     */
    @PostMapping("/login")
    public CommonResult login(String adminUsername,String adminPassword){
        if (StringUtils.isEmpty(adminUsername) || StringUtils.isEmpty(adminPassword)) {
            System.out.println(CommonResult.failed("用户或密码为空"));
            return CommonResult.failed("用户或密码为空");
        }
        return adminService.login(adminUsername, adminPassword);
    }
    //admin增加 手拿token
    @PostMapping("/user")
    public CommonResult addAdmin(String adminUsername, String adminPassword, String token) {
        Integer usrid = null;
        try {
            usrid = (Integer) JWTUtil.CheckToken(token).get("usrid");
        } catch (Exception e) {
            return CommonResult.unauthorized("token有错误");
        }
        if(adminService.IsExistAdmin(adminUsername)){
            return CommonResult.failed("该用户存在");
        }
        Admin admin = new Admin();
        admin.setAdminUsername(adminUsername);
        admin.setByWho(Long.valueOf(usrid));
        admin.setAdminPassword(adminPassword);
        if(adminService.save(admin)){
            return CommonResult.success("添加成功");
        }
        return CommonResult.failed("更新失败");
    }
    @PutMapping("/user")
    //修改
    public boolean modifyAdmin(Admin admin,String token) {
        System.out.println(admin);
        boolean b = adminService.updateById(admin);
        return b;
    }
    @DeleteMapping("/user/{id}")
    public boolean deleteAdmin(@PathVariable("id") Integer adminID) {
        boolean removeById = adminService.removeById(adminID);
        System.out.println(removeById);
        return removeById;
    }

}

