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
 * @since 2020-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("activity")
public class Activity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.UUID)
    private String tid;

    /**
     * 活动名称
     */
    @TableField("activityName")
    private String activityName;

    /**
     * 活动介绍
     */
    @TableField("activityDesc")
    private String activityDesc;

    /**
     * 活动类别
     */
    @TableField("activityType")
    private String activityType;

    /**
     * 活动负责人
     */
    private String responsible;

    /**
     * 是否通过审核
     */
    @TableField("isVerify")
    private String isVerify;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 活动开始时间
     */
    @TableField("stratTime")
    private LocalDateTime stratTime;

    /**
     * 活动结束时间
     */
    @TableField("endTime")
    private LocalDateTime endTime;

    /**
     * 活动地点
     */
    private String depot;

    /**
     * 预约人数
     */
    private String orders;

    /**
     * 活动主办单位
     */
    private String company;

    /**
     * 活动协办单位
     */
    @TableField("asstCompany")
    private String asstCompany;

    /**
     * 联系人电话
     */
    @TableField("responsiblePhone")
    private String responsiblePhone;

    /**
     * 经费
     */
    private BigDecimal funds;

    /**
     * 单人费用
     */
    @TableField("oneCost")
    private BigDecimal oneCost;

    /**
     * 计划状态
     */
    private String state;

    /**
     * 活动图片
     */
    private String image;

    /**
     * 活动视频
     */
    private String video;

    /**
     * 审核人
     */
    @TableField("authMan")
    private String authMan;

    /**
     * 已预约人数
     */
    private String booked;


}
