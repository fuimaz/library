package com.hk.culture.mini.program.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.dto.vo.LiteraryTeamVO;
import com.hk.culture.mini.program.entity.LiteraryTeam;

/**
 * <p>
 * 文艺团队服务表 服务类
 * </p>
 *
 * @author 
 * @since 2020-06-07
 */
public interface LiteraryTeamService extends IService<LiteraryTeam> {

    Result<LiteraryTeamVO> getByTid(String tid);

    IPage<LiteraryTeamVO> listByCondition(PagesQuery<LiteraryTeam> pagesQuery);

    Result<Boolean> add(LiteraryTeamVO literaryTeamVO);

    Result<Boolean> updateByTid(LiteraryTeamVO routeVO);

    Result<Boolean> deleteByTid(String tid, String operator);
}
