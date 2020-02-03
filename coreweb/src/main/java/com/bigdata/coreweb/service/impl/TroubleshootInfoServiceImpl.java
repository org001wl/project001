package com.bigdata.coreweb.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigdata.coreweb.entity.TroubleshootInfo;
import com.bigdata.coreweb.mapper.TroubleshootInfoMapper;
import com.bigdata.coreweb.model.TroubleshootInfoParam;
import com.bigdata.coreweb.service.ITroubleshootInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author root
 * @since 2020-02-01
 */
@Service
public class TroubleshootInfoServiceImpl extends ServiceImpl<TroubleshootInfoMapper, TroubleshootInfo> implements ITroubleshootInfoService {
    @Override
    public Page list(TroubleshootInfoParam param, Page page) {
        page.setRecords(getBaseMapper().list(param, page));
        return page;
    }
}
