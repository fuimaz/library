package com.hk.library.dto.query;

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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class NewsQuery implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 关键字
     */
    private String keyword;

}
