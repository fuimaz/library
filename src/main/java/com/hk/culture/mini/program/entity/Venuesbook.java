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
@TableName("venuesbook")
public class Venuesbook implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.AUTO)
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


}
