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
@TableName("corporation")
public class Corporation implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.AUTO)
    private String tid;

    private String name;

    @TableField("parentTid")
    private String parentTid;

    @TableField("parentName")
    private String parentName;

    private String degree;

    private String image;

    private String video;


}
