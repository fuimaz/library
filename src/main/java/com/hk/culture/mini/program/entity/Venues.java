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
 * @since 2020-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("venues")
public class Venues implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.UUID)
    private String tid;

    /**
     * 场馆名
     */
    private String name;

    /**
     * 场馆人数
     */
    private String number;

    /**
     * 场馆地址
     */
    private String address;

    /**
     * 详情
     */
    private String detail;

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

    /**
     * 全部花费
     */
    @TableField("allCount")
    private Integer allCount;

    /**
     * 场馆状态
     */
    private String state;

    /**
     * 每小时花费
     */
    @TableField("hourCost")
    private BigDecimal hourCost;

    /**
     * 场馆图片
     */
    private String image;

    /**
     * 场馆视频
     */
    private String video;

    /**
     * 场类型
     */
    private String category;

    /**
     * 场馆负责人电话
     */
    private String tel;


}
