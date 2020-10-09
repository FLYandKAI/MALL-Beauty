package images;

import images.entity.ComImg;
import images.entity.IntiImg;
import images.mapper.ComImgMapper;
import images.service.ComImgService;
import images.service.IntiImgService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ImgApplicationTests {

    @Autowired
    private ComImgService comImgService;

    @Autowired
    private IntiImgService intiImgService;

    @Autowired
    private ComImgMapper comImgMapper;

    @Test
    public void testInsert(){
        ComImg comImg = new ComImg();
        // comImg.setComImgId(5545L);
        comImg.setComId(4654611L);
        comImg.setByWho(5545L);
        System.out.println(comImgService.addImg(comImg));
        // System.out.println(comImgMapper.insert(comImg));
    }

    @Test
    public void testFind(){
        List<ComImg> all = comImgService.findAll();
        all.forEach(one-> System.out.println("one = " + one));

        System.out.println("===================================");

        comImgService.findAll().forEach(one-> System.out.println("one = " + one));
    }

    @Test
    public void testFind2(){
        System.out.println(comImgMapper.selectByComId(2L));
    }

    @Test

    public void testInsertInti(){
        IntiImg intiImg = new IntiImg();
        intiImg.setIntiId(4654611L);
        intiImg.setByWho(5545L);
        System.out.println(intiImgService.addImg(intiImg));
    }

    @Test
    public void testFindInti(){
        intiImgService.findAll().forEach(one-> System.out.println("one = " + one));

        System.out.println("===================================");

        intiImgService.findAll().forEach(one-> System.out.println("one = " + one));
    }

}
