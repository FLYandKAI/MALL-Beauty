package images.service.impl;

import images.entity.ComImg;
import images.mapper.ComImgMapper;
import images.service.ComImgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.FastDFSUtil;

import java.util.List;

/**
 * <p>
 * 商品图片信息表 服务实现类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-02
 */
@Service
public class ComImgServiceImpl extends ServiceImpl<ComImgMapper, ComImg> implements ComImgService {
    @Autowired
    private ComImgMapper comImgMapper;


    @Override
    public Integer addImg(ComImg comImg) {
        System.out.println(comImg.toString());
        return comImgMapper.insert(comImg);
    }

    @Override
    public Integer deleteImg(Long ComImgId) {
        ComImg comImg = comImgMapper.selectById(ComImgId);
        //删除服务器中的图片
        FastDFSUtil.delete(comImg.getComImgGroup(),comImg.getComImgAddress());
        //删除数据库的数据（逻辑删除）
        return comImgMapper.deleteById(ComImgId);
    }

    @Override
    public List<ComImg> findAll() {
        return comImgMapper.selectList(null);
    }
}
