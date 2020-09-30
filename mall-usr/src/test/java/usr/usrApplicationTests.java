package usr;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import usr.entites.User;
import usr.mapper.UserMapper;

import javax.annotation.Resource;

@SpringBootTest
public class usrApplicationTests {

    // @Autowired
    @Resource
    private UserMapper userMapper;

    @Test
    void contextLoads(){
        User user = new User();
        // User user = userMapper.selectById(4654654L);
        user.setUserId(4654654L);
        user.setUserName("ashdfjkashdjkfh");
        user.setPassword("dsfsdfsd");
        user.setByWho("sdfsdfds");
        // System.out.println(userMapper.insert(user));
        System.out.println(userMapper.updateById(user));
    }
}
