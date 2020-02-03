package com.bigdata.coreweb.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigdata.coreweb.entity.CommunicateInfo;
import com.bigdata.coreweb.mapper.CommunicateInfoMapper;
import com.bigdata.coreweb.model.CommunicateParam;
import com.bigdata.coreweb.model.ExportListData;
import com.bigdata.coreweb.model.StatisticsData;
import com.bigdata.coreweb.service.ICommunicateInfoService;

/**
 * <p>
 * 通信情况表 服务实现类
 * </p>
 *
 * @author root
 * @since 2020-02-01
 */
@Service
public class CommunicateInfoServiceImpl extends ServiceImpl<CommunicateInfoMapper, CommunicateInfo> implements ICommunicateInfoService {

	@Override
	public Page list(CommunicateParam param, Page page) {
		page.setRecords(getBaseMapper().list(param, page));
		return page;
	}

	@Override
	public Page listByPhone(CommunicateParam param, Page page) {
		page.setRecords(getBaseMapper().listByPhone(param, page));
		return page;
	}

	@Override
	public Page listData(CommunicateParam param, Page page) {
		page.setRecords(getBaseMapper().listData(param, page));
		return page;
	}

	@Override
	public List<ExportListData> exportListData(CommunicateParam param) {
		return getBaseMapper().exportListData(param);
	}

	@Override
	public List<StatisticsData> statisticsData(CommunicateParam param) {
		List<StatisticsData> data = getBaseMapper().statisticsData(param);
		StatisticsData o = new StatisticsData();
		o.setArea("安康市");
		o.setPhoneNum(data.stream().mapToInt(StatisticsData::getPhoneNum).sum());
		o.setWhNum(data.stream().mapToInt(StatisticsData::getWhNum).sum());
		o.setHbNum(data.stream().mapToInt(StatisticsData::getHbNum).sum());
		o.setOneNum(data.stream().mapToInt(StatisticsData::getOneNum).sum());
		o.setTwoNum(data.stream().mapToInt(StatisticsData::getTwoNum).sum());
		o.setThreeNum(data.stream().mapToInt(StatisticsData::getThreeNum).sum());
		data.add(o);
		return data;
	}
}
