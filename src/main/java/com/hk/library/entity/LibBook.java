package com.hk.library.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 书籍
 * </p>
 *
 * @author 
 * @since 2020-08-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("lib_book")
public class LibBook implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 书名称
     */
    private String bookName;

    /**
     * 书简介
     */
    private String bookIntro;

    /**
     * 单价
     */
    private Long bookPrice;

    /**
     * 种类
     */
    private Integer typeId;

    /**
     * 出版社
     */
    private Integer pubId;

    /**
     * 缩略图url
     */
    private String imageUrl;

    /**
     * 作者
     */
    private String author;

    /**
     * 库存数量
     */
    private Integer repertorySize;

    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
