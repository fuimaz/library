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
@TableName("processlife")
public class Processlife implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.UUID)
    private String tid;

    /**
     * 对应的流程模板
     */
    @TableField("processGroup")
    private String processGroup;

    /**
     * 上一层的级别
     */
    @TableField("fromPerson")
    private String fromPerson;

    /**
     * 当前级别
     */
    @TableField("toApproval")
    private String toApproval;

    /**
     * 审批结果
     */
    private String result;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 流程部门
     */
    private String dep;

    /**
     * 审批时间
     */
    @TableField("operateTime")
    private LocalDateTime operateTime;

    /**
     * 审批人
     */
    private String operator;

    /**
     * 发起人
     */
    @TableField("startMan")
    private String startMan;

    /**
     * 对应的流程总概述TID
     */
    @TableField("allProcessTID")
    private String allProcessTID;

    /**
     * 上一步的审核人
     */
    @TableField("lastOperator")
    private String lastOperator;

    /**
     * 上一步流程生命链的TID
     */
    @TableField("lastTID")
    private String lastTID;

    /**
     * 对应的步骤模板TID
     */
    @TableField("processlistTid")
    private String processlistTid;

    /**
     * 实际一条审批流程的执行顺序  递增
     */
    private Integer sequence;

    private String type;

    @TableField("toPerson")
    private String toPerson;

    @TableField("insertTime")
    private LocalDateTime insertTime;

    @TableField("lastOperateTime")
    private LocalDateTime lastOperateTime;


}
