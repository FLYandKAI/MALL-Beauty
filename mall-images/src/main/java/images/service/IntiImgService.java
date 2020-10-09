package images.service;

import images.entity.IntiImg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 帖子图片信息表 服务类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-02
 */
public interface IntiImgService extends IService<IntiImg> {

    List<IntiImg> findAll();
    Integer addImg(IntiImg intiImg);
}
