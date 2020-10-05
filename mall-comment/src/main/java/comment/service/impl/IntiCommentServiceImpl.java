package comment.service.impl;

import comment.entity.IntiComment;
import comment.mapper.IntiCommentMapper;
import comment.service.IntiCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 帖子评论表 服务实现类
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-09-29
 */
@Service
public class IntiCommentServiceImpl extends ServiceImpl<IntiCommentMapper, IntiComment> implements IntiCommentService {

}
