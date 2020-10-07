package admin.controller;


import admin.entity.Admin;
import admin.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-09-29
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;
        //后台登陆验证
    @PostMapping("/login")
    public Admin checkAdmin(@PathVariable String adminUsername, String adminPassword){

        return null;
    }

}

