package com.hk.library.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.library.common.constant.ReturnCodeEnum;
import com.hk.library.common.utils.BeanUtil;
import com.hk.library.dto.Result;
import com.hk.library.dto.query.ActivityBookQuery;
import com.hk.library.dto.query.PagesQuery;
import com.hk.library.dto.vo.ActivityVO;
import com.hk.library.entity.Activity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author
 * @since 2020-04-08
 */
@RestController
@RequestMapping("/activity")
@ResponseBody
public class ActivityController {

}

