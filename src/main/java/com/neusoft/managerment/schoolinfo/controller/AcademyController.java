package com.neusoft.managerment.schoolinfo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.managerment.message.ResultMessage;
import com.neusoft.managerment.office.model.NewsModel;
import com.neusoft.managerment.schoolinfo.model.AcademyModel;
import com.neusoft.managerment.schoolinfo.service.IAcademyService;

/**
 *    模块：学校基础信息  表：学院表  academy
 */
@RestController
@RequestMapping(value="/academy")
public class AcademyController {
	
	@Autowired
	IAcademyService academyservice = null;

	//查询所有学院
	@RequestMapping(value="/tolist")
	public List<AcademyModel> getall() throws Exception {
		return academyservice.getall();
	}
	//增加
	@RequestMapping(value="/add")
	public ResultMessage<AcademyModel> add(AcademyModel academymodel) throws Exception {
		academyservice.add(academymodel);
		return new ResultMessage<>("OK", "增加学院成功");
	}

	//删除
	@RequestMapping(value="/delete")
	public ResultMessage<AcademyModel> delete(AcademyModel academymodel) throws Exception {
		academyservice.delete(academymodel);
		return new ResultMessage<AcademyModel>("OK", "删除学院成功");
	}

	//修改
	@RequestMapping(value="/modify")
	public ResultMessage<AcademyModel> update(AcademyModel academymodel) throws Exception {
		academyservice.update(academymodel);
		return new ResultMessage<AcademyModel>("OK", "修改学院成功");
	}

	//检索学院列表，分页
	@GetMapping(value="/list/condition/page")
	public ResultMessage<AcademyModel> getListByConditionWithPage(	
	   
		@RequestParam(required = false,defaultValue ="") String academyname,
		@RequestParam(required = false,defaultValue ="10") int rows,
		@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
	ResultMessage<AcademyModel> result=new ResultMessage<AcademyModel>("OK","学院新闻列表分页模式成功");	
	result.setList(academyservice.getListByConditionWithPage(academyname,rows, page));
	result.setCount(academyservice.getCountByCondition(academyname));
	result.setPageCount(academyservice.getPageByConditionWithPage(academyname,rows));
	result.setPage(page);
	result.setRows(rows);
	return result;
	}
	
	//获取学院名称
	@RequestMapping(value="/list/name")
	public List<AcademyModel>getname()throws Exception{
		return academyservice.getname();
	}
	//根据学院名检索
	@RequestMapping(value="/tolistbyname")
	public List<AcademyModel>tolistbyname(String academyname)throws Exception{
		return academyservice.getbyname(academyname);
	}

	//取得学院编号
	@RequestMapping(value="/get")
	public ResultMessage<AcademyModel>get (String academyid)throws Exception{
		ResultMessage<AcademyModel> result = new ResultMessage<AcademyModel>("ok","取得学院成功");
		System.out.println(result);
		result.setModel(academyservice.getByName(academyid));
		return result;
		
	}
	

	
	
}
