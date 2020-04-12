package com.hk.culture.mini.program.dto.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class VenuesVO implements Serializable {

    private static final long serialVersionUID=1L;

    private String tid;

    private String name;

    private String number;

    private String address;

    private String detail;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer allCount;

    private String state;

    private BigDecimal hourCost;

    private String image;

    private String video;

    private String category;


}
