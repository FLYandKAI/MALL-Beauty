package images.entity;

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
 * 商品图片信息表
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ComImg对象", description="商品图片信息表")
public class ComImg implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品图片编号")
    @TableId(value = "com_img_id", type = IdType.ID_WORKER)
    private Long comImgId;

    @ApiModelProperty(value = "商品编号(每一个商品编号对应多个图片编号)")
    private Long comId;

    @ApiModelProperty(value = "商品组名（服务器保存)")
    private String comImgGroup;

    @ApiModelProperty(value = "商品图片地址")
    private String comImgAddress;

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
