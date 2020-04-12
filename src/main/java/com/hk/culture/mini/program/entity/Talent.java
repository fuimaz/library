package com.hk.culture.mini.program.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("talent")
public class Talent implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 文艺人才表
     */
    @TableId(value = "TID", type = IdType.UUID)
    private String tid;

    private String name;

    private String degree;

    private String type;

    private String sex;

    private String remarks;


}
