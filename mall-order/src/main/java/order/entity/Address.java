package order.entity;

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
 * 收货地址信息表
 * </p>
 *
 * @author 黄俭豪
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Address对象", description="收货地址信息表")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "地址编号")
    @TableId(value = "addr_id", type = IdType.ID_WORKER)
    private Long addrId;

    @ApiModelProperty(value = "用户编号")
    private Long userId;

    @ApiModelProperty(value = "收货人姓名")
    private String addrName;

    @ApiModelProperty(value = "收货人电话")
    private String addrPhone;

    @ApiModelProperty(value = "收货人所在地区")
    private String addrPosition;

    @ApiModelProperty(value = "收货人详细地址")
    private String addrDetail;

    @ApiModelProperty(value = "是否为默认地址（0为否，1为是，默认为0）")
    private Integer addrDefault;

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
