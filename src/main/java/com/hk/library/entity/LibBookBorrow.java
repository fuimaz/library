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
 * 书的借出归还记录
 * </p>
 *
 * @author 
 * @since 2020-08-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("lib_book_borrow")
public class LibBookBorrow implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 书名称
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 书id
     */
    private Integer bookId;

    /**
     * 用户id
     */
    private Integer userId;

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
