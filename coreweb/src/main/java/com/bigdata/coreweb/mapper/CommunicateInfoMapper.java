package com.bigdata.coreweb.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.coreweb.entity.CommunicateInfo;
import com.bigdata.coreweb.model.CommunicateParam;
import com.bigdata.coreweb.model.ExportListData;
import com.bigdata.coreweb.model.StatisticsData;

/**
 * <p>
 * 通信情况表 Mapper 接口
 * </p>
 *
 * @author root
 * @since 2020-02-01
 */
public interface CommunicateInfoMapper extends BaseMapper<CommunicateInfo> {
	List<Map<String, Object>> list(CommunicateParam param, Page page);
	List<Map<String, Object>> listByPhone(CommunicateParam param, Page page);
	List<Map<String, Object>> listData(CommunicateParam param, Page page);
	List<ExportListData> exportListData(CommunicateParam param);
	List<StatisticsData> statisticsData(CommunicateParam param);
}
