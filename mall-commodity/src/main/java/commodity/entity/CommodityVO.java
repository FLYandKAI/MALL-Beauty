package commodity.entity;

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
 * 商品信息表
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Commodity对象", description="商品信息表")
public class CommodityVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品编号")
    @TableId(value = "com_id", type = IdType.ID_WORKER)
    private Long comId;

    @ApiModelProperty(value = "商品类型编号")
    private Long comSortId;

    @ApiModelProperty(value = "类别名称")
    private String comTypeName;

    @ApiModelProperty(value = "商品名称")
    private String comName;

    // @ApiModelProperty(value = "商品详情编号")
    // private Long comItemParamId;

    @ApiModelProperty(value = "商品价格")
    private Integer comPrice;

    @ApiModelProperty(value = "商品库存")
    private Integer comStock;

    @ApiModelProperty(value = "商品状态（0为下架，1为销售）")
    private Integer comStatus;

    @ApiModelProperty(value = "主要的图片（首页显示）")
    @TableField("mainImage")
    private String mainImage;

    @ApiModelProperty(value = "商品购买参数")
    private String itemParamData;

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

    @ApiModelProperty(value = "操作者（用户编号）")
    private Long byWho;


}
