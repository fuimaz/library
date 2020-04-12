package com.hk.culture.mini.program.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("actteam")
public class Actteam implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.UUID)
    private String tid;

    /**
     * 文艺团名称
     */
    @TableField("teamName")
    private String teamName;

    /**
     * 文艺团负责人
     */
    private String responsible;

    /**
     * 文艺团编号
     */
    private String number;

    /**
     * 文艺团负责人电话
     */
    @TableField("responsiblePhone")
    private String responsiblePhone;


}
