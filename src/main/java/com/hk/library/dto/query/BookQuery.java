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
public class BookQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 书名
     */
    private String bookName;

    /**
     * 作者
     */
    private String author;

}
