package com.hk.culture.mini.program.entity;

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
@TableName("onlineshow")
public class Onlineshow implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.AUTO)
    private String tid;

    @TableField("startMan")
    private String startMan;

    @TableField("startTime")
    private LocalDateTime startTime;

    @TableField("insertTime")
    private LocalDateTime insertTime;

    @TableField("showName")
    private String showName;

    private String title;

    private String state;

    private String process;

    @TableField("endTime")
    private LocalDateTime endTime;

    @TableField("readCount")
    private String readCount;


}
