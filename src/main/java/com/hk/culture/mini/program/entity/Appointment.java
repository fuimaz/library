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
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("appointment")
public class Appointment implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.UUID)
    private String tid;

    /**
     * 预约人名称
     */
    private String name;

    /**
     * 预约人电话
     */
    private String phone;

    /**
     * 预约时间
     */
    @TableField("dateTime")
    private LocalDateTime dateTime;

    /**
     * 预约地点
     */
    private String depot;

    /**
     * 预约类型  包括场地、场馆、活动
     */
    private String type;

    /**
     * 审核状态
     */
    private String process;

    /**
     * 预约人的TID
     */
    @TableField("memberTid")
    private String memberTid;

    /**
     * 预约记录的TID
     */
    @TableField("bookTid")
    private String bookTid;

    /**
     * 预约方式  PC或手机
     */
    private String way;


}
