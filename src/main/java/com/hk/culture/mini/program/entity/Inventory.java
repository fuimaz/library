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
@TableName("inventory")
public class Inventory implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "TID", type = IdType.AUTO)
    private Integer tid;

    @TableField("createTime")
    private LocalDateTime createTime;

    @TableField("createBy")
    private String createBy;

    @TableField("lastModifyBy")
    private String lastModifyBy;

    @TableField("lastModifyTime")
    private LocalDateTime lastModifyTime;

    @TableField("oprateTime")
    private LocalDateTime oprateTime;

    @TableField("oprateServiceTime")
    private LocalDateTime oprateServiceTime;

    @TableField("productState")
    private String productState;

    @TableField("oprateType")
    private String oprateType;

    private String depotId;

    private String remark;

    @TableField("getDate")
    private LocalDateTime getDate;

    @TableField("ecpCode")
    private String ecpCode;

    private String state;

    private String sequence;

    @TableField("checkPrice")
    private Double checkPrice;

    private String origin;

    @TableField("ownDep")
    private String ownDep;

    private String responsible;

    @TableField("typeForSearch")
    private Integer typeForSearch;

    @TableField("depotTid")
    private String depotTid;

    private String name;

    private String model;

    private Integer count;

    private String remarks;

    @TableField("oldCount")
    private Integer oldCount;


}
