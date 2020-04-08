package com.hk.culture.mini.program.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("dateresource")
public class Dateresource implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 预约资源库
     */
    @TableId(value = "TID", type = IdType.AUTO)
    private String tid;

    /**
     * 预约时间
     */
    @TableField("dateTime")
    private LocalDateTime dateTime;

    /**
     * 资源名称
     */
    @TableField("resourceName")
    private String resourceName;

    /**
     * 资源TID
     */
    @TableField("resourceTid")
    private String resourceTid;

    /**
     * 资源详情
     */
    @TableField("resourceDetail")
    private String resourceDetail;

    private String image;

    private String video;


}
