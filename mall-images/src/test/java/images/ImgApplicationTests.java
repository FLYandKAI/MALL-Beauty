package images;

import images.entity.ComImg;
import images.mapper.ComImgMapper;
import images.service.ComImgService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ImgApplicationTests {

    @Autowired
    private ComImgService comImgService;

    @Autowired
    private ComImgMapper comImgMapper;

    @Test
    public void testInsert(){
        ComImg comImg = new ComImg();
        // comImg.setComImgId(5545L);
        comImg.setComId(5545L);
        comImg.setByWho(5545L);
        System.out.println(comImgService.addImg(comImg));
        // System.out.println(comImgMapper.insert(comImg));
    }

}
