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
@TableName("votestore")
public class Votestore implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.AUTO)
    private String tid;

    private String name;

    private String sequence;

    private String question;

    private String answer;

    @TableField("voteTitle")
    private String voteTitle;

    @TableField("startTime")
    private LocalDateTime startTime;

    @TableField("startMan")
    private String startMan;

    /**
     * 答案类型 多选、单选、自由发挥
     */
    private String type;

    private String state;


}
