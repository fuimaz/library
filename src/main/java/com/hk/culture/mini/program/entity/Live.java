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
@TableName("live")
public class Live implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 直播表
     */
    @TableId(value = "TID", type = IdType.UUID)
    private String tid;

    /**
     * 直播名称
     */
    private String name;

    /**
     * 开始时间
     */
    @TableField("startTime")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @TableField("endTime")
    private LocalDateTime endTime;

    private String responsible;

    @TableField("responsiblePhone")
    private String responsiblePhone;

    @TableField("ID")
    private String id;

    /**
     * 直播类型
     */
    private String type;


}
