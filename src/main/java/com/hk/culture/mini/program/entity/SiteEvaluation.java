package com.hk.culture.mini.program.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 标注点评价表
 * </p>
 *
 * @author 
 * @since 2020-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("site_evaluation")
public class SiteEvaluation implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.UUID)
    private String tid;

    /**
     * 描述
     */
    @TableField("siteTid")
    private String siteTid;

    /**
     * 会员id
     */
    @TableField("memberTid")
    private String memberTid;

    /**
     * 审核状态
     */
    private String auditing;

    /**
     * 审核人
     */
    @TableField("authMan")
    private String authMan;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否展示
     */
    @TableField("`show`")
    private String show;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private LocalDateTime updateTime;


}
