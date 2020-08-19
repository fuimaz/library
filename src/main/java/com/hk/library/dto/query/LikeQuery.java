package com.hk.library.dto.query;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LikeQuery {
    /**
     * 点赞的类型
     */
    private String type;

    /**
     * 点赞内容的Tid
     */
    private String targetTid;

    /**
     * 点赞的会员id
     */
    private String memberTid;
}
