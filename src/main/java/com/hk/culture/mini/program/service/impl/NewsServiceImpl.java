package com.hk.culture.mini.program.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.culture.mini.program.common.constant.ReturnCodeEnum;
import com.hk.culture.mini.program.common.constant.StateEnum;
import com.hk.culture.mini.program.common.utils.BeanUtil;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.NewsQuery;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.dto.vo.NewsDetailVO;
import com.hk.culture.mini.program.entity.Like;
import com.hk.culture.mini.program.entity.MyCollection;
import com.hk.culture.mini.program.entity.News;
import com.hk.culture.mini.program.mapping.NewsMapper;
import com.hk.culture.mini.program.service.LikeService;
import com.hk.culture.mini.program.service.MyCollectionService;
import com.hk.culture.mini.program.service.NewsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 新闻资讯表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-05-02
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Autowired
    private LikeService likeService;
    @Autowired
    private MyCollectionService myCollectionService;

    /**
     * 条件查询，无条件则返回全部分页数据
     * @param pagesQuery
     * @return
     */
    @Override
    public IPage<News> listByCondition(PagesQuery<NewsQuery> pagesQuery) {
        QueryWrapper<News> wrapper = new QueryWrapper();
        wrapper.select("TID", "title", "brief", "`show`", "createTime", "type", "backgroundImg");
        NewsQuery queryData = pagesQuery.getData();
        if (StringUtils.isNotEmpty(queryData.getKeyword())) {
            wrapper.like("title", queryData.getKeyword() + "%");
            wrapper.or().like("brief", queryData.getKeyword() + "%");
        }

        wrapper.eq("`show`", StateEnum.ENABLE.getState());
        wrapper.orderByDesc("createTime");

        Page<News> page = new Page<>(pagesQuery.getCurrent(), pagesQuery.getPageSize());

        IPage<News> newsIPage = getBaseMapper().selectPage(page, wrapper);

        return newsIPage;
    }

    /**
     * 获取文章详情
     * @param tid
     * @param memberTid
     * @return
     */
    @Override
    public Result getByTidAndMemberTid(String tid, String memberTid) {
        News news = getBaseMapper().selectById(tid);

        if (news == null) {
            return Result.error(ReturnCodeEnum.RECORD_NOT_EXISTS, "文章不存在");
        }

        NewsDetailVO newsDetailVO = BeanUtil.convertToBean(news, NewsDetailVO.class);

        Like like = likeService.getByTargetIdAndMemberId(tid, memberTid);
        if (like != null) {
            newsDetailVO.setLike(true);
        }

        MyCollection myCollection = myCollectionService.getByTargetIdAndMemberId(tid, memberTid);
        if (myCollection != null) {
            newsDetailVO.setFavorite(true);
        }

        return Result.success(newsDetailVO);
    }
}
