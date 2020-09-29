package comment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 帖子信息表
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Intitation对象", description="帖子信息表")
public class Intitation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "帖子编号")
    @TableId(value = "inti_id", type = IdType.ID_WORKER)
    private Long intiId;

    @ApiModelProperty(value = "用户编号")
    private Long userId;

    @ApiModelProperty(value = "帖子标题")
    private String intiTitle;

    @ApiModelProperty(value = "帖子图片id")
    private Long intiImgId;

    @ApiModelProperty(value = "帖子内容")
    private String intiContent;

    @ApiModelProperty(value = "帖子评论内容")
    private String intiComment;

    @ApiModelProperty(value = "点赞数量")
    @TableField("inti_likeNum")
    private Integer intiLikenum;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "本记录是否有效")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "历史版本")
    @Version
    private Long lastVersion;

    @ApiModelProperty(value = "操作者")
    private Long byWho;


}
