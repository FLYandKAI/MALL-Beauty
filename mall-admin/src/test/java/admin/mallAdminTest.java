package admin;

import admin.mapper.AdminMapper;
import admin.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author: YRB
 * @Description:
 * @Date: Create in 21:22 2020/10/6
 */
@SpringBootTest
public class mallAdminTest {
    @Resource
    private AdminService adminService;
    @Test
    void test(){
//        adminMapper.selectByname("aaa");
        System.out.println(adminService.login("aaa", "bbb"));
//        adminService.getById("1");
    }
}
