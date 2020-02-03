package com.bigdata.coreweb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bigdata.coreweb.entity.TroubleshootInfo;
import com.bigdata.coreweb.model.TroubleshootInfoParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author root
 * @since 2020-02-01
 */
public interface ITroubleshootInfoService extends IService<TroubleshootInfo> {
    Page list(TroubleshootInfoParam param, Page page);
}
