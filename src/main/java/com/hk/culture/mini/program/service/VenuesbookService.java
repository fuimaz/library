package com.hk.culture.mini.program.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.culture.mini.program.common.constant.BookTypeEnum;
import com.hk.culture.mini.program.entity.Venuesbook;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-04-08
 */
public interface VenuesbookService extends IService<Venuesbook> {

    int bookedActiveRecordCount(String phone);

    List<Venuesbook> listByTidAndDate(String tid, LocalDateTime dateTime, BookTypeEnum bookTypeEnum);

    Venuesbook getOneByUserAndId(String phone, BookTypeEnum bookTypeEnum, LocalDateTime bookTime, String sceneId);

}
