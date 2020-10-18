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
 * 商品图片VO
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ComImgVO对象", description="商品图片VO信息表")
public class ComImgVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品图片编号")
    @TableId(value = "com_img_id", type = IdType.ID_WORKER)
    private Long comImgId;

    @ApiModelProperty(value = "商品编号(每一个商品编号对应多个图片编号)")
    private Long comId;

    @ApiModelProperty(value = "商品图片地址(由服务器+存储组名+存储地址拼接)")
    private String address;

    @ApiModelProperty(value = "商品图片上传名字")
    private String comImgName;

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
