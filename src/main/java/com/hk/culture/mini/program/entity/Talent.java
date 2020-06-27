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
 * 
 * </p>
 *
 * @author 
 * @since 2020-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("talent")
public class Talent implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 文艺人才表
     */
    @TableId(value = "TID", type = IdType.UUID)
    private String tid;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 身份证号
     */
    @TableField("idNo")
    private String idNo;

    /**
     * 生日
     */
    private String birth;

    /**
     * 毕业院校
     */
    private String school;

    /**
     * 学历
     */
    private String degree;

    /**
     * 地址
     */
    private String address;

    /**
     * 工作单位
     */
    @TableField("workUnit")
    private String workUnit;

    /**
     * 手机号
     */
    @TableField("phoneNo")
    private String phoneNo;

    /**
     * 座机号
     */
    @TableField("mobileNo")
    private String mobileNo;

    /**
     * 邮箱
     */
    private String email;

    /**
     * qq号
     */
    private String qq;

    /**
     * 爱好特长
     */
    private String interest;

    /**
     * 工艺经历
     */
    private String experience;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 审核状态
     */
    private String auditing;

    /**
     * 顺序
     */
    @TableField("`order`")
    private Integer order;

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
