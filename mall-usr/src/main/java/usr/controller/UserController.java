package usr.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import usr.entites.User;
import usr.service.UserService;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 郑树凯
 * @since 2020-09-20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get/{id}")
    public User searchById(@PathVariable("id") Integer id){
        User user = userService.getById(id);
        return user;
    }
}

