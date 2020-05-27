package com.xiaozhang.managerment.associationinfo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaozhang.managerment.associationinfo.model.ApplicationModel;
import com.xiaozhang.managerment.associationinfo.service.IApplicationService;
import com.xiaozhang.managerment.communityinfo.model.CommunityActiveModel;
import com.xiaozhang.managerment.message.ResultMessage;


/**
 *    模块：社团审核基础信息  表：社团审核表  application
 */
@RestController
@RequestMapping(value="/application")
public class ApplicationController {
	
	@Autowired
	IApplicationService applicationservice = null;

	//查询
	@RequestMapping(value="/tolist")
	public List<ApplicationModel> getall() throws Exception {
		return applicationservice.getall();
	}
	//增加
	@RequestMapping(value="/add")
	public ResultMessage<ApplicationModel> add(ApplicationModel applicationmodel) throws Exception{
		
		applicationservice.add(applicationmodel);
		
		return new ResultMessage<ApplicationModel>("ok","增加成功");
	}

	//修改
	@RequestMapping(value="/modify")
	public ResultMessage<ApplicationModel> update(ApplicationModel applicationmodel) throws Exception {
		applicationservice.update(applicationmodel);
		System.out.println("修改社团审核成功");
		return new ResultMessage<ApplicationModel>("OK", "修改社团审核成功");
		
	}

	//检索社团列表，分页
	@GetMapping(value="/list/condition/page")
	public ResultMessage<ApplicationModel> getListByConditionWithPage(	
		@RequestParam(required = false,defaultValue ="") String appname,
		@RequestParam(required = false,defaultValue ="10") int rows,
		@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
	ResultMessage<ApplicationModel> result=new ResultMessage<ApplicationModel>("OK","社团审核列表分页模式成功");	
	result.setList(applicationservice.getListByConditionWithPage(appname,rows, page));
	result.setCount(applicationservice.getCountByCondition(appname));
	result.setPageCount(applicationservice.getPageByConditionWithPage(appname,rows));
	result.setPage(page);
	result.setRows(rows);
	return result;
	}
	
	//获取社团名称
	@RequestMapping(value="/list/name")
	public List<ApplicationModel>getname()throws Exception{
		return applicationservice.getname();
	}
	//根据社团名检索
	@RequestMapping(value="/tolistbyname")
	public List<ApplicationModel>tolistbyname(String appname)throws Exception{
		return applicationservice.getbyname(appname);
	}

	//取得社团编号
	@RequestMapping(value="/get")
	public ResultMessage<ApplicationModel>get (String appid)throws Exception{
		ResultMessage<ApplicationModel> result = new ResultMessage<ApplicationModel>("ok","取得社团审核成功");
		System.out.println(result);
		result.setModel(applicationservice.getByName(appid));
		return result;
		
	}
	

	
	
}
