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
 * 商品分类信息表
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ComSort对象", description="商品分类信息表")
public class ComSort implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品类型编号")
    @TableId(value = "com_type_id", type = IdType.ID_WORKER)
    private Long comTypeId;

    @ApiModelProperty(value = "类别名称")
    private String comTypeName;

    @ApiModelProperty(value = "商品父类编号")
    private Long comTypeParentId;

    @ApiModelProperty(value = "类别状态（1为可用，0为不可用）")
    private Integer comTypeStatus;

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
    private Long lastVersion;

    @ApiModelProperty(value = "操作者（用户编号）")
    private Long byWho;

}
