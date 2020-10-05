package images;

import images.entity.ComImg;
import images.mapper.ComImgMapper;
import images.service.ComImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 黄俭豪
 * @date 2020/10/2 19:28
 */
@SpringBootTest
public class Test111 {

    @Autowired
    private ComImgService comImgService;

    @Autowired
    private ComImgMapper comImgMapper;

    @Test
    public void testInsert(){
        ComImg comImg = new ComImg();
        comImg.setComImgId(555L);
        comImg.setComId(555L);
        comImg.setByWho(555L);
        // System.out.println(comImgService.addImg(comImg));
        System.out.println(comImgMapper.insert(comImg));
    }
}
