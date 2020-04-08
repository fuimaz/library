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
@TableName("activity")
public class Activity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.AUTO)
    private String tid;

    /**
     * 活动名称
     */
    @TableField("activityName")
    private String activityName;

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


}
