package com.hk.library.dto.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 路线图表
 * </p>
 *
 * @author
 * @since 2020-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RouteVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tid;

    /**
     * 路线名
     */
    private String name;

    /**
     * 描述
     */
    private String detail;

    /**
     * 简介
     */
    private String brief;

    /**
     * 审核状态
     */
    private String auditing;

    /**
     * 审核人
     */
    private String authMan;

    /**
     * 背景图
     */
    private String img;

    /**
     * 是否展示
     */
    private String show;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 关联坐标点
     */
    private List<RouteSiteVO> siteList;

}
