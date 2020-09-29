package commodity.entity;

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
 * 帖子图片信息表
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="IntiImg对象", description="帖子图片信息表")
public class IntiImg implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "帖子图片编号")
    @TableId(value = "inti_img_id", type = IdType.ID_WORKER)
    private Long intiImgId;

    @ApiModelProperty(value = "帖子组名（服务器保存)")
    private String intiImgGroup;

    @ApiModelProperty(value = "帖子图片地址")
    private String intiImgAddress;

    @ApiModelProperty(value = "帖子图片上传名字")
    private String intiImgName;

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
