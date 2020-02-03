package com.bigdata.coreweb.controller;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.coreweb.common.ResultInfo;
import com.bigdata.coreweb.common.SystemException;
import com.bigdata.coreweb.constant.ResultStatus;
import com.bigdata.coreweb.entity.CommunicateInfo;
import com.bigdata.coreweb.exception.ContentException;
import com.bigdata.coreweb.model.CommunicateParam;
import com.bigdata.coreweb.model.ExportListData;
import com.bigdata.coreweb.model.LoginInfo;
import com.bigdata.coreweb.model.StatisticsData;
import com.bigdata.coreweb.service.ICommunicateInfoService;
import com.bigdata.coreweb.util.RedisUtil;
import com.bigdata.coreweb.util.ResultInfoUtil;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

/**
 * <p>
 * 通信情况API
 * </p>
 *
 * @author root
 * @since 2020-02-01
 */
@RestController
@RequestMapping("/communicateInfo")
public class CommunicateInfoController {
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private ICommunicateInfoService communicateInfoService;
	
	/**
	 * 查询通信情况列表
	 * @param page
	 * @return
	 * @throws ContentException 
	 */
	@GetMapping("/list")
	public ResultInfo list(Page page, CommunicateParam param, @RequestHeader String token) throws ContentException {
		param.setCode(getCode(token));
		Page data = communicateInfoService.list(param, page);
		return ResultInfoUtil.success(data);
	}
	
	@GetMapping("/listByPhone")
	public ResultInfo listByPhone(Page page, CommunicateParam param, @RequestHeader String token) throws ContentException {
		param.setCode(getCode(token));
		Page data = communicateInfoService.listByPhone(param, page);
		return ResultInfoUtil.success(data);
	}
	
	/**
	 * 查询人员列表
	 * @param page
	 * @param param
	 * @param token
	 * @return
	 * @throws ContentException
	 */
	@GetMapping("/listData")
	public ResultInfo listData(Page page, CommunicateParam param, @RequestHeader String token) throws ContentException {
		param.setCode(getCode(token));
		Page data = communicateInfoService.listData(param, page);
		return ResultInfoUtil.success(data);
	}
	
	@GetMapping("/exportListData")
	public void exportListDataq(CommunicateParam param, HttpServletResponse response, @RequestHeader String token) throws IOException, ContentException {
		param.setCode(getCode(token));
		List<ExportListData> data = communicateInfoService.exportListData(param);
		ExcelWriter writer = ExcelUtil.getWriter();
		writer.addHeaderAlias("sj", "手机号码");
		writer.addHeaderAlias("gsd", "归属地");
		writer.addHeaderAlias("gsd1", "归属地1");
		writer.addHeaderAlias("lx", "来源地");
		writer.addHeaderAlias("dfxq", "到访地");
		writer.addHeaderAlias("xzmc", "乡镇名称");
		writer.addHeaderAlias("jzmc", "基站名称");
		writer.addHeaderAlias("lksj", "离开时间");
		writer.addHeaderAlias("days", "驻留时间(天)");
		writer.addHeaderAlias("yys", "运营商");
		writer.addHeaderAlias("dz", "基站地址");
		writer.addHeaderAlias("date", "最后通信日期");
		writer.addHeaderAlias("status", "是否排查");
		int size = data.size();
		int len = 60000;
		if (size < len) {
			writer.write(data);
		} else {
			int n;
			if (size % len == 0) {
				n = size / len;
			} else {
				n = size / len + 1;
			}
			for (int i = 0; i < n; i++) {
				if (i == 0) {
					writer.write(data.subList(i*len, (i+1)*len));
				} else if (i == n - 1) {
					writer.setSheet("sheet"+(i+1)).write(data.subList(i*len, size));
				} else {
					writer.setSheet("sheet"+(i+1)).write(data.subList(i*len, (i+1)*len));
				}
			}
		}
		response.setContentType("application/vnd.ms-excel;charset=utf-8"); 
		response.setHeader("Content-Disposition","attachment;filename=phone_info_"+DateUtil.today()+".xls"); 
		ServletOutputStream out = response.getOutputStream(); 
		writer.flush(out);
		writer.close();
	}
	
	@GetMapping("/statisticsData")
	public ResultInfo statisticsData(CommunicateParam param, @RequestHeader String token) throws ContentException {
		param.setCode(getCode(token));
		List<StatisticsData> data = communicateInfoService.statisticsData(param);
		return ResultInfoUtil.success(data);
	}

	/**
	 * 新增通信情况
	 * @param communicateInfo
	 * @return
	 */
	@PostMapping("/save")
	public ResultInfo save(@RequestBody CommunicateInfo communicateInfo) {
		communicateInfoService.save(communicateInfo);
		return ResultInfoUtil.success();
	}
	
	/**
	 * 修改通信情况
	 * @param communicateInfo
	 * @return
	 */
	@PostMapping("/update")
	public ResultInfo update(@RequestBody CommunicateInfo communicateInfo) {
		communicateInfoService.updateById(communicateInfo);
		return ResultInfoUtil.success();
	}
	
	/**
	 * 删除通信情况
	 * @param ids
	 * @return
	 */
	@PostMapping("/delete")
	public ResultInfo delete(@RequestBody CommunicateParam param) {
		communicateInfoService.removeByIds(Arrays.asList(param.getIds()));
		return ResultInfoUtil.success();
	}
	
	private String getCode(String token) throws ContentException {
		Object obj = redisUtil.get(token);
		if (obj == null) {
			throw new SystemException(ResultStatus.TOKEN_IS_VVALID);
		}
		LoginInfo user = (LoginInfo)obj;
		return user.getDistrictCode();
	}
}
