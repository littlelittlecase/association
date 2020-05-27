package com.xiaozhang.managerment.schoolinfo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaozhang.managerment.message.ResultMessage;
import com.xiaozhang.managerment.schoolinfo.model.MajoyModel;
import com.xiaozhang.managerment.schoolinfo.service.IMajoyService;

/**
 *    模块：学校基础信息  表：专业表  majoy
 */
@RestController
@RequestMapping(value="/majoy")
public class MajoyController {
	
	@Autowired
	IMajoyService majoyservice = null;

	//查询所有专业
	@RequestMapping(value="/tolist")
	public List<MajoyModel> getall() throws Exception {
		return majoyservice.getall();
	}
	//增加
	@RequestMapping(value="/add")
	public ResultMessage<MajoyModel> add(MajoyModel majoymodel) throws Exception {
		majoyservice.add(majoymodel);
		return new ResultMessage<>("OK", "增加专业成功");
	}

	//删除
	@RequestMapping(value="/delete")
	public ResultMessage<MajoyModel> delete(MajoyModel majoymodel) throws Exception {
		majoyservice.delete(majoymodel);
		return new ResultMessage<MajoyModel>("OK", "删除专业成功");
	}

	//修改
	@RequestMapping(value="/modify")
	public ResultMessage<MajoyModel> update(MajoyModel majoymodel) throws Exception {
		majoyservice.update(majoymodel);
		return new ResultMessage<MajoyModel>("OK", "修改专业成功");
	}

	//检索学院列表，分页
	@GetMapping(value="/list/condition/page")
	public ResultMessage<MajoyModel> getListByConditionWithPage(	
	   
		@RequestParam(required = false,defaultValue ="") String majoyname,
		@RequestParam(required = false,defaultValue ="10") int rows,
		@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
	ResultMessage<MajoyModel> result=new ResultMessage<MajoyModel>("OK","专业列表分页模式成功");	
	result.setList(majoyservice.getListByConditionWithPage(majoyname,rows, page));
	result.setCount(majoyservice.getCountByCondition(majoyname));
	result.setPageCount(majoyservice.getPageByConditionWithPage(majoyname,rows));
	result.setPage(page);
	result.setRows(rows);
	return result;
	}
	
	//获取学院名称
	@RequestMapping(value="/list/name")
	public List<MajoyModel>getname()throws Exception{
		return majoyservice.getname();
	}
	//根据学院名检索
	@RequestMapping(value="/tolistbyname")
	public List<MajoyModel>tolistbyname(String majoyname)throws Exception{
		return majoyservice.getbyname(majoyname);
	}

	//取得学院编号
	@RequestMapping(value="/get")
	public ResultMessage<MajoyModel>get (String majoyid)throws Exception{
		ResultMessage<MajoyModel> result = new ResultMessage<MajoyModel>("ok","取得专业成功");
		System.out.println(result);
		result.setModel(majoyservice.getByName(majoyid));
		return result;
		
	}
	

	
	
}
