package com.hk.culture.mini.program.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.dto.vo.VenuesVO;
import com.hk.culture.mini.program.entity.Talent;
import com.hk.culture.mini.program.entity.Venues;
import com.hk.culture.mini.program.mapping.TalentMapper;
import com.hk.culture.mini.program.service.TalentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-04-11
 */
@Service
public class TalentServiceImpl extends ServiceImpl<TalentMapper, Talent> implements TalentService {

    /**
     * 条件查询，无条件则返回全部分页数据
     * @param pagesQuery
     * @return
     */
    @Override
    public IPage<Talent> listByCondition(PagesQuery<Talent> pagesQuery) {
        QueryWrapper<Talent> wrapper = new QueryWrapper();

        wrapper.select("TID", "name");
        Talent talent = pagesQuery.getData();
        // todo 对人才按点赞数进行排名展示
//        wrapper.orderByDesc("stratTime");

        Page<Talent> page = new Page<>(pagesQuery.getCurrent(), pagesQuery.getPageSize());

        IPage<Talent> talentIPage = getBaseMapper().selectPage(page, wrapper);

        return talentIPage;
    }
}
