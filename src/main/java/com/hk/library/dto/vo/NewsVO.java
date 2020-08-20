package com.hk.library.dto.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 新闻资讯表
 * </p>
 *
 * @author
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class NewsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tid;

    /**
     * 文章标题
     */
    private String title;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 简介
     */
    private String brief;

    /**
     * 类型
     */
    private String type;

    /**
     * 背景图
     */
    private String backgroundImg;


}
