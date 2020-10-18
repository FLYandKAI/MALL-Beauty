package images.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 帖子图片信息表
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="IntiImg对象", description="帖子图片信息表")
public class IntiImgVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "帖子图片编号")
    @TableId(value = "inti_img_id", type = IdType.ID_WORKER)
    private Long intiImgId;

    @ApiModelProperty(value = "帖子图片编号(一个帖子对应多个图片编号)")
    private Long intiId;

    @ApiModelProperty(value = "帖子图片地址(由服务器+存储组名+存储地址拼接)")
    private String address;

    @ApiModelProperty(value = "帖子图片上传名字")
    private String intiImgName;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "本记录是否有效")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "历史版本")
    @Version
    private Integer lastVersion;

    @ApiModelProperty(value = "操作者")
    private Long byWho;


}
