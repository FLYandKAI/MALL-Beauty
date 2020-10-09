package usr.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import usr.entites.User;
import usr.service.UserService;

import java.util.List;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 郑树凯

 * @since 2020-09-30
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    /** 
     * @description: 通过用户ID查询一个用户 
     * @param: id:  
     * @return: usr.entites.User 
     * @author 黄俭豪
     * @date: 2020/9/30 11:02
     */
    @GetMapping("/get/{id}")
    public User searchById(@PathVariable("id") Integer id){
        User user = userService.getById(id);
        return user;
    }

    /** 
     * @description: 查询所有用户 
     * @param:  
     * @return: java.util.List<usr.entites.User> 
     * @author 黄俭豪
     * @date: 2020/9/30 14:34
     */ 
    @GetMapping("/getAll")
    public List<User> searchAll(){
        // Map map = userService.getMap(null);
        List<User> list = userService.list(null);
        return list;
    }

}

