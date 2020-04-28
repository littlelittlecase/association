package com.neusoft.managerment.baseinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.managerment.baseinfo.model.MaintainProviderModel;

import com.neusoft.managerment.baseinfo.service.IMaintainproviderService;
import com.neusoft.managerment.message.ResultMessage;

/**
 *    模块：基础信息  表：维修单位 maintainprovider
 *    author : 张梓琪
 *    version: 1.0
 */
//维修单位的控制类
@RestController
@RequestMapping("/maintainprovider")
public class MaintainproviderController {
	@Autowired
	IMaintainproviderService maintainproviderservice = null;
	//增加
	@RequestMapping(value="/add")
	public ResultMessage<MaintainProviderModel> add(MaintainProviderModel maintainprovidermodel)throws Exception{
		maintainproviderservice.add(maintainprovidermodel);
		return new ResultMessage<MaintainProviderModel>("OK","增加维修单位成功");
		
	} 
	//删除
	@RequestMapping(value="/delete")
	public ResultMessage<MaintainProviderModel> delete(MaintainProviderModel maintainprovidermodel)throws Exception{
		maintainproviderservice.delete(maintainprovidermodel);
		return new ResultMessage<MaintainProviderModel>("OK","删除维修单位成功");
		
	} 
	//修改
	@RequestMapping(value="/modify")
	public ResultMessage<MaintainProviderModel> modify(MaintainProviderModel maintainprovidermodel)throws Exception{
		maintainproviderservice.update(maintainprovidermodel);
		return new ResultMessage<MaintainProviderModel>("OK","修改维修单位成功");
			
	} 
	//根据综合条件查询，分页
	@GetMapping(value="/list/all/page")
	public ResultMessage<MaintainProviderModel>getlistbycondition(
			@RequestParam(required = false,defaultValue ="") String pname,
			@RequestParam(required = false,defaultValue ="") String paddress,
			@RequestParam(required = false,defaultValue ="10") int rows,
			@RequestParam(required = false,defaultValue = "1") int page
			) throws Exception{
		ResultMessage<MaintainProviderModel>result = new ResultMessage<MaintainProviderModel>("OK","维修单位列表分页模式成功");
		System.out.println("11"+result);
		result.setCount(maintainproviderservice.getCountByCondition(pname, paddress));
		result.setList(maintainproviderservice.getListByConditionWithPage(pname, paddress, rows, page));
		result.setPageCount(maintainproviderservice.getPageByConditionWithPage(pname, paddress, rows));
		result.setPage(page);
		result.setRows(rows);
		return result;

	}
	@GetMapping(value="/list/all")
	public List<MaintainProviderModel>getlistbyall() throws Exception{
		return maintainproviderservice.getall();
		
	}

}
