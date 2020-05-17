package com.hk.culture.mini.program.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 路线标注点关联表
 * </p>
 *
 * @author 
 * @since 2020-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("route_relate")
public class RouteRelate implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.UUID)
    private String tid;

    /**
     * 路线名
     */
    @TableField("routeTid")
    private String routeTid;

    /**
     * 描述
     */
    @TableField("siteTid")
    private String siteTid;


    /**
     * 顺序
     */
    @TableField("order")
    private int order;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private LocalDateTime updateTime;


}
