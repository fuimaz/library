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
@TableName("collection")
public class Collection implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "Tid", type = IdType.UUID)
    private String Tid;

    @TableField("createTime")
    private LocalDateTime createTime;

    @TableField("lastModifyTime")
    private LocalDateTime lastModifyTime;

    private String name;

    /**
     * 编码
     */
    private String number;

    /**
     * 编码类型
     */
    @TableField("numberType")
    private String numberType;

    /**
     * 原名
     */
    @TableField("oldName")
    private String oldName;

    /**
     * 年代
     */
    private String years;

    /**
     * 具体年代
     */
    @TableField("concreteYears")
    private String concreteYears;

    /**
     * 文物类别
     */
    private String category;

    /**
     * 质地
     */
    private String quality;

    /**
     * 质地一
     */
    @TableField("qualityOne")
    private String qualityOne;

    /**
     * 质地二
     */
    @TableField("qualityTwo")
    private String qualityTwo;

    private String count;

    /**
     * 长
     */
    private String length;

    /**
     * 高
     */
    private String height;

    /**
     * 宽
     */
    private String width;

    /**
     * 具体尺寸
     */
    @TableField("concreteSize")
    private String concreteSize;

    /**
     * 质量范围
     */
    @TableField("massRang")
    private String massRang;

    /**
     * 具体质量
     */
    private String mass;

    /**
     * 质量单位
     */
    @TableField("massType")
    private String massType;

    /**
     * 文物等级
     */
    private String degree;

    /**
     * 来源
     */
    private String origin;

    /**
     * 完缺程度
     */
    private String complete;

    /**
     * 完缺状况
     */
    @TableField("completeDetail")
    private String completeDetail;

    /**
     * 保存状态
     */
    @TableField("conditionString")
    private String conditionString;

    /**
     * 入藏时间范围
     */
    @TableField("inYear")
    private String inYear;

    /**
     * 具体入藏时间
     */
    @TableField("concreteInYear")
    private String concreteInYear;

    /**
     * 作者
     */
    private String author;

    /**
     * 版本
     */
    private String version;

    /**
     * 存卷
     */
    private String cunjuan;

    /**
     * 库存状态
     */
    private String state;

    /**
     * 本身状态
     */
    @TableField("useState")
    private String useState;

    /**
     * 中间数字
     */
    @TableField("middleCount")
    private Integer middleCount;

    /**
     * 进出状态
     */
    private String inorout;

    /**
     * 负责人
     */
    private String responsible;

    /**
     * 储藏室
     */
    @TableField("storeRoom")
    private String storeRoom;

    private String init;

    private String allow;

    private String image;

    @TableField("imageTid")
    private String imageTid;

    /**
     * epc编码
     */
    @TableField("ecpCode")
    private String ecpCode;

    /**
     * epcTid
     */
    @TableField("ecpTid")
    private String ecpTid;

    /**
     * epc二维码
     */
    @TableField("ecpQRCode")
    private String ecpQRCode;

    @TableField("imageCode")
    private String imageCode;

    /**
     * 折旧率
     */
    private String depreciation;

    /**
     * 报废条件
     */
    private String scrapcondition;


}
