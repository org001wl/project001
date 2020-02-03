package com.bigdata.coreweb.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.coreweb.common.ResultInfo;
import com.bigdata.coreweb.common.SystemException;
import com.bigdata.coreweb.constant.ResultStatus;
import com.bigdata.coreweb.entity.District;
import com.bigdata.coreweb.service.IDistrictService;
import com.bigdata.coreweb.util.ResultInfoUtil;
import com.bigdata.coreweb.util.StringUtil;
import com.bigdata.coreweb.vo.DistrictVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author root
 * @since 2020-02-01
 */
@RestController
@RequestMapping("/district")
public class DistrictController {
    @Autowired
    private IDistrictService districtService;
    @ApiOperation("用户添加")
    @PostMapping("/add")
    public ResultInfo add(District district) throws SystemException {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", district.getName());
        List<District> districtList = districtService.list(queryWrapper);
        QueryWrapper queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("code", district.getCode());
        List<District> districtList1 = districtService.list(queryWrapper1);
        if (districtList.size() > 0) {
            throw new SystemException(ResultStatus.DISTRICT_NAME_REPEAT);
        } else if (districtList1.size()>0){
            throw new SystemException(ResultStatus.DISTRICT_CODE_REPEAT);
        }
        district.setCreateTime(System.currentTimeMillis());
        district.setUpdateTime(System.currentTimeMillis());
        districtService.save(district);
        return ResultInfoUtil.success();
    }

    @ApiOperation("用户修改")
    @PostMapping("/update")
    public ResultInfo update(District district) throws SystemException {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", district.getName());
        queryWrapper.ne("id", district.getId());
        List<District> districtList = districtService.list(queryWrapper);
        QueryWrapper queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("code", district.getCode());
        queryWrapper1.ne("id", district.getId());
        List<District> districtList1 = districtService.list(queryWrapper1);
        if (districtList.size() > 0) {
            throw new SystemException(ResultStatus.DISTRICT_NAME_REPEAT);
        } else if (districtList1.size()>0){
            throw new SystemException(ResultStatus.DISTRICT_CODE_REPEAT);
        }
        district.setUpdateTime(System.currentTimeMillis());
        districtService.updateById(district);
        return ResultInfoUtil.success();
    }

    @ApiOperation("区划删除")
    @DeleteMapping("/delete")
    public ResultInfo delete(String[] ids) throws SystemException {
        for (String id : ids) {
            districtService.removeById(id);
        }
        return ResultInfoUtil.success();
    }
    /**
     *查询顶级区划
     */
    @ApiOperation("查询按code查询区划左边")
    @PostMapping("/find")
    public ResultInfo find(String code) throws SystemException {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",code);
        List<District> districtList = districtService.list(queryWrapper);
        return ResultInfoUtil.success(districtList);
    }
    /**
     * 分页查询该区划的子区划
     */
    @ApiOperation("查询顶级区划左边")
    @PostMapping("/findPage")
    public ResultInfo findPage(DistrictVo district) throws SystemException {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(!StringUtil.isNullOrEmpty(district.getCode())){
            queryWrapper.eq("parent_id",district.getCode());
        }else {
            queryWrapper.isNull("parent_id");
        }
        if(!StringUtil.isNullOrEmpty(district.getName())){
            queryWrapper.like("name",district.getName());
        }
        Page page = new Page();
        if (district.getCurrent()!=null){
            page.setCurrent(district.getCurrent());
        }
        if (district.getSize()!=null){
            page.setSize(district.getSize());
        }
        IPage page1=districtService.page(page,queryWrapper);
        return ResultInfoUtil.success(page1);
    }

    /**
     * 查询所有区划
     */
    @GetMapping ("/findTree")
    public ResultInfo findTree() throws SystemException {
        List<District> districtList = districtService.list();
        ArrayList<District> parentContainer= new ArrayList<District>();
        this.generateTree(null,parentContainer,districtList);
        return ResultInfoUtil.success(parentContainer);
    }
    private void generateTree(String parentId, List<District> parentContainer, List<District> all) {
        for (District item : all) {
            if (StringUtil.equals(item.getParentId(), parentId)) {
                parentContainer.add(item);
                item.setChildren(new ArrayList<>());
                generateTree(item.getId(), item.getChildren(), all);
            }
        }

    }
}

