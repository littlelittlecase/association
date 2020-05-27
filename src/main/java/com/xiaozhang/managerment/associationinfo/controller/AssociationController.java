package com.xiaozhang.managerment.associationinfo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaozhang.managerment.associationinfo.model.AssociationModel;
import com.xiaozhang.managerment.associationinfo.service.IAssociationService;
import com.xiaozhang.managerment.message.ResultMessage;


/**
 *    模块：社团基础信息  表：社团表  assocition
 */
@RestController
@RequestMapping(value="/association")
public class AssociationController {
	
	@Autowired
	IAssociationService associationservice = null;

	//查询所有社团
	@RequestMapping(value="/tolist")
	public List<AssociationModel> getall() throws Exception {
		return associationservice.getall();
	}
	//增加
	@RequestMapping(value="/add")
	public ResultMessage<AssociationModel> add(AssociationModel associationmodel) throws Exception {
		associationservice.add(associationmodel);
		return new ResultMessage<>("OK", "增加社团成功");
	}

	//删除
	@RequestMapping(value="/delete")
	public ResultMessage<AssociationModel> delete(AssociationModel associationmodel) throws Exception {
		associationservice.delete(associationmodel);
		return new ResultMessage<AssociationModel>("OK", "删除社团成功");
	}

	//修改
	@RequestMapping(value="/modify")
	public ResultMessage<AssociationModel> update(AssociationModel associationmodel) throws Exception {
		associationservice.update(associationmodel);
		return new ResultMessage<AssociationModel>("OK", "修改社团成功");
	}

	//检索社团列表，分页
	@GetMapping(value="/list/condition/page")
	public ResultMessage<AssociationModel> getListByConditionWithPage(	
	   
		@RequestParam(required = false,defaultValue ="") String associationname,
		@RequestParam(required = false,defaultValue ="10") int rows,
		@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
	ResultMessage<AssociationModel> result=new ResultMessage<AssociationModel>("OK","社团列表分页模式成功");	
	result.setList(associationservice.getListByConditionWithPage(associationname,rows, page));
	result.setCount(associationservice.getCountByCondition(associationname));
	result.setPageCount(associationservice.getPageByConditionWithPage(associationname,rows));
	result.setPage(page);
	result.setRows(rows);
	return result;
	}
	
	//获取社团名称
	@RequestMapping(value="/list/name")
	public List<AssociationModel>getname()throws Exception{
		return associationservice.getname();
	}
	//根据社团名检索
	@RequestMapping(value="/tolistbyname")
	public List<AssociationModel>tolistbyname(String associationname)throws Exception{
		return associationservice.getbyname(associationname);
	}

	//取得社团编号
	@RequestMapping(value="/get")
	public ResultMessage<AssociationModel>get (String associationid)throws Exception{
		ResultMessage<AssociationModel> result = new ResultMessage<AssociationModel>("ok","取得社团成功");
		System.out.println(result);
		result.setModel(associationservice.getByName(associationid));
		return result;
		
	}
	

	
	
}
