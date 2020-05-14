package com.hk.culture.mini.program.dto.query;

import lombok.Data;

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
public class PagesQuery<T> implements Serializable {

    private static final long serialVersionUID=1L;

    private boolean orderByDesc = true;
    private String orderByType;

    private T data;

    private int current = 1;

    private int pageSize = 10;

}
