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
@TableName("venues")
public class Venues implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.AUTO)
    private String tid;

    private String name;

    private String number;

    private String address;

    private String detail;

    @TableField("startTime")
    private LocalDateTime startTime;

    @TableField("endTime")
    private LocalDateTime endTime;

    @TableField("allCount")
    private Integer allCount;

    private String state;

    @TableField("hourCost")
    private BigDecimal hourCost;

    private String image;

    private String video;

    private String category;


}
