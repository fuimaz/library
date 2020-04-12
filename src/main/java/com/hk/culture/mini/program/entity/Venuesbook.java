package com.hk.culture.mini.program.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("venuesbook")
public class Venuesbook implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.UUID)
    private String tid;

    @TableField("venuesTid")
    private String venuesTid;

    private LocalDateTime booktime;

    @TableField("activityTid")
    private String activityTid;

    @TableField("activityName")
    private String activityName;

    @TableField("venuesName")
    private String venuesName;

    private String responsible;

    @TableField("responsiblePhone")
    private String responsiblePhone;

    @TableField("hourCost")
    private BigDecimal hourCost;

    private String state;


}
