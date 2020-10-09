package images.service.impl;


import com.netflix.discovery.converters.Auto;
import images.entity.IntiImg;
import images.mapper.IntiImgMapper;
import images.service.IntiImgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 帖子图片信息表 服务实现类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-02
 */
@Service
public class IntiImgServiceImpl extends ServiceImpl<IntiImgMapper, IntiImg> implements IntiImgService {


    @Autowired
    private IntiImgMapper intiImgMapper;

    @Override
    public List<IntiImg> findAll() {
        return intiImgMapper.selectList(null);
    }

    @Override
    public Integer addImg(IntiImg intiImg) {
        return intiImgMapper.insert(intiImg);
    }
}
