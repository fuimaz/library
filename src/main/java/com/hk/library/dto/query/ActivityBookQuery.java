package com.hk.library.dto.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
public class ActivityBookQuery implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 预约活动id
     */
    @NonNull
    private String tid;

    /**
     * 预约人手机号
     */
    @NonNull
    private String mobile;

    /**
     * 预约人id
     */
    @NonNull
    private String memberTid;

    /**
     * 预约人姓名
     */
    @NonNull
    private String name;

    /**
     * 预约方式
     */
    @NonNull
    private String way;

    /**
     * 预约时间
     */
    @NonNull
    private LocalDateTime bookTime;

}
