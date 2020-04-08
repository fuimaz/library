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
@TableName("processall")
public class Processall implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.AUTO)
    private String tid;

    /**
     * 开始人
     */
    @TableField("startMan")
    private String startMan;

    /**
     * 对应那条流程模范
     */
    @TableField("processGroup")
    private String processGroup;

    @TableField("startTime")
    private LocalDateTime startTime;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 流程当前状态
     */
    private String state;

    /**
     * 流程部门
     */
    private String dep;

    /**
     * 流程标题
     */
    private String title;

    /**
     * 流程结束时间
     */
    @TableField("endTime")
    private LocalDateTime endTime;

    /**
     * 最后的审核人
     */
    @TableField("lastOperator")
    private String lastOperator;

    /**
     * 最后的审核级别
     */
    @TableField("lastApproval")
    private String lastApproval;

    /**
     * 修改前的TID 防止修改后找不到对应的TID
     */
    @TableField("forOldTid")
    private String forOldTid;

    @TableField("projectTid")
    private String projectTid;


}
