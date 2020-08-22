package com.hk.library.mapper;

import com.hk.library.entity.LibBook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 书籍 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-08-22
 */
public interface LibBookMapper extends BaseMapper<LibBook> {

    int updateRepertoryCnt(@Param("updateCnt") int updateCnt, @Param("id") int id);
}
