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
@TableName("collection_life")
public class CollectionLife implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "Tid", type = IdType.AUTO)
    private String Tid;

    /**
     * epc编号
     */
    @TableField("ecpCode")
    private String ecpCode;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     */
    @TableField("lastMidifyTime")
    private LocalDateTime lastMidifyTime;

    /**
     * 操作类型
     */
    @TableField("operationType")
    private String operationType;

    /**
     * 操作人
     */
    @TableField("operationPerson")
    private String operationPerson;

    /**
     * 操作详情
     */
    @TableField("operationDetail")
    private String operationDetail;

    /**
     * 操作后的状态
     */
    private String state;

    /**
     * 操作后的保存状态
     */
    @TableField("useState")
    private String useState;

    @TableField("toPlace")
    private String toPlace;

    /**
     * 储存地点
     */
    @TableField("storeRoom")
    private String storeRoom;


}
