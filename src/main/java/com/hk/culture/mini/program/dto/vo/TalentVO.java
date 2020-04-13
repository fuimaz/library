package com.hk.culture.mini.program.dto.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-04-08
 */
@Data
public class TalentVO implements Serializable {

    private static final long serialVersionUID=1L;

    private String tid;

    private String name;

    private String degree;

    private String type;

    private String sex;

    private String remarks;


}
