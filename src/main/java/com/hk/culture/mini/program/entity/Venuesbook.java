package com.hk.culture.mini.program.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("venuesbook")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Venuesbook implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.UUID)
    private String tid;

    /**
     * 场馆id
     */
    @TableField("venuesTid")
    private String venuesTid;

    /**
     * 操作时间
     */
    private LocalDateTime booktime;

    /**
     * 预约开始时间
     */
    @TableField("startTime")
    private LocalDateTime startTime;

    /**
     * 预约结束时间
     */
    @TableField("endTime")
    private LocalDateTime endTime;

    /**
     * 预约活动id
     */
    @TableField("activityTid")
    private String activityTid;

    /**
     * 预约活动id
     */
    @TableField("activityName")
    private String activityName;

    /**
     * 场馆名
     */
    @TableField("venuesName")
    private String venuesName;

    /**
     * 预约人
     */
    private String responsible;

    /**
     * 预约人电话
     */
    @TableField("responsiblePhone")
    private String responsiblePhone;

    /**
     * 每小时花费
     */
    @TableField("hourCost")
    private BigDecimal hourCost;

    /**
     * 状态
     */
    private String state;


}
