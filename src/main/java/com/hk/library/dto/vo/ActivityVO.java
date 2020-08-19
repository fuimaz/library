package com.hk.library.dto.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-04-08
 */
@Data
public class ActivityVO implements Serializable {

    private static final long serialVersionUID=1L;

    private String tid;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动介绍
     */
    private String activityDesc;

    /**
     * 活动类别
     */
    private String activityType;

    /**
     * 活动负责人
     */
    private String responsible;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 活动开始时间
     */
    private LocalDateTime stratTime;

    /**
     * 活动结束时间
     */
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
    private String asstCompany;

    /**
     * 联系人电话
     */
    private String responsiblePhone;

    /**
     * 经费
     */
    private BigDecimal funds;

    /**
     * 单人费用
     */
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
     * 已预约人数
     */
    private String booked;

}
