package usr.entites;

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
 * 用户表
 * </p>
 *
 * @author 郑树凯
<<<<<<< HEAD
 * @since 2020-09-20
=======
 * @since 2020-09-30
>>>>>>> hao
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编号")
    @TableId(value = "user_id", type = IdType.ID_WORKER)
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
<<<<<<< HEAD
=======
    @TableField(fill = FieldFill.INSERT_UPDATE)
>>>>>>> hao
    private Date updateTime;

    @ApiModelProperty(value = "本记录是否有效")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "历史版本")
    @Version
<<<<<<< HEAD
    private Long lastVersion;
=======
    private Integer lastVersion;

    @ApiModelProperty(value = "操作者")
    private String byWho;
>>>>>>> hao


}
