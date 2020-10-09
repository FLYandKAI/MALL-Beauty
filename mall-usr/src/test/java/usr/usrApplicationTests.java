package usr;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import usr.entites.User;
import usr.mapper.UserMapper;

import javax.annotation.Resource;
import java.security.PublicKey;
import java.util.List;

@SpringBootTest
public class usrApplicationTests {

    // @Autowired
    @Resource
    private UserMapper userMapper;

    @Test
    public void update(){
        User user = new User();
        // User user = userMapper.selectById(4654654L);
        user.setUserId(4654654L);
        user.setUserName("ashdfjkashdjkfh");
        user.setPassword("dsfsdfsd");
        user.setByWho("sdfsdfds");
        // System.out.println(userMapper.insert(user));
        System.out.println(userMapper.updateById(user));
    }

    @Test
    public void deleteUser(){
        System.out.println(userMapper.deleteById(123L));
    }

    @Test
    public void selectUser(){
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

}
