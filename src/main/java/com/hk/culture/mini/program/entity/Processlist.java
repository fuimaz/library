package com.hk.culture.mini.program.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("processlist")
public class Processlist implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.UUID)
    private String tid;

    /**
     * 使用这套流程的部门
     */
    private String dep;

    /**
     * 一组流程的TID
     */
    @TableField("groupTid")
    private String groupTid;

    /**
     * 是否是开头或结尾  first,end,normal分别表示起始，结束，中间普通
     */
    @TableField("processIndex")
    private String processIndex;

    /**
     * 上一步的TID
     */
    @TableField("lastTid")
    private String lastTid;

    /**
     * 下一步的TID
     */
    @TableField("nextTid")
    private String nextTid;

    /**
     * 审批人级别
     */
    private String approval;

    @TableField("forOldTID")
    private String forOldTID;

    /**
     * 流程顺序
     */
    private Integer sequence;

    /**
     * X轴位置
     */
    @TableField("positionX")
    private Integer positionX;

    /**
     * Y轴位置
     */
    @TableField("positionY")
    private Integer positionY;

    private String name;


}
