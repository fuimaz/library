package com.hk.culture.mini.program.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.entity.Talent;
import lombok.NonNull;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-04-08
 */
public interface TalentService extends IService<Talent> {

    Result getByTid(@NonNull String tid);

    IPage<Talent> listByCondition(PagesQuery<Talent> pagesQuery);

    Result<Boolean> add(Talent talent);

    Result<Boolean> updateByTid(Talent talent);

    Result<Boolean> deleteByTid(String tid, String operator);

}
