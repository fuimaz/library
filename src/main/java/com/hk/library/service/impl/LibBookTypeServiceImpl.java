package com.hk.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hk.library.common.constant.StateEnum;
import com.hk.library.dto.Result;
import com.hk.library.entity.LibBookType;
import com.hk.library.mapper.LibBookTypeMapper;
import com.hk.library.service.LibBookTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 书籍类型 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-08-22
 */
@Service
public class LibBookTypeServiceImpl extends ServiceImpl<LibBookTypeMapper, LibBookType> implements LibBookTypeService {

    @Override
    public List<LibBookType> listAll() {
        return getBaseMapper().selectList(new QueryWrapper<>());
    }

    @Override
    public boolean addBookType(String typeName, String desc) {
        LibBookType libBookType = new LibBookType();
        libBookType.setTypeIntro(desc);
        libBookType.setTypeName(typeName);
        libBookType.setCreateTime(LocalDateTime.now());
        libBookType.setUpdateTime(LocalDateTime.now());
        libBookType.setStatus(StateEnum.ENABLE.getStateCode());

        return getBaseMapper().insert(libBookType) == 1;
    }
}
