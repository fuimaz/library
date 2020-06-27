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
 * 文艺团队服务表
 * </p>
 *
 * @author 
 * @since 2020-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("literary_team")
public class LiteraryTeam implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "TID", type = IdType.UUID)
    private String tid;

    /**
     * 团队名
     */
    private String name;

    /**
     * 申请人
     */
    private String applicant;

    /**
     * 经济类型
     */
    @TableField("companyType")
    private String companyType;

    /**
     * 注册资本
     */
    private String capital;

    /**
     * 登记注册机关
     */
    private String authority;

    /**
     * 经营范围
     */
    @TableField("bizScope")
    private String bizScope;

    /**
     * 注册号
     */
    @TableField("regNo")
    private String regNo;

    /**
     * 从业人员
     */
    private String member;

    /**
     * 代表人
     */
    private String representative;

    /**
     * 代表人手机号
     */
    @TableField("repMobileNo")
    private String repMobileNo;

    /**
     * 代表人固定电话
     */
    @TableField("repPhone")
    private String repPhone;

    /**
     * 代表人身份证号
     */
    @TableField("repId")
    private String repId;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 负责人手机号
     */
    @TableField("leaderMobileNo")
    private String leaderMobileNo;

    /**
     * 负责人固定电话
     */
    @TableField("leaderPhone")
    private String leaderPhone;

    /**
     * 负责人身份证号
     */
    @TableField("leaderId")
    private String leaderId;

    /**
     * 住所邮编
     */
    @TableField("zipCode")
    private String zipCode;

    /**
     * 住所
     */
    private String residence;

    /**
     * 住所传真电话
     */
    @TableField("faxPhone")
    private String faxPhone;

    /**
     * 经营地址
     */
    @TableField("bizAddr")
    private String bizAddr;

    /**
     * 经营地址邮编
     */
    @TableField("bizZipCode")
    private String bizZipCode;

    /**
     * 经营地址传真
     */
    @TableField("bizFaxPhone")
    private String bizFaxPhone;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 审核状态
     */
    private String auditing;

    /**
     * 照片
     */
    @TableField("regImg")
    private String regImg;

    /**
     * 照片
     */
    @TableField("bizLicImg")
    private String bizLicImg;

    /**
     * 照片
     */
    @TableField("constitutionImg")
    private String constitutionImg;

    /**
     * 照片
     */
    @TableField("repIdImg")
    private String repIdImg;

    /**
     * 照片
     */
    @TableField("equipmentImg")
    private String equipmentImg;

    /**
     * 照片
     */
    @TableField("addressImg")
    private String addressImg;

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
