package com.hk.culture.mini.program.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.entity.LitTeamMember;

import java.util.List;

/**
 * <p>
 * 文艺团队成员表 服务类
 * </p>
 *
 * @author 
 * @since 2020-06-07
 */
public interface LitTeamMemberService extends IService<LitTeamMember> {
    
    List<LitTeamMember> listByTeamTid(String routeTid);
    
    Result<Boolean> deleteByTeamTid(String routeTid);
    
    Result<Boolean> add(LitTeamMember routeRelate);
    
    Result<Boolean> updateByTid(LitTeamMember routeRelate);
    
    Result<Boolean> deleteByTid(String tid, String operator);
}
