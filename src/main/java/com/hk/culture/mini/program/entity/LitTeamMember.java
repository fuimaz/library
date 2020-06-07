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
 * 文艺团队成员表
 * </p>
 *
 * @author 
 * @since 2020-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("lit_team_member")
public class LitTeamMember implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "TID", type = IdType.UUID)
    private String tid;

    /**
     * 团队id
     */
    @TableField("teamTID")
    private String teamTID;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 专业
     */
    private String major;

    /**
     * 身份证号
     */
    @TableField("idNo")
    private String idNo;

    /**
     * 学历或职称
     */
    private String degree;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 审核状态
     */
    private String auditing;

    /**
     * 人才照片
     */
    private String img;

    /**
     * 身份证照片
     */
    @TableField("idImg")
    private String idImg;

    /**
     * 能力证明照片
     */
    @TableField("abilityImg")
    private String abilityImg;

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

    /**
     * 状态
     */
    private Integer state;


}
