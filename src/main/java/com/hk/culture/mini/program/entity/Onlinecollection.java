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
@TableName("onlinecollection")
public class Onlinecollection implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.UUID)
    private String tid;

    private String name;

    private String image;

    private String video;

    private String depot;

    @TableField("onlineName")
    private String onlineName;

    @TableField("insertTime")
    private LocalDateTime insertTime;

    @TableField("insertPerson")
    private String insertPerson;

    private String type;

    @TableField("onlineshowTid")
    private String onlineshowTid;


}
