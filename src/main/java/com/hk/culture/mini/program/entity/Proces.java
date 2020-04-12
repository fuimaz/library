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
@TableName("proces")
public class Proces implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.UUID)
    private String tid;

    private String title;

    private String remarks;

    /**
     * 流程适用开始职位
     */
    @TableField("startOperator")
    private String startOperator;

    private String image;

    /**
     * 流程开始的部门
     */
    private String dep;

    /**
     * 修改前的TID 防止修改后找不到对应的TID
     */
    @TableField("forOldTID")
    private String forOldTID;

    private String state;

    @TableField("lastModifyTime")
    private LocalDateTime lastModifyTime;


}
