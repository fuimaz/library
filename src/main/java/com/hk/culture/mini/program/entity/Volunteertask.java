package com.hk.culture.mini.program.entity;

import java.math.BigDecimal;
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
@TableName("volunteertask")
public class Volunteertask implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.UUID)
    private String tid;

    @TableField("taskName")
    private String taskName;

    private String type;

    @TableField("startTime")
    private LocalDateTime startTime;

    @TableField("endTime")
    private LocalDateTime endTime;

    @TableField("volunteerCount")
    private Integer volunteerCount;

    private String depot;

    private BigDecimal cost;

    private String responsible;

    @TableField("responsiblePhone")
    private String responsiblePhone;

    private String state;

    private String remarks;

    @TableField("activityTid")
    private String activityTid;


}
