package com.hk.library.dto.vo;

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
 * @since 2020-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class VenuesVO implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 全部花费
     */
    private Integer allCount;

    /**
     * 场馆状态
     */
    private String state;

    /**
     * 每小时花费
     */
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
